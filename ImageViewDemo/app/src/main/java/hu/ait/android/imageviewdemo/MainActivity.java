package hu.ait.android.imageviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView ivHeader;

    private class CountdownThread extends Thread {
        public void run() {
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ivHeader.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivHeader = (ImageView) findViewById(R.id.ivHeader);
        ivHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ivHeader.getVisibility() == ivHeader.VISIBLE) {
                    ivHeader.setVisibility(View.GONE);

                    // start countdown here
                    new CountdownThread().start();

                }
            }
        });
    }
}
