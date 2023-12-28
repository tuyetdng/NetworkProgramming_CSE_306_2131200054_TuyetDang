package eiu.cit.netprog;
import java.net.InetAddress;

public class IPToDomainName {
    public static void main(String[] args) {
        try {
            String ipAddress = "192.168.72.177"; // Replace with the IP address you want to resolve to a domain name
            InetAddress address = InetAddress.getByName(ipAddress);
            String domainName = address.getHostName();
            System.out.println("IP Address: " + ipAddress);
            System.out.println("Domain Name: " + domainName);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
}

    
