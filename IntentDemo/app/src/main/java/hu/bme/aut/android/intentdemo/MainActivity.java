package hu.bme.aut.android.intentdemo;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCall = (Button) findViewById(R.id.btnCall);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intentCall = new Intent(
                            Intent.ACTION_CALL,
                            Uri.parse("tel:+36208225581")
                    );
                    startActivity(intentCall);
                }catch (SecurityException e) {
                    e.printStackTrace();
                }
            }
        });

        final EditText etData = (EditText) findViewById(R.id.etData);
        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSearch = new Intent(Intent.ACTION_WEB_SEARCH);
                intentSearch.putExtra(SearchManager.QUERY,
                        etData.getText().toString());
                startActivity(intentSearch);
            }
        });

        Button btnSendEmail = (Button) findViewById(R.id.btnSendEmail);
        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail(etData.getText().toString());
            }
        });

        Button btnShare = (Button) findViewById(R.id.btnShare);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentShare = new Intent(Intent.ACTION_SEND);
                intentShare.setType("text/plain");
                intentShare.putExtra(Intent.EXTRA_TEXT, etData.getText().toString());
                intentShare.setPackage("com.facebook.katana");
                startActivity(Intent.createChooser(
                        intentShare, "Select share app"));
            }
        });
    }

    private void sendEmail(String message) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL,
                new String[]{"peter.ekler@aut.bme.hu"});
        i.putExtra(Intent.EXTRA_SUBJECT,
                "subject");
        i.putExtra(Intent.EXTRA_TEXT,
                message);
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this,
                    "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
