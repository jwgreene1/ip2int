## Synopsis

Author: Josh Greene

Code assignment for Requisition #: 2204632

ip2int is a utility that converts an IP address to it's integer equivalent.  The 
calculation for this is as follows.

```
1st octet X 256 cubed + 2nd octet X 256 squared + 3rd octet X 256 + 4th octet = integer value
```

If you choose to download the "ipv4-address-space.csv" file to the execution directory
then the utility will also display the owner and status of the first octet prefix...although this 
is admittedly not very interesting after 57.x.x.x!

## Execution

You may choose to compile the source code yourself using the pom.xml file included, or you may
use the jar file as shown here.

```
$ java -jar ip2int-1.0-jar-with-dependencies.jar 

### IP to Integer Conversion Utility Begin - 2016 02-12 04:55:04 ###


Please enter a valid IP address or (q) to quit: 192.168.0.1

        The integer value of IP 192.168.0.1 is: 3232235521
        IANA Information for Prefix: 192
                        Designation: Administered by ARIN
                             Status: LEGACY

Please enter a valid IP address or (q) to quit: q

### IP to Integer Conversion Utility End - 2016 02-12 04:55:22 ###
```
