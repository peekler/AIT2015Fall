package hu.ait.android.timerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView tvCounter;
    private int counter = 0;
    private Timer timer;

    private class MyCounterTask extends TimerTask {
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvCounter.setText(getString(R.string.txt_ellapsed_time, counter++));
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCounter = (TextView) findViewById(R.id.tvCounter);

        timer = new Timer();
        timer.schedule(new MyCounterTask(), 0, 1000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }
}
