package hu.bme.aut.android.fragmentdemo.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by peter on 2015. 11. 19..
 */
public class OptionsFragment extends DialogFragment implements
        DialogInterface.OnClickListener {

    public static final String TAG = "OptionsFragment";

    public interface OptionsFragmentInterface {
        public void onOptionsFragmentResult(String fruit);
    }

    private String[] options = {"Apple", "Orange", "Lemon"};
    private OptionsFragmentInterface optionsFragmentInterface;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            optionsFragmentInterface = (OptionsFragmentInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OptionsFragmentInterface");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Please select");
        builder.setItems(options, this);
        AlertDialog alert = builder.create();

        return alert;
    }

    @Override
    public void onClick(DialogInterface dialog, int choice) {
        optionsFragmentInterface.onOptionsFragmentResult(options[choice]);
    }

}
