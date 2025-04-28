package com.example.broadcast_receiver;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/*
 MainActivity: Entry screen where the user can enter their mobile number and confirm it.
*/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Makes app content extend into the system bars (for full-screen effect).
        setContentView(R.layout.activity_main); // Sets the layout to activity_main.xml

        // Find the "Send OTP" button from the layout
        Button sendbtn = findViewById(R.id.btnOtp);

        // Set an onClickListener to the button
        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(); // Call the method to show confirmation dialog when button is clicked
            }
        });
    }

    // Method to show the confirmation dialog
    public void showDialog() {
        // Find the EditText where the user enters the mobile number
        final EditText txtnum = findViewById(R.id.etMobNumber);

        // Create an AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Set the title and message of the dialog
        builder.setTitle("Confirm")
                .setMessage(txtnum.getText().toString() + "\nIs this your Number?") // Fixed: "\n" for new line

                // Positive button: User clicks "Confirm"
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // If confirmed, create an Intent to move to OTPActivity
                        Intent intent = new Intent(MainActivity.this, OTPActivity.class);

                        // Attach the mobile number entered by the user to the Intent
                        intent.putExtra("mobnum", txtnum.getText().toString());

                        // Start the OTPActivity
                        startActivity(intent);
                    }
                })

                // Negative button: User clicks "Cancel"
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // If cancelled, simply close the dialog
                        dialog.cancel();
                    }
                });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
