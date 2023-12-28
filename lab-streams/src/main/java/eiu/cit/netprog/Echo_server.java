package eiu.cit.netprog;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class Echo_server {
    public final static int PORT = 15;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                try (Socket connection = server.accept()) {
                    Writer out = new OutputStreamWriter(connection.getOutputStream());
                    InputStreamReader in = new InputStreamReader(connection.getInputStream());
                    BufferedReader reader = new BufferedReader(in);

                    for (String line = reader.readLine(); !line.equals("quit");line = reader.readLine()) {
                        out.write(line + "\r\n");
                        out.flush();

                    }
                     connection.close();
                     server.close();
                     break;
                } catch (Exception e) {
                }
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
