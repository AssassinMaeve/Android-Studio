package com.example.Fibonacci;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Enable edge-to-edge display for better UI experience
        setContentView(R.layout.activity_main);

        // Initialize the button for generating the Fibonacci sequence
        Button genBtn = findViewById(R.id.button);

        // Set click listener to handle button click events
        genBtn.setOnClickListener(v -> {
            // Retrieve the user input from the EditText field
            EditText numtxt = findViewById(R.id.editText);

            // Convert input text to an integer value
            int num = Integer.parseInt(numtxt.getText().toString());

            // Create an intent to navigate to SecondActivity
            Intent i = new Intent(MainActivity.this, SecondActivity.class);

            // Pass the user input as an extra in the intent
            i.putExtra("limit", num);

            // Start SecondActivity
            startActivity(i);
        });

        // Adjust layout insets to account for system UI elements
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btnGenerate), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            return insets;
        });
    }
}
