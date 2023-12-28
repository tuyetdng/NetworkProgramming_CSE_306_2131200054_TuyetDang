package eiu.cit.netprog;

public class BoardLeft extends OPPTicTacToeAbstract {
    // Data members
    // Constructor
    @Override
    public void makeMove() {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == '-') {
                board[i] = 'x';
                break;
            }
        }

    }

}
