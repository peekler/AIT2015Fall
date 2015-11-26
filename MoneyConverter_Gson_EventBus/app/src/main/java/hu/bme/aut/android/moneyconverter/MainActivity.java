package hu.bme.aut.android.moneyconverter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import de.greenrobot.event.EventBus;
import hu.bme.aut.android.moneyconverter.data.MoneyResult;
import hu.bme.aut.android.moneyconverter.network.HttpAsyncTask;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = (TextView) findViewById(R.id.tvResult);
        Button btnGetRates = (Button) findViewById(R.id.btnGetRates);
        btnGetRates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HttpAsyncTask(getApplicationContext()).execute(
                       "http://api.fixer.io/latest?base=USD"
                );
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(MoneyResult moneyResult) {
        tvResult.setText(
                "HUF: " + moneyResult.getRates().getHUF() + "\n" +
                        "EUR: " + moneyResult.getRates().getEUR());
    }

}
