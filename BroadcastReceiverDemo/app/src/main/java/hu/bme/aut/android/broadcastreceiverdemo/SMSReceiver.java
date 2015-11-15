package hu.bme.aut.android.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by peter on 2015. 11. 09..
 */
public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle extras = intent.getExtras();
        Object[] pdus = (Object[]) extras.get("pdus");
        for (Object pdu : pdus) {
            SmsMessage msg = SmsMessage.createFromPdu((byte[]) pdu);
            String origin = msg.getOriginatingAddress();
            String body = msg.getMessageBody();
            Toast.makeText(context, "SMS catched, number: " + origin + " body: " + body, Toast.LENGTH_LONG).show();
        }

        // does not put message into Inbox
        //abortBroadcast();
    }
}

