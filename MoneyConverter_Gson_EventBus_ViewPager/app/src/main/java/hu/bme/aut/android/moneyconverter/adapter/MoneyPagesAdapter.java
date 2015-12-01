package hu.bme.aut.android.moneyconverter.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import hu.bme.aut.android.moneyconverter.FragmentDetails;
import hu.bme.aut.android.moneyconverter.FragmentMain;

/**
 * Created by peter on 2015. 12. 01..
 */
public class MoneyPagesAdapter extends FragmentPagerAdapter {

    public MoneyPagesAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "HUF";
            case 1:
                return "EUR and CHF";
            default:
                return "HUF";
        }
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentMain();
            case 1:
                return new FragmentDetails();
            default:
                return new FragmentMain();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
