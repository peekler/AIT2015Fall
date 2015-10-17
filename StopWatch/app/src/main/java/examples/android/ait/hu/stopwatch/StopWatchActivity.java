package examples.android.ait.hu.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class StopWatchActivity extends Activity {

    private TextView tvRunTime;
    private Timer timerStopWatch;
    private long startTime = 0;

    private class RunTimeTimerTask extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    long currentTime = System.currentTimeMillis();
                    long diff = currentTime - startTime;
                    long sec = diff / 1000;
                    long msec = (diff % 1000) / 10;

                    tvRunTime.setText(
                            getString(R.string.txt_run_time,
                                    sec, msec));
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        final LinearLayout container = (LinearLayout) findViewById(
                R.id.layoutContainer);

        tvRunTime = (TextView) findViewById(R.id.tvRunTime);

        Button btnStart = (Button) findViewById(R.id.btnStart);
        final Button btnPick = (Button) findViewById(R.id.btnPick);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTime = System.currentTimeMillis();
                btnPick.setEnabled(true);
                tvRunTime.setVisibility(View.VISIBLE);

                if (timerStopWatch != null) {
                    timerStopWatch.cancel();
                }

                container.removeAllViews();
                timerStopWatch = new Timer();
                timerStopWatch.schedule(new RunTimeTimerTask(), 0, 10);
            }
        });

        btnPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long diff = System.currentTimeMillis() - startTime;
                long diffInSec = diff / 1000;
                long diffMsMod = diff % 1000;

                LayoutInflater inflater = (LayoutInflater) getSystemService(
                        LAYOUT_INFLATER_SERVICE);

                View lineTime = inflater.inflate(
                        R.layout.line_time, null, false);

                TextView tvTime = (TextView) lineTime.findViewById(R.id.tvTime);
                tvTime.setTextSize(23);
                tvTime.setText(
                        getString(R.string.txt_run_time,
                                diffInSec, diffMsMod));

                container.addView(lineTime);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (timerStopWatch != null) {
            timerStopWatch.cancel();
        }
    }
}
