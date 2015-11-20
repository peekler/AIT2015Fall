package hu.bme.aut.android.fragmentdemo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hu.bme.aut.android.fragmentdemo.R;

/**
 * Created by peter on 2015. 11. 16..
 */
public class DetailsFragment extends Fragment {

    public static final String TAG = "DETAILSFRAGMENT_TAG";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details,
                container, false);
        return rootView;
    }
}
