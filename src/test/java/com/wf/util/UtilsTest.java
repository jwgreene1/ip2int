package com.wf.util;

import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigInteger;

public class UtilsTest {

   @Test
   /**
    * Validates that the integer value is being calculated properly
    */
   public void testGetIpIntegerValue() {

      String[] testIPArr = {"192", "168", "0", "1"};
      BigInteger expected = new BigInteger("3232235521");
      BigInteger actual = Utils.GetIpIntegerValue(testIPArr);

      assertEquals(expected, actual);
      
      String[] testIPArr2 = {"255", "255", "255", "255"};
      expected = new BigInteger("4294967295");
      actual = Utils.GetIpIntegerValue(testIPArr2);

      assertEquals(expected, actual);
      
      String[] testIPArr3 = {"001", "01", "1", "001"};
      expected = new BigInteger("16843009");
      actual = Utils.GetIpIntegerValue(testIPArr3);

      assertEquals(expected, actual);
   }
   
   @Test
   /**
    * Validates the octet padding works properly
    */
   public void testPadOctet(){
       
       String octet = "1";
       String expected = "001";
       String actual = Utils.PadOctet(octet);
       
       assertEquals(expected, actual);
       
       octet = "10";
       expected = "010";
       actual = Utils.PadOctet(octet);
       
       assertEquals(expected, actual);
       
       octet = "100";
       expected = "100";
       actual = Utils.PadOctet(octet);
       
       assertEquals(expected, actual);
   }
}
