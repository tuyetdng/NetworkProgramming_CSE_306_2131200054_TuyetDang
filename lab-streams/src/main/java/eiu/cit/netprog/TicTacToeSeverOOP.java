package eiu.cit.netprog;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TicTacToeSeverOOP {
    private static int PORT = 17;

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                Socket connection = server.accept();
                TicTacToeServer_MultiThreads multiThreads = new TicTacToeServer_MultiThreads(connection);
                multiThreads.start();
            }
        }

    }

}
