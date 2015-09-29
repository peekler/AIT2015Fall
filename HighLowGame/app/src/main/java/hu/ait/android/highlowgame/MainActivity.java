package hu.ait.android.highlowgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();
    }

    private void setupUI() {
        setupStartButton();
        setupHelpButton();
        setupAboutButton();
    }

    private void setupAboutButton() {
        Button btnAbout = (Button) findViewById(R.id.btnAbout);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "@Peter Ekler, HighLow Game v1.0",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setupHelpButton() {
        Button btnHelp = (Button) findViewById(R.id.btnHelp);
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "Press start to use the game!",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setupStartButton() {
        Button btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStartHighLow = new Intent();
                intentStartHighLow.setClass(MainActivity.this,
                        HighLowActivity.class);
                startActivity(intentStartHighLow);
            }
        });
    }

}
