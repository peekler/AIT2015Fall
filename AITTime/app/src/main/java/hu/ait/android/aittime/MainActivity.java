package hu.ait.android.aittime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        final TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        Button btnTime = (Button) findViewById(R.id.btnTime);

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = new Date(System.currentTimeMillis()).toString();

                Log.d("TAG_MY", "time is: " + time);

                // change the text of the TextView
                tvTitle.setText(time);

                // POPUP window
                Toast.makeText(MainActivity.this,
                        time,
                        Toast.LENGTH_LONG).show();
            }
        });
    }


}
