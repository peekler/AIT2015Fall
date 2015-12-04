package hu.bme.aut.android.intenttest;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callPhone(View v){
        /*Intent intentCall = new Intent(Intent.ACTION_CALL,
                Uri.parse("tel:+36304892111"));
        startActivity(intentCall);*/

        /*Intent intentTest = new Intent(Intent.ACTION_WEB_SEARCH);
        intentTest.putExtra(SearchManager.QUERY, "query text");
        startActivity(intentTest);*/

        /*Intent intentTest = new Intent(Intent.ACTION_SEND);
        intentTest.setType("text/plain");
        intentTest.putExtra(Intent.EXTRA_TEXT, "http://www.google.hu/");
        intentTest.setPackage("com.facebook.katana");
        startActivity(Intent.createChooser(intentTest, "Select share app"));*/


    }


    public void customAction(View v) {
        String CUSTOM_ACTION =
                "hu.bme.aut.android.customactionhandler.ACTION_CUSTOM";

        Intent i = new Intent();
        i.setAction(CUSTOM_ACTION);
        i.putExtra("key_data", "demo data");
        startActivity(i);
    }

}
