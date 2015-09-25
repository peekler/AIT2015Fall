package hu.ait.android.tictactoe;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import hu.ait.android.tictactoe.view.TicTacToeView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout layoutContent;
    private TicTacToeView ticTacToeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutContent = (LinearLayout) findViewById(R.id.layoutContent);

        ticTacToeView =
            (TicTacToeView) findViewById(R.id.gameView);
        Button btnRestart =
                (Button) findViewById(R.id.btnRestart);
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticTacToeView.clearGameArea();

                showSnackBarMessage(
                    getString(R.string.txt_game_restart_message));
            }
        });
    }

    public void showSnackBarMessage(String msg) {
        Snackbar.make(layoutContent,
                msg,
                Snackbar.LENGTH_LONG).show();
    }

    public void showSnackBarMessageWithRestart(String msg) {
        Snackbar.make(layoutContent, msg, Snackbar.LENGTH_LONG).
                setAction(R.string.btn_restart, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ticTacToeView.clearGameArea();
                    }
                }).show();
    }
}
