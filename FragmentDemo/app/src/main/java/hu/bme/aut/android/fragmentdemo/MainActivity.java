package hu.bme.aut.android.fragmentdemo;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showFragment(MainFragment.TAG);
    }


    public void showFragment(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment newFragment = fragmentManager.findFragmentByTag(tag);
        if (newFragment == null) {
            if (tag.equals(MainFragment.TAG)) {
                newFragment = new MainFragment();
            } else if (tag.equals(DetailsFragment.TAG)) {
                newFragment = new DetailsFragment();
            }
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.layoutContainer,newFragment,tag);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_main:
                showFragment(MainFragment.TAG);
                break;
            case R.id.action_details:
                showFragment(DetailsFragment.TAG);
                break;
            default:
                break;
        }

        return true;
    }
}
