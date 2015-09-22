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

}