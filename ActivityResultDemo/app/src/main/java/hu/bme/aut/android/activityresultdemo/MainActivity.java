package hu.bme.aut.android.activityresultdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import hu.bme.aut.android.activityresultdemo.data.City;

public class MainActivity extends AppCompatActivity {

    public static final int REQUES_NEW_CITY = 101;
    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvData = (TextView) findViewById(R.id.tvData);

        Button btnNew = (Button) findViewById(R.id.btnNew);
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenNewCity = new Intent();
                intenNewCity.setClass(MainActivity.this, NewCityActivity.class);
                startActivityForResult(intenNewCity,
                        REQUES_NEW_CITY);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == REQUES_NEW_CITY) {
            if (resultCode == RESULT_OK) {
                // save city name and refresh textview
                City newCity = (City) data.getSerializableExtra(
                        NewCityActivity.KEY_CITY);
                tvData.append(newCity.getCityName());

            } else {
                Toast.makeText(MainActivity.this,
                        "New city was cancelled",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
