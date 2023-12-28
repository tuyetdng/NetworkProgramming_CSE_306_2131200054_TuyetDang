package eiu.cit.netprog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.Scanner;

public class tic_tac_toe_client {
    public static void main(String[] args) {
        Socket socket = null;

        try (Scanner sc = new Scanner(System.in)) {
            socket = new Socket("localhost", 16);
            socket.setSoTimeout(15000);
            Writer writer = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
            writer = new BufferedWriter(writer);
            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            BufferedReader terminal = new BufferedReader(new InputStreamReader(System.in));
            // for (int i = 0; i < 3; i++) {
                System.out.println(reader.readLine());
            // }

            // writer.flush(); 
            String move = terminal.readLine();
            while (!move.equals("quit")) {
                writer.write(move + "\r\n");
                writer.flush();

                // for (int i = 0; i < 3; i++) {
                    System.out.println(reader.readLine());
                // }
                move = terminal.readLine();
            }

            reader.close();
            writer.close();

        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (socket != null) {
            try {
            socket.close();
            } catch (IOException ex) {

            }
            }

        }
    }

}
