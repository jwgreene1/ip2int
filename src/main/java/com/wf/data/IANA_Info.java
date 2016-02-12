/**
 * Stores the Internet Address Number Administration information
 */
package com.wf.data;

public class IANA_Info {
    public String prefix;
    public String designation;
    public String status;
    
    /**
     * Constructor assigns the values passed
     * @param prefix
     * @param designation
     * @param status 
     */
    public IANA_Info(String prefix, String designation, String status){
        this.prefix = prefix;
        this.designation = designation;
        this.status = status;
    }
    
    /**
     * Prints the IANA info
     */
    public void Printinfo(){
        System.out.println("        IANA Information for Prefix: "+prefix);
        System.out.println("                        Designation: "+designation);
        System.out.println("                             Status: "+status);
    }
}
