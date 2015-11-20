package hu.bme.aut.android.viewpagerapp;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ViewPager pager = (ViewPager) findViewById(R.id.pager);
    pager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager()));
  }

}
