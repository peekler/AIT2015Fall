package demo.android.ait.hu.locktaskdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLockTask = (Button) findViewById(R.id.btnLockTask);
        btnLockTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLockTask();
            }
        });
        Button btnUnlockTask = (Button) findViewById(R.id.btnUnlockTask);
        btnUnlockTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopLockTask();
            }
        });

    }

}
