package eiu.cit.netprog;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Assignment_WebLog {

    public static void main(String[] args) {
        String pathName = "E:\\Documents\\CSE 306_NP\\lab-streams\\src\\main\\resources\\weblog1.txt";
        try (FileReader fr = new FileReader(pathName)) {
            BufferedReader br = new BufferedReader(fr);

            for (String line = br.readLine(); line != null; line = br.readLine()) {
                int index = line.indexOf(' ');
                String ipAddress = line.substring(0, index);
                String resLine = line.substring(index);

                InetAddress address = InetAddress.getByName(ipAddress);
                System.out.println(address.getHostName() + resLine);

            }
        } catch (UnknownHostException ex) {

            System.err.println("Could not find domain name");

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }
}
