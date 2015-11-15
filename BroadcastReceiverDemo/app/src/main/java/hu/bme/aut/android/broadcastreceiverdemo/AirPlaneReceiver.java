package hu.bme.aut.android.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by peter on 2015. 11. 09..
 */
public class AirPlaneReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean airPlaneState = intent.getBooleanExtra("state", false);

        Toast.makeText(context, "Airplane state: "+airPlaneState,
                Toast.LENGTH_SHORT).show();
    }
}
