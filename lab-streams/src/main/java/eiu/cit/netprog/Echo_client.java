package eiu.cit.netprog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.Scanner;

public class Echo_client {
    public static void main(String[] args) {
        Socket socket = null;

        try (Scanner sc = new Scanner(System.in)) {
            socket = new Socket("localhost", 15);
            socket.setSoTimeout(15000);
            Writer writer = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
            writer = new BufferedWriter(writer);
            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            BufferedReader terminal = new BufferedReader(new InputStreamReader(System.in));
            String input = terminal.readLine();
            while (!input.equals("quit")) {
                writer.write(input + "\r\n");
                writer.flush();
                readLine(reader);
                input = terminal.readLine();
            }
            writer.write("quit\r\n");
            writer.flush();
            reader.close();
            writer.close();

        } catch (Exception e) {
            System.err.println(e);
        } finally {
            // if (socket != null) {
            // try {
            // socket.close();
            // } catch (IOException ex) {

            // }
            // }

        }
    }

    public static void readLine(BufferedReader reader) {
        try {
            String line = reader.readLine();
            System.out.println(line);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
