package eiu.cit.netprog;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TicTacToeSeverOOP_ClientKeepBoard {
    private static int PORT = 17;

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                Socket connection = server.accept(); // waiting for connection, who wants to play it
                TicTacToeServer_MultiThreads_ClientKeepBoard multiThreads = new TicTacToeServer_MultiThreads_ClientKeepBoard(
                        connection);
                multiThreads.start(); // as machine as connection(no pool) || depends on the number of threads pool
                                      // designed
            }

        }
    }
}
