package eiu.cit.netprog;

public class Board {
    // Data members
    private char[] board;
    private final static int[][] winners = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 },
            { 2, 5, 8 }, { 0, 4, 8 }, { 2, 4, 6 } };

    // Constructor
    public Board() {
        board = new char[9];
    }

    // Methods
    public char[] getBoard() {
        return board;
    }

    public void setBoard(char[] board) {
        this.board = board;
    }

    public void initialize() {
        for (int i = 0; i < board.length; i++) {
            board[i] = '-';
        }
    }

    public boolean checkMove(int cell) {
        return board[cell] == '-';
    }

    public boolean checkWinner(int[] winner, char player) {
        boolean check = true;
        for (int cell : winner) {
            if (board[cell] != player) {
                check = false;
                break;
            }
        }

        return check;

    }

    public int checkStatus(char player) {
        int status = 0;

        for (int[] winner : winners) {
            if (checkWinner(winner, player)) {
                status = 1;
                break;
            }
        }
        return status;

    }

    public int checkBoard() {
        int status = 1;
        //
        for (char c : board) {
            if (c == '-') {
                status = 0;
                break;
            }
        }
        return status;

    }

    public void updateBoard(int cell) {
        board[cell] = 'o';
        // return board;

    }

    public void makeMove() {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == '-') {
                board[i] = 'x';
                break;
            }
        }

    }

    // encoding the current board
    public String encodeBoard() {
        StringBuilder builder = new StringBuilder();
        for (char c : board) {
            builder.append(c).append('.');
        }
        return builder.toString();

    }

}
