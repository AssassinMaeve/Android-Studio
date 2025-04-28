package com.example.broadcast_receiver;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class OTPActivity extends AppCompatActivity {

    static EditText txtotp;   // Static EditText to allow setting OTP from outside (e.g., via BroadcastReceiver)
    int OTP;                  // Variable to store the generated OTP

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);  // Makes the app fullscreen edge-to-edge
        setContentView(R.layout.activity_otpactivity);

        // Find the OTP EditText field from layout
        txtotp = findViewById(R.id.etOTP);

        // Check if Android version >= Marshmallow (API 23), then request SMS permissions if not already granted
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{
                        Manifest.permission.SEND_SMS,
                        Manifest.permission.RECEIVE_SMS,
                        Manifest.permission.READ_SMS}, 123); // Request codes 123 for permission result
            }
        }

        // Find confirm button and set a click listener
        Button confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When Confirm button is clicked
                EditText etOTP = findViewById(R.id.etOTP);  // Get user input from EditText
                int editOTP = Integer.parseInt(etOTP.getText().toString()); // Convert input to integer

                // Compare user input OTP with generated OTP
                if(OTP == editOTP){
                    Toast.makeText(OTPActivity.this, "Valid OTP", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(OTPActivity.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Get the phone number passed from MainActivity via Intent
        String phno = getIntent().getStringExtra("mobnum");

        // Send OTP SMS to the user's phone number
        sendSMS(phno);
    }

    // Function to generate a random OTP and send it via SMS
    public void sendSMS(String mobilenumber){
        Random r = new Random();
        OTP = 10000 + r.nextInt(20000);  // Generate a random 5-digit OTP between 10000 and 29999
        SmsManager smsmgr = SmsManager.getDefault();
        smsmgr.sendTextMessage(mobilenumber, null, "Your OTP is " + OTP, null, null);  // Send the SMS
    }

    // A public method to programmatically set the OTP into the EditText
    public void putOTP(int otp){
        txtotp.setText(String.valueOf(otp));
    }
}
