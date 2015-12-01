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
public class FragmentDetails extends Fragment {

    private TextView tvDetailsEur;
    private TextView tvDetailsChf;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        tvDetailsEur = (TextView) rootView.findViewById(R.id.tvDetailsEur);
        tvDetailsChf = (TextView) rootView.findViewById(R.id.tvDetaisChf);

        return rootView;
    }

    public void setDetails(MoneyResult moneyResult) {
        tvDetailsEur.setText("EUR: "+moneyResult.getRates().getEUR());
        tvDetailsChf.setText("CHF: " + moneyResult.getRates().getCHF());
    }
}

