package hu.bme.aut.android.multiactivitydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_USERNAME = "KEY_USERNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etName = (EditText) findViewById(R.id.etName);
        Button btnStart = (Button) findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".equals(etName.getText().toString())) {
                    // Start new Activity
                    Intent startSec = new Intent();
                    startSec.setClass(MainActivity.this,
                            SecondActivity.class);

                    startSec.putExtra(KEY_USERNAME,
                            etName.getText().toString());

                    // Close this Activity
                    // finish();

                    startActivity(startSec);
                } else {
                    etName.setError(getString(R.string.error_empty_name));
                }
            }
        });
    }
}
