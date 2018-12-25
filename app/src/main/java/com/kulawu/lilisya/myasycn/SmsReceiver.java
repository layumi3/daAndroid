package com.kulawu.lilisya.myasycn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {

    final String TAG = SmsReceiver.class.getSimpleName();
    final SmsManager sms = SmsManager.getDefault();

    public SmsReceiver(){

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        final Bundle bundle = intent.getExtras();

        try {
            if(bundle != null){
                final Object[] pduObj = (Object[]) bundle.get("pdus");
                if (pduObj !=null){}
                for (Object aPdusObj : pduObj){
                    SmsMessage currentMessage = getIncomingMessage(aPdusObj, bundle);
                    String senderNum = currentMessage.getDisplayOriginatingAddress();
                    String message = currentMessage.getDisplayMessageBody();
                    Log.d(TAG, "sender: "+senderNum+"; message: "+message);
                    Intent showSmsIntent = new Intent(context, SmsReceiverActivity.class);
                    showSmsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    showSmsIntent.putExtra(SmsReceiverActivity.EXTRA_SMS_NO, senderNum);
                    showSmsIntent.putExtra(SmsReceiverActivity.EXTRA_SMS_MESSAGE, message);
                    context.startActivity(showSmsIntent);
                }
            }else {
                Log.d(TAG, "onReceive: SMS tidak ada, null");
            }
        }catch (Exception e){
            Log.d(TAG, "Exception SMS Receiver "+e);
        }
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private SmsMessage getIncomingMessage(Object aObject, Bundle bundle){
        SmsMessage currentSMS;

        if (Build.VERSION.SDK_INT >=23){
            String format = bundle.getString("format");
            currentSMS = SmsMessage.createFromPdu((byte[]) aObject,format);

        }else {
            currentSMS = SmsMessage.createFromPdu((byte[]) aObject);
        }
        return currentSMS;
    }
}
