package hu.bme.aut.android.moneyconverter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hu.bme.aut.android.moneyconverter.data.MoneyResult;

/**
 * Created by peter on 2015. 12. 01..
 */
public class FragmentMain  extends Fragment {

    private TextView tvMainHuf;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        tvMainHuf = (TextView) rootView.findViewById(R.id.tvMainDataHuf);

        return rootView;
    }

    public void setMainData(MoneyResult moneyResult) {
        tvMainHuf.setText("HUF: "+moneyResult.getRates().getHUF());
    }
}

