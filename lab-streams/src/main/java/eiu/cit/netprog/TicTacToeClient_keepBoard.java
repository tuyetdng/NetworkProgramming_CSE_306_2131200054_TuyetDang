package eiu.cit.netprog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.ParseException;

public class TicTacToeClient_keepBoard {
    public static void main(String[] args) throws ParseException {
        String hostname = "localhost";
        Socket socket = null;
        OPPTicTacToeAbstract board;
        String start = args[0];

        if (start.equals("left")) {
            board = new BoardLeft();
        } else {
            board = new BoardRight();
        }
        board.initialize();
        while (true) {
            try {
                socket = new Socket(hostname, 17);
                socket.setSoTimeout(15000);
                InputStream in = socket.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                BufferedReader bif = new BufferedReader(reader);
                OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
                BufferedWriter bout = new BufferedWriter(out);

                // Enter data using BufferReader
                BufferedReader terminal = new BufferedReader(new InputStreamReader(System.in));
                // String previousBoard = terminal.readLine();
                bout.write(args[0] + "\r\n");
                bout.flush();

                String move = terminal.readLine();
                bout.write(board.encodeBoard() + "\r\n");
                bout.flush();

                while (!(move.equals("quit"))) {
                    bout.write(board.encodeBoard() + "\r\n");
                    bout.flush();
                    bout.write(move + "\r\n");
                    bout.flush();
                    readBoard(bif);

                    move = terminal.readLine();
                    bout.write("quit" + "\r\n");
                    bout.flush();
                    // socket.close();
                }
                return;

            } catch (IOException ex) {
                System.err.println(ex);
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException ex) {
                        System.out.println("WHY");
                    }
                }
            }
        }
    }

    static void readBoard(BufferedReader bif) {
        try {
            String encodedBoard = bif.readLine();

            System.out.println(encodedBoard);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // static String sendBoardToServerByString () throws IOException {
    // StringBuilder builder = new StringBuilder();
    // for (char c : board) {
    // builder.append(c).append('.');
    // }
    // return builder.toString();

    // }

    static String sendBoarString(char[] boardTest) {
        StringBuilder builder = new StringBuilder();
        for (char c : boardTest) {
            builder.append(c);
        }
        return builder.toString();
    }

    static char[] getBoardFromString(String str) {
        char[] board = new char[9];
        int idx = 0;

        for (int i = 0; i < str.length(); i++) {
            board[i] = str.charAt(idx++);
        }
        return board;

    }

}