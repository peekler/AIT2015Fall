package hu.bme.aut.android.viewpagerdemo;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PersonPagerAdapter extends FragmentPagerAdapter{

    public PersonPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Main data";
            case 1:
                return "Person details";
            default:
                return "Main data";
        }
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentMainData();
            case 1:
                return new FragmentPersonDetails();
            default:
                return new FragmentMainData();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
