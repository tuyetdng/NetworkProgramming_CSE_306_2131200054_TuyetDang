package eiu.cit.netprog;

public class BoardRight extends OPPTicTacToeAbstract {

    @Override
    public void makeMove() {
        for (int i = board.length - 1; i >= 0; i--) {
            if (board[i] == '-') {
                board[i] = 'x';
                break;
            }
        }

    }

}
