package com.wf;

import com.wf.data.IANA_Info;
import com.wf.util.Utils;
import java.io.File;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
/**
 * Runs the loop prompting the user for an IP address and prints the results
 * IANA refers to Internet Assigned Numbers Authority
 */
public class RunConverter {

    private String ip;
    private String[] ipArr = new String[4];
    private BigInteger ipIntValue;
    private Boolean ianaLookup = false;
    private String ianaFile = "./ipv4-address-space.csv";
    private HashMap<String, IANA_Info> ianaMap = new HashMap();

    public void Execute(){
        String response = "q";
        
        //Determine if the required IANA file is in place and if so, build the
        //HashMap with it's data
        SetIanaData();

        do {
            try {
                //Prompt user for an IP Address
                System.out.print("\nPlease enter a valid IP address or (q) to quit: ");

                //Read input
                response = Utils.ReadInput();

                //Validate the address
                if (Utils.ValidateIP(response)) {

                    ip = response;

                    //Break the address into octets
                    ipArr = ip.split("\\.");

                    //Compute the integer value
                    ipIntValue = Utils.GetIpIntegerValue(ipArr);

                    //Report the integer value
                    System.out.println("\n        The integer value of IP " + ip + " is: " + ipIntValue);
                    
                    //If the IANA data is available, print that information
                    if(ianaLookup){
                        //Get the first octet padded with zeros(if needed)
                        String key = Utils.PadOctet(ipArr[0]);
                        
                        if(ianaMap.containsKey(key)){
                            ianaMap.get(key).Printinfo();
                        }
                    }

                } else {
                    if(!response.equals("q") && !response.equals("Q")) {
                        System.out.println("\nError: Invalid IP address: " + response);
                    }
                }
            }catch(Exception e){
                System.out.println("Exception in com.wf.RunConverter.Execute: "+e.getMessage());
                e.printStackTrace();
            }

            //Repeat until the user chooses to quit
        }while(!response.equals("q") && !response.equals("Q"));
    }
    
    /**
     * Check for the required file and build the IANA HashMap
     */
    private void SetIanaData(){
        File inFile = new File(ianaFile);
        
        //First test to make sure the file is there and readable
        if(!inFile.canRead()){
            System.out.println("    If you would like to see the IANA designation for any IP Addresses converted,");
            System.out.println("    please put the \"ipv4-address-space.csv\" file in the current directory.\n");
            
        }else{
            //Then test to make sure it is formatted correctly and read into the hashmap
            if(!SetIanaData(inFile)){    
                System.out.println("    There was a problem reading the IANA file data.\n");
            }else{
                //Data was read and stored successfully, enable lookup
                ianaLookup = true;
            }
        }
    }
    
    /**
     * Reads the IANA file info into the HashMap for later use
     * @param file file to read in
     * @return     Boolean indicating success
     */
    private Boolean SetIanaData(File file){
        Boolean success = true;
        
        try{
            //Read the file in
            List<String> fileContents = Utils.ReadFile(file);

            //Only proceed if there are contents available
            if(fileContents.isEmpty()){
                success = false;
            }else{
                //Process each entry in the file
                for(String line : fileContents){

                    //Only accept lines the start with an integer, i.e. - skip the header
                    if(Character.isDigit(line.charAt(0))){

                        String[] lineArr = line.split(",");

                        //Test to make sure the file entry has the number of fields we expect, 6 or 7
                        if(!(lineArr.length == 6 || lineArr.length == 7)){

                            System.out.println("Ill-formed IANA file entry: "+line);
                            success = false;
                            
                        }else{
                            //Build the key using the starting octet
                            if(lineArr[0].contains("/8")){

                                //We only want the octet, not the "/8"
                                String key = lineArr[0].substring(0, lineArr[0].indexOf("/8"));

                                //Add the entry to the hashmap with the prefix, owner, and status
                                ianaMap.put(key, new IANA_Info(key, lineArr[1], lineArr[5]));
                            }
                        }
                    }
                }
            }
        }catch(Exception e){
            System.out.println("Exception in RunConverter.SetIanaData: "+e.getMessage());
            e.printStackTrace();
            success = false;
        }
        
        return success;
    }
}
