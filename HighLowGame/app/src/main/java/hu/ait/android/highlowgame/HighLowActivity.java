package hu.ait.android.highlowgame;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class HighLowActivity extends AppCompatActivity {

    public static final String KEY_GENERATED = "KEY_GENERATED";
    public static final String KEY_DATA_TEXT = "KEY_DATA_TEXT";
    private int generatedNum = 0;
    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_low);

        tvData = (TextView) findViewById(R.id.tvData);

        if (savedInstanceState != null) {
            generatedNum = savedInstanceState.getInt(KEY_GENERATED);
            tvData.setText(savedInstanceState.getString(KEY_DATA_TEXT));
        } else {
            generateNewNumber();
        }

        final EditText etGuess = (EditText) findViewById(R.id.etGuess);
        Button btnGuess = (Button) findViewById(R.id.btnGuess);
        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".equals(etGuess.getText().toString())) {
                    int guess = Integer.parseInt(
                            etGuess.getText().toString());

                    if (guess < generatedNum) {
                        tvData.setText("Your guess is smaller!");
                    } else if (guess > generatedNum) {
                        tvData.setText("Your guess is larger!");
                    } else {
                        tvData.setText("You have won! Yuppeee!");
                        //generateNewNumber();
                        Intent intentShowDialog = new Intent();
                        intentShowDialog.setClass(HighLowActivity.this,
                                DialogResultActivity.class);

                        startActivity(intentShowDialog);
                    }

                } else {
                    etGuess.setError("Please fill this field!");
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(KEY_GENERATED, generatedNum);
        outState.putString(KEY_DATA_TEXT, tvData.getText().toString());
    }

    private void generateNewNumber() {
        Random rand = new Random(System.currentTimeMillis());
        // new random number between 0 and 99
        generatedNum = rand.nextInt(100);
    }
}
