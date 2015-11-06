package hu.bme.aut.android.activityresultdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hu.bme.aut.android.activityresultdemo.data.City;

public class NewCityActivity extends AppCompatActivity {

    public static final String KEY_CITY = "KEY_CITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_city);

        final EditText etCity = (EditText) findViewById(R.id.etCityName);
        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                City newCity = new City(etCity.getText().toString());

                Intent intentResult = new Intent();
                intentResult.putExtra(KEY_CITY,newCity);

                setResult(RESULT_OK, intentResult);
                finish();
            }
        });
    }
}
