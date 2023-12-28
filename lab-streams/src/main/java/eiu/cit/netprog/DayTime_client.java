package eiu.cit.netprog;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class DayTime_client {
    public static final int PORT = 14;
    public static final int TIMEOUT = 15000;

    public static void main(String[] args) {
        Socket socket = null;

        try {
            socket = new Socket("localhost", PORT);
            socket.setSoTimeout(TIMEOUT);
            
            InputStreamReader in = new InputStreamReader(socket.getInputStream());
            BufferedReader reader = new BufferedReader(in);
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                System.out.println(line);
            }
            reader.close();
        } catch (Exception e) {
            System.err.println(e);
        }

    }
}
