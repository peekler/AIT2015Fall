package hu.bme.aut.android.moneyconverter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import de.greenrobot.event.EventBus;
import hu.bme.aut.android.moneyconverter.adapter.MoneyPagesAdapter;
import hu.bme.aut.android.moneyconverter.data.MoneyResult;
import hu.bme.aut.android.moneyconverter.network.HttpAsyncTask;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGetRates = (Button) findViewById(R.id.btnGetRates);
        btnGetRates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HttpAsyncTask(getApplicationContext()).execute(
                        "http://api.fixer.io/latest?base=USD"
                );
            }
        });

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new MoneyPagesAdapter(getSupportFragmentManager()));
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
        String mainFragmentTag = getFragmentTag(R.id.pager, 0); // 0, because it is the index of the first page
        FragmentMain fragmentMain = (FragmentMain) getSupportFragmentManager().findFragmentByTag(mainFragmentTag);
        fragmentMain.setMainData(moneyResult);

        String detailsFragmentTag = getFragmentTag(R.id.pager, 1); // 1, because it is the index of the second page
        FragmentDetails fragmentDetails = (FragmentDetails) getSupportFragmentManager().findFragmentByTag(
                detailsFragmentTag);
        fragmentDetails.setDetails(moneyResult);
    }

    private String getFragmentTag(int viewPagerId, int index) {
        return "android:switcher:" + viewPagerId + ":" + index;
    }
}
