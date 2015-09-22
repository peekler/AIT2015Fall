package hu.ait.android.tictactoe.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import hu.ait.android.tictactoe.MainActivity;
import hu.ait.android.tictactoe.R;
import hu.ait.android.tictactoe.model.TicTacToeModel;

/**
 * Created by peter on 2015. 09. 17..
 */
public class TicTacToeView extends View {

    private Paint paintBg;
    private Paint paintLine;

    private Bitmap backGroundBitmap;


    public TicTacToeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paintBg = new Paint();
        paintBg.setColor(Color.BLACK);
        paintBg.setStyle(Paint.Style.FILL);

        paintLine = new Paint();
        paintLine.setColor(Color.WHITE);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(5);

        backGroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        backGroundBitmap = backGroundBitmap.createScaledBitmap(backGroundBitmap, getWidth(), getHeight(), false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0, 0, getWidth(), getHeight(), paintBg);

        canvas.drawBitmap(backGroundBitmap, 0, 0, null);

        drawGameArea(canvas);

        drawPlayers(canvas);
    }

    private void drawPlayers(Canvas canvas) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (TicTacToeModel.getInstance().getFieldContent(i, j) ==
                        TicTacToeModel.CIRCLE) {

                    // draw a circle at the center of the field

                    // X coordinate: left side of the square + half width of the square
                    float centerX = i * getWidth() / 3 + getWidth() / 6;
                    float centerY = j * getHeight() / 3 + getHeight() / 6;
                    int radius = getHeight() / 6 - 2;

                    canvas.drawCircle(centerX, centerY, radius, paintLine);

                } else if (TicTacToeModel.getInstance().getFieldContent(i, j) ==
                        TicTacToeModel.CROSS) {
                    canvas.drawLine(i * getWidth() / 3, j * getHeight() / 3,
                            (i + 1) * getWidth() / 3,
                            (j + 1) * getHeight() / 3, paintLine);

                    canvas.drawLine((i + 1) * getWidth() / 3, j * getHeight() / 3,
                            i * getWidth() / 3, (j + 1) * getHeight() / 3,
                            paintLine);
                }
            }
        }
    }

    private void drawGameArea(Canvas canvas) {
        // border
        canvas.drawRect(0, 0, getWidth(), getHeight(), paintLine);
        // two horizontal lines
        canvas.drawLine(0, getHeight() / 3, getWidth(), getHeight() / 3,
                paintLine);
        canvas.drawLine(0, 2 * getHeight() / 3, getWidth(),
                2 * getHeight() / 3, paintLine);

        // two vertical lines
        canvas.drawLine(getWidth() / 3, 0, getWidth() / 3, getHeight(),
                paintLine);
        canvas.drawLine(2 * getWidth() / 3, 0, 2 * getWidth() / 3, getHeight(),
                paintLine);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int tX = ((int) event.getX()) / (getWidth() / 3);
            int tY = ((int) event.getY()) / (getHeight() / 3);

            handlePlayerMove(tX, tY);
        }

        return super.onTouchEvent(event);
    }

    private void handlePlayerMove(int tX, int tY) {
        if (tX < 3 && tY < 3 &&
                TicTacToeModel.getInstance().getFieldContent(tX, tY) ==
                        TicTacToeModel.EMPTY) {
            TicTacToeModel.getInstance().setFieldContent(
                    tX, tY, TicTacToeModel.getInstance().getNextPlayer());
            TicTacToeModel.getInstance().changeNextPlayer();
            ((MainActivity) getContext()).showSnackBarMessage(
                    "The next player is: " +
                            ((TicTacToeModel.getInstance().getNextPlayer() ==
                                    TicTacToeModel.CIRCLE) ? "O" : "X")
            );

            invalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int d = w == 0 ? h : h == 0 ? w : w < h ? w : h;
        setMeasuredDimension(d, d);
    }

    public void clearGameArea() {
        TicTacToeModel.getInstance().resetModel();
        invalidate();
    }
}
