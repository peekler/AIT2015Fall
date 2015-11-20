package hu.bme.aut.android.fragmentdemo.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by peter on 2015. 11. 19..
 */
public class UserNameDialog extends DialogFragment {

    public static final String TAG = "DialogFragmentMessage";
    public static final String KEY_MSG = "KEY_MSG";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String message = getArguments().getString(KEY_MSG);

        AlertDialog.Builder alertDialogBuilder =
                new AlertDialog.Builder(
                getActivity());
        alertDialogBuilder.setTitle("Title");
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        // if the dialog always dismisses then override this
                        // event handler also in the onStart(..)

                    }
                });

        return alertDialogBuilder.create();
    }

}
