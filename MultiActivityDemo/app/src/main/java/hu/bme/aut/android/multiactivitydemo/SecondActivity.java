package hu.bme.aut.android.multiactivitydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView tvData = (TextView) findViewById(R.id.tvData);

        if (getIntent().getExtras() != null &&
                getIntent().getExtras().containsKey(
                        MainActivity.KEY_USERNAME)) {

            tvData.setText(getIntent().getExtras().getString(
                    MainActivity.KEY_USERNAME));
        }
    }

}
