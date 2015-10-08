package hu.ait.android.autocompletedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private String[] countries = new String[] {
            "Sweden", "Switzerland", "Swaziland",
            "Hungary", "United States", "United Kingdom"
    };

    @Bind(R.id.acCountryName) AutoCompleteTextView acCountryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);



        ArrayAdapter<String> countriesAdapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_dropdown_item_1line,
                        countries);

        acCountryName.setAdapter(countriesAdapter);
    }


    public void onClick(View v) {
        if (v.getId() == R.id.btnPress) {

            Toast.makeText(MainActivity.this,
                    "Button pressed",
                    Toast.LENGTH_SHORT).show();
        }
    }


    @OnClick(R.id.btnButter)
    public void butterButtonPressed(Button b) {
        Toast.makeText(MainActivity.this,
                "ButterKnife demo",
                Toast.LENGTH_SHORT).show();
    }

}
