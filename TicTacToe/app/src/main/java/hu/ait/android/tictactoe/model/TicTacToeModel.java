package hu.ait.android.tictactoe.model;

/**
 * Created by peter on 2015. 09. 21..
 */
public class TicTacToeModel {

    private static TicTacToeModel instance = null;

    private TicTacToeModel () {
    }

    public static TicTacToeModel getInstance() {
        if (instance == null) {
            instance = new TicTacToeModel();
        }
        return instance;
    }

    public static final short EMPTY = 0;
    public static final short CIRCLE = 1;
    public static final short CROSS = 2;
    public static final short NO_WINNER_YET = -1;

    private short[][] model = {
            { EMPTY, EMPTY, EMPTY },
            { EMPTY, EMPTY, EMPTY },
            { EMPTY, EMPTY, EMPTY }
    };
    private short nextPlayer = CIRCLE;

    public void resetModel() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                model[i][j] = EMPTY;
            }
        }
        nextPlayer = CIRCLE;
    }

    public short getFieldContent(int x, int y) {
        return model[x][y];
    }

    public short setFieldContent(int x, int y, short content) {
        return model[x][y] = content;
    }

    public short getNextPlayer() {
        return nextPlayer;
    }

    public void changeNextPlayer() {
        nextPlayer = (nextPlayer == CIRCLE) ? CROSS : CIRCLE;

        // SAME
        /*if (nextPlayer == CIRCLE) {
            nextPlayer = CROSS;
        } else  {
            nextPlayer = CIRCLE;
        }*/

    }

    public int getWinner() {
        boolean row1_O = model[0][0] == CIRCLE && model[1][0] == CIRCLE
                && model[2][0] == CIRCLE;
        boolean row2_O = model[0][1] == CIRCLE && model[1][1] == CIRCLE
                && model[2][1] == CIRCLE;
        boolean row3_O = model[0][2] == CIRCLE && model[1][2] == CIRCLE
                && model[2][2] == CIRCLE;
        boolean col1_O = model[0][0] == CIRCLE && model[0][1] == CIRCLE
                && model[0][2] == CIRCLE;
        boolean col2_O = model[1][0] == CIRCLE && model[1][1] == CIRCLE
                && model[1][2] == CIRCLE;
        boolean col3_O = model[2][0] == CIRCLE && model[2][1] == CIRCLE
                && model[2][2] == CIRCLE;
        boolean diag1_O = model[0][0] == CIRCLE && model[1][1] == CIRCLE
                && model[2][2] == CIRCLE;
        boolean diag2_O = model[2][0] == CIRCLE && model[1][1] == CIRCLE
                && model[0][2] == CIRCLE;

        boolean row1_X = model[0][0] == CROSS && model[1][0] == CROSS
                && model[2][0] == CROSS;
        boolean row2_X = model[0][1] == CROSS && model[1][1] == CROSS
                && model[2][1] == CROSS;
        boolean row3_X = model[0][2] == CROSS && model[1][2] == CROSS
                && model[2][2] == CROSS;
        boolean col1_X = model[0][0] == CROSS && model[0][1] == CROSS
                && model[0][2] == CROSS;
        boolean col2_X = model[1][0] == CROSS && model[1][1] == CROSS
                && model[1][2] == CROSS;
        boolean col3_X = model[2][0] == CROSS && model[2][1] == CROSS
                && model[2][2] == CROSS;
        boolean diag1_X = model[0][0] == CROSS && model[1][1] == CROSS
                && model[2][2] == CROSS;
        boolean diag2_X = model[2][0] == CROSS && model[1][1] == CROSS
                && model[0][2] == CROSS;

        if (row1_O || row2_O || row3_O || col1_O || col2_O || col3_O || diag1_O
                || diag2_O)
            return CIRCLE;
        else if (row1_X || row2_X || row3_X || col1_X || col2_X || col3_X
                || diag1_X || diag2_X)
            return CROSS;
        else // check if draw
        {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (model[i][j] == 0)
                        return NO_WINNER_YET;
                }
            }

            return EMPTY;
        }
    }

}