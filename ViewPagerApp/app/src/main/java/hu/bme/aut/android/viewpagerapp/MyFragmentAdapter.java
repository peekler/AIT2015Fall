package hu.bme.aut.android.viewpagerapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Peter on 2015.04.02..
 */
public class MyFragmentAdapter extends
        FragmentPagerAdapter {

  public MyFragmentAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override
  public Fragment getItem(int i) {
    switch (i) {
      case 0:
        return new FragmentOne();
      case 1:
        return new FragmentTwo();
      default:
        return new FragmentOne();
    }
  }

  @Override
  public CharSequence getPageTitle(int position) {
    switch (position) {
      case 0:
        return "First page";
      case 1:
        return "Second page";
      default:
        return "First page";
    }
  }

  @Override
  public int getCount() {
    return 2;
  }
}
