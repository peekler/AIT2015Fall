package hu.bme.aut.android.fragmentdemo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import hu.bme.aut.android.fragmentdemo.IMainFragmentHandler;
import hu.bme.aut.android.fragmentdemo.R;

/**
 * Created by peter on 2015. 11. 16..
 */
public class MainFragment extends Fragment {

    public static String TAG = "MAINFRAGMENT_TAG";

    private IMainFragmentHandler iMainFragmentHandler;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            iMainFragmentHandler =
                    (IMainFragmentHandler) context;
        } catch (ClassCastException e) {
            throw new RuntimeException();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container,
                false);

        Button btnOk = (Button) rootView.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),
                        "Button pressed",
                        Toast.LENGTH_SHORT).show();


                iMainFragmentHandler.showDetails();
            }
        });


        return rootView;
    }
}
