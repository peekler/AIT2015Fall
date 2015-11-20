package hu.bme.aut.android.fragmentdemo;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import hu.bme.aut.android.fragmentdemo.fragments.DetailsFragment;
import hu.bme.aut.android.fragmentdemo.fragments.MainFragment;
import hu.bme.aut.android.fragmentdemo.fragments.OptionsFragment;
import hu.bme.aut.android.fragmentdemo.fragments.UserNameDialog;

public class MainActivity extends AppCompatActivity
        implements IMainFragmentHandler,
        OptionsFragment.OptionsFragmentInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showFragment(MainFragment.TAG, false);
    }


    public void showFragment(String tag, boolean addToBackStack) {
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

        transaction.setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
        );

        transaction.replace(R.id.layoutContainer, newFragment, tag);


        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
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
                showFragment(MainFragment.TAG, true);
                break;
            case R.id.action_details:
                showFragment(DetailsFragment.TAG, true);
                break;
            case R.id.action_message:
                showMessage("Do you agree?");
                break;
            case R.id.action_select:
                new OptionsFragment().show(getSupportFragmentManager(),
                        OptionsFragment.TAG);
                break;
            default:
                break;
        }

        return true;
    }

    protected void showMessage(String msg) {
        UserNameDialog dialog = new UserNameDialog();

        Bundle b = new Bundle();
        b.putString(UserNameDialog.KEY_MSG, msg);
        dialog.setArguments(b);

        dialog.setCancelable(false);

        dialog.show(getSupportFragmentManager(), UserNameDialog.TAG);
    }

    @Override
    public void showDetails() {
        showFragment(DetailsFragment.TAG, true);
    }

    @Override
    public void onOptionsFragmentResult(String fruit) {
        Toast.makeText(MainActivity.this, "SELECTED fruit: "+fruit,
                Toast.LENGTH_SHORT).show();
    }
}
