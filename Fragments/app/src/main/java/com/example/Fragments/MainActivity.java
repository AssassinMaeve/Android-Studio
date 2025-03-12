package com.example.Fragments;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    // Declare text input fields for user input
    TextInputEditText firsttxt, secondtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize text input fields
        firsttxt = findViewById(R.id.txtFirst);
        secondtxt = findViewById(R.id.txtSecond);

        // Adjust layout padding to account for system UI insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Method to handle button clicks for arithmetic operations
    public void findresult(View v) {
        // Convert user input from text fields to double values
        double firstNo = Double.parseDouble(
                Objects.requireNonNull(firsttxt.getText()).toString());

        double secondNo = Double.parseDouble(
                Objects.requireNonNull(secondtxt.getText()).toString());

        // Create a Bundle to pass the numbers to the fragments
        Bundle bundle = new Bundle();
        bundle.putDouble("FNO", firstNo);
        bundle.putDouble("SNO", secondNo);

        // Get FragmentManager to handle fragment transactions
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        // Check which button was clicked and load the corresponding fragment

        // Addition Fragment
        if (v.getId() == R.id.btnAdd) {
            addFragment addFragment = new addFragment();
            addFragment.setArguments(bundle); // Pass data to fragment
            addFragment oldfragment = (addFragment) manager.findFragmentByTag("ADDFRAG");

            // Remove existing fragment before adding a new one
            if (oldfragment != null) {
                transaction.remove(oldfragment);
            }

            // Add the fragment to the specified container
            transaction.add(R.id.frAddContainer, addFragment, "ADDFRAG");

            // Subtraction Fragment
        } else if (v.getId() == R.id.btnSub) {
            SubtractFragment subtractFragment = new SubtractFragment();
            subtractFragment.setArguments(bundle);
            SubtractFragment oldfragment = (SubtractFragment) manager.findFragmentByTag("SUBFRAG");

            if (oldfragment != null) {
                transaction.remove(oldfragment);
            }

            transaction.add(R.id.frSubContainer, subtractFragment, "SUBFRAG");

            // Multiplication Fragment
        } else if (v.getId() == R.id.btnMul) {
            MulFragment mulFragment = new MulFragment();
            mulFragment.setArguments(bundle);
            MulFragment oldfragment = (MulFragment) manager.findFragmentByTag("MULFRAG");

            if (oldfragment != null) {
                transaction.remove(oldfragment);
            }

            transaction.add(R.id.frMulContainer, mulFragment, "MULFRAG");

            // Division Fragment
        } else if (v.getId() == R.id.btnDiv) {
            DivFragment divFragment = new DivFragment();
            divFragment.setArguments(bundle);
            DivFragment oldfragment = (DivFragment) manager.findFragmentByTag("DIVFRAG");

            if (oldfragment != null) {
                transaction.remove(oldfragment);
            }

            transaction.add(R.id.frDivContainer, divFragment, "DIVFRAG");
        }

        // Commit the transaction to apply the changes
        transaction.commit();
    }
}
