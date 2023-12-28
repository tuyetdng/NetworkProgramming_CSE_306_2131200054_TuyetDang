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

public class ElizaClient {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Socket socket = null;
            try {
                socket = new Socket("telehack.com", 23);
                socket.setSoTimeout(15000);
                Writer writer = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
                writer = new BufferedWriter(writer);
                InputStream in = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

                char cop = 'a';

                for (int c = reader.read(); c != -1; c = reader.read()) {
                    System.out.print((char) c);

                    if ((char) c == ('.')) {
                        if ((char) cop == ('\n')) {
                            break;
                        }
                    }
                    cop = (char) c;
                }
                BufferedReader terminal = new BufferedReader(new InputStreamReader(System.in));
                String input = terminal.readLine();

                writer.write(input + "\r\n");
                writer.flush();

                readFirstLine(reader);
                // readEliza(reader);

                while (!input.equals("quit")) {
                    input = terminal.readLine();
                    writer.write(input + "\r\n");
                    writer.flush();
                    readEliza(reader);
                }
                writer.write("quit\r\n");
                reader.close();
                writer.close();

            } catch (Exception e) {
                System.err.println(e.getMessage());
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

    public static void readFirstLine(BufferedReader reader) {
        int count = 0;
        try {
            for (int c = reader.read(); c != -1; c = reader.read()) {
                if ((char) c == '\r') {
                    if (count == 2) {
                        break;
                    } else {
                        count++;
                    }
                }
                if (count == 1) {
                    System.out.print((char) c);
                }
            }
            System.out.println();
        } catch (Exception e) {
            System.err.println(e.getMessage());

        }
    }

    public static void readEliza(BufferedReader reader) {
        int count = 0;
        try {
            for (int c = reader.read(); c != -1; c = reader.read()) {
                if ((char) c == '\r') {
                    if (count == 3) {
                        break;
                    } else {
                        count++;
                    }
                }

                if (count == 2) {
                    System.out.print((char) c);
                }
            }
            System.out.println();
        } catch (Exception e) {
            System.err.println(e.getMessage());

        }
    }
}
