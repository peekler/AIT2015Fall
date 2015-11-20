package hu.bme.aut.android.viewpagerdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by peter on 2015. 11. 19..
 */
public class FragmentPersonDetails extends Fragment {

    public static final String TAG = "TAG_DETAILS";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_person_details
                , container, false
        );

        return rootView;
    }
}