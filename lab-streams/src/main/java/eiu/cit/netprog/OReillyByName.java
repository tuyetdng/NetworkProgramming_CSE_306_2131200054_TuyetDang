package eiu.cit.netprog;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class OReillyByName {

    public static void main(String[] args) {

        try {
            byte[] addressIP = { 45, 118, (byte) 137, (byte) 172 };

            InetAddress address = InetAddress.getByAddress(addressIP);
            InetAddress addressWithName = InetAddress.getByAddress("address.com", addressIP);

            System.out.println(address);

        } catch (UnknownHostException ex) {

            System.out.println("Could not find www.eiu.edu.vn");

        }

    }

}