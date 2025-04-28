package com.example.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SMSReceiver extends BroadcastReceiver {

    // This method is called automatically when an SMS is received
    @Override
    public void onReceive(Context context, Intent intent) {
        // Get the extras (additional data) from the received intent
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            // Retrieve the PDUs (Protocol Data Units) from the bundle
            Object[] pdus = (Object[]) bundle.get("pdus");

            // Loop through all PDUs to process each SMS message
            for (Object pdu : pdus) {
                // Create an SmsMessage object from the raw PDU byte array
                SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu);

                // Get the text content of the SMS
                String msg = sms.getMessageBody();

                // Use regular expression to find a 5-digit OTP in the message
                Matcher m = Pattern.compile("\\b\\d{5}\\b").matcher(msg);

                // If an OTP is found and the OTP EditText is available
                if (m.find() && OTPActivity.txtotp != null) {
                    // Automatically set the extracted OTP into the EditText field
                    OTPActivity.txtotp.setText(m.group(0));
                }
            }
        }
    }
}
