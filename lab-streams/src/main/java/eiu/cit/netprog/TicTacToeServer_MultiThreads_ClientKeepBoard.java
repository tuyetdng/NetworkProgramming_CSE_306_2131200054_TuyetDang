package eiu.cit.netprog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

public class TicTacToeServer_MultiThreads_ClientKeepBoard extends Thread {
    // private String str;
    private Socket connection;
    private OPPTicTacToeAbstract board;

    @Override
    public void run() {
        try {
            play();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public TicTacToeServer_MultiThreads_ClientKeepBoard(Socket connection) {
        this.connection = connection;

    }

    public void play() throws IOException {
        Writer out = new OutputStreamWriter(connection.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String start = in.readLine();
        if (start.equals("left")) {
            board = new BoardLeft();
        } else {
            board = new BoardRight();
        }

        String boardFromClient = in.readLine();

        while (true) {
            boardFromClient = in.readLine();
            String move = in.readLine();
            if (move.equals("quit")) {
                connection.close();
                break;
            } else {

                board.getBoardFromString(boardFromClient);
                
                // get move
                /*
                 * This method returns the numeric value of the character, as a non-negative int
                 * value; -2 if the character has a numeric value that is not a non-negative
                 * integer; -1 if the character has no numeric value.
                 */
                int cell = Character.getNumericValue(move.charAt(0));
                // check that the move is within range
                if (cell >= 0 && cell < 9) {
                    // check that the move is to an empty cell
                    boolean empty = board.checkMove(cell);
                    // System.out.println(empty);
                    if (empty) {
                        /// update board
                        board.updateBoard(cell);
                        // check status for player 'o'
                        // 0. player has not won yet
                        // 1. player won
                        if (board.checkStatus('o') == 0) {
                            if (board.checkBoard() == 0) {
                                board.makeMove();
                                // check status for player 'x'
                                if (board.checkStatus('x') == 0) {
                                    // check status of board
                                    // 0. no draw yet
                                    // 1. draw
                                    if (board.checkBoard() == 0) {
                                        // return new board
                                        out.write(board.encodeBoard() + "\r\n");
                                        out.flush();
                                    } else {
                                        // return new board
                                        out.write(board.encodeBoard() + " *** ");
                                        out.write("It's a draw!" + " *** ");
                                        out.write("Let's play again!" + " *** " + "\r\n");
                                        out.flush();
                                        // board.initialize();
                                    }
                                } else {
                                    // return new board
                                    out.write(board.encodeBoard() + " *** ");
                                    out.write("I won!" + " *** ");
                                    out.write("Let's play again!" + " *** " + "\r\n");
                                    out.flush();
                                    // board.initialize();
                                }

                            } else {
                                // return new board
                                out.write(board.encodeBoard() + " *** ");
                                out.write("It's a draw!" + " *** ");
                                out.write("Let's play again!" + " *** " + "\r\n");
                                out.flush();
                                // board.initialize();
                            }

                        } else {
                            // return new board
                            out.write(board.encodeBoard() + " *** ");
                            out.write("You won!" + " *** ");
                            out.write("Let's play again!" + " *** " + "\r\n");
                            out.flush();
                            // board.initialize();
                        }

                    } else {
                        // return new board
                        out.write("Occupied cell!" + "\r\n");
                        out.flush();
                    }

                } else {
                    // return new board
                    out.write("Wrong input!" + "\r\n");
                    out.flush();
                }
            }

        }

        // connection.close();
    }

}
