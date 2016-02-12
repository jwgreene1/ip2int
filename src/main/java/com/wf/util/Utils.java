package com.wf.util;

import java.io.File;
import java.util.Scanner;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.apache.commons.io.FileUtils;

public class Utils {

    /**
     * Returns input from STDIN
     * @return user input
     */
    public static String ReadInput() {

        Scanner reader = new Scanner(System.in);

        return reader.next();
    }

    /**
     * Returns the integer value of the IP address passed.
     * @param ipArr string array containing the four octets of the IP Address
     * @return      integer value of the IP Address
     */

    public static BigInteger GetIpIntegerValue(String[] ipArr){

        BigInteger returnValue = new BigInteger("0");

        try {
            //The multiplier starts as 256 cubed to be multiplied to the first 
            //octet and is then reduced in the loop.
            BigInteger multiplier = new BigInteger("16777216");
            BigInteger divider = new BigInteger("256");

            for (int i = 0; i < ipArr.length; i++) {

                //Add the octet multiplied by the multiplier to the return value
                returnValue = returnValue.add(multiplier.multiply(new BigInteger(ipArr[i])));

                //Now reduce the multiplier by a factor of 256, but only if there 
                //will be another iteration of the loop
                if(i < (ipArr.length - 1)){
                    multiplier = multiplier.divide(divider);
                }
            }
        }catch(Exception e){
            System.out.println("Exception in Utils.GetIpIntegerValue: "+e.getMessage());
            e.printStackTrace();
        }

        return returnValue;
    }

    /**
     * Validates the IP Address passed in using the org.apache.commons InetAddressValidator
     * @param ip ip address submitted from the user
     * @return   boolean value indicating whether the ip address was valid, default: false
     */

    public static Boolean ValidateIP(String ip){

        Boolean result = false;

        try{
            result = InetAddressValidator.getInstance().isValid(ip);
        }catch(Exception e){
            System.out.println("Exception in Utils.ValidateIP: "+e.getMessage());
            e.printStackTrace();
        }

        return result;
    }
    
    /**
     * Returns a List of strings containing the file entries
     * @param  file the file to be read
     * @return list of strings with the file entries
     */
    public static List ReadFile(File file){
        List returnList = Collections.EMPTY_LIST;
        
        try{
            returnList = FileUtils.readLines(file, null);
            
        }catch(Exception e){
            System.out.println("Exception in Utils.ReadFile: "+e.getMessage());
            e.printStackTrace();
        }
        
        return returnList;
    }
     
    /**
     * Adds zeros to the octet, i.e. "10" becomes "010"
     * @param octet octet to which to add zeros
     * @return      octet padded with zeros if necessary
     */
    public static String PadOctet(String octet){
        String returnString = "";
        
        String[] octetArr = octet.split("");

        switch(octetArr.length){
            case 1: returnString = "00" + octet;
                    break;
            case 2: returnString = "0" + octet;
                    break;
            case 3: returnString = octet;
                    break;
            default: returnString = "INVALID";
        }
        
        return returnString;
    }
}
