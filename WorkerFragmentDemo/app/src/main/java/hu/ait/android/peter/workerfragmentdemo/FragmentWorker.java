package hu.ait.android.peter.workerfragmentdemo;

import android.app.Fragment;
import android.os.Bundle;

import java.util.Date;

/**
 * Created by Peter on 2015.03.18..
 */
public class FragmentWorker  extends Fragment {
    private static final String TAG = FragmentWorker.class.getSimpleName();

    private String date;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        date = new Date(System.currentTimeMillis()).toString();
    }

    public String getDate() {
        return date;
    }
}