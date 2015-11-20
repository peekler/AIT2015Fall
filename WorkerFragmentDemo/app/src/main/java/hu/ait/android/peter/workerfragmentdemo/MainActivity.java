package hu.ait.android.peter.workerfragmentdemo;

import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentWorker worker = (FragmentWorker)
                getFragmentManager().findFragmentByTag("fragment_worker");

        if (worker == null)
        {
            Toast.makeText(this,"Existing fragment not found.", Toast.LENGTH_LONG).show();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(new FragmentWorker(), "fragment_worker").commit();
        }
        else
        {
            Toast.makeText(this,"Existing fragment found. Last attached: " +
                    ""+worker.getDate(), Toast.LENGTH_LONG).show();
        }

    }



}
