
package eiu.cit.netprog;

import java.io.BufferedReader;
import java.io.IOException;   
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class tic_tac_toe_server {
    public final static int PORT = 16;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                try (Socket connection = server.accept()) {
                    Writer out = new OutputStreamWriter(connection.getOutputStream());
                    InputStreamReader in = new InputStreamReader(connection.getInputStream());
                    BufferedReader reader = new BufferedReader(in);

                    char[] celboard = { '-', '-', '-', '-', '-', '-', '-', '-', '-' };
                    returnBoard(out, celboard);

                    String move = reader.readLine();

                    while (!move.equals("quit")) {
                        int cell = Integer.parseInt(move); // chỉ số input ở client
                        if (celboard[cell] != '-') {
                            out.write("Invalid Move" + "\r\n"); // chỉ số ko trống, không khả dụng để chơi
                            out.flush();
                        } else {
                            celboard[cell] = 'X'; // khả dụng, gán X về vị trí input
                            if (!checkDarw(celboard)) { // nếu bảng còn ô trống, thực hiện đánh cho vòng tiếp theo
                                makeMove(celboard);
                                returnBoard(out, celboard);
                                out.write("\r\n");
                                out.flush();
                            } else {
                                returnBoard(out, celboard); // ko còn trống đánh dâu hoàn thành trò chơi
                                out.write("It's a draw");
                                out.write("\r\n");
                                out.flush();
                            }
                        }
                        move = reader.readLine();
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

    public static String displayBoard(char[] celboard) {
        String str = "";

        str += celboard[0] + " | " + celboard[1] + " | " + celboard[2] + "|";
        str += celboard[3] + " | " + celboard[4] + " | " + celboard[5] + "|";
        str += celboard[6] + " | " + celboard[7] + " | " + celboard[8] + "|";

        return str;
    }

    public static void returnBoard(Writer out, char[] celboard) {
        try {
            String str = displayBoard(celboard);
            out.write(str);
            out.write("\r\n");
            out.flush();
        } catch (IOException e) {

        }

    }

    public static void makeMove(char[] celboard) {
        for (int i = 0; i < celboard.length; i++) {
            if (celboard[i] == '-') {
                celboard[i] = 'O';
                break;
            }
        }
    }

    public static boolean checkDarw(char[] celboard) {
        boolean flag = true;

        for (int i = 0; i < celboard.length; i++) {
            if (celboard[i] == ('-')) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
