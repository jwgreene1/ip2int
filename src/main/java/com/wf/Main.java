package com.wf;
/**
 *  Code Assignment for Requisition: Wells Fargo-2204632
 *
 *  ip2int prompts the user for an IP address and converts it to an integer.
 *
 *  @author     Josh Greene
 *  @version    1.0
 *  @since      2016-02-12
 *
 */

import java.util.Date;
import java.text.SimpleDateFormat;

public class Main {

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM-dd hh:mm:ss");

    public static void main(String[] args) {

        String date = sdf.format(new Date());

        System.out.println("\n### IP to Integer Conversion Utility Begin - "+date+" ###\n");

        RunConverter runConverter = new RunConverter();

        runConverter.Execute();

        date = sdf.format(new Date());

        System.out.println("\n### IP to Integer Conversion Utility End - "+date+" ###\n");
    }
}
