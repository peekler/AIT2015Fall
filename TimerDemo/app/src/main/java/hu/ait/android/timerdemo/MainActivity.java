package hu.ait.android.timerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView tvCounter;
    private long startTime = 0;
    private Timer timer;

    private class MyCounterTask extends TimerTask {
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    long currentTime = System.currentTimeMillis();
                    long diff = currentTime - startTime;
                    long sec = diff / 1000;
                    long msec = (diff % 1000) / 10;

                    tvCounter.setText(
                            getString(R.string.txt_elapsed_time,
                                    sec, msec));

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
        startTime = System.currentTimeMillis();
        timer.schedule(new MyCounterTask(), 0, 10);
    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }
}
