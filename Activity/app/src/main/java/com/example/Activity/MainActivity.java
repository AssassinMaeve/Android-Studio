package com.example.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Enables edge-to-edge UI
        setContentView(R.layout.activity_main); // Sets the layout for the activity

        // Logging message to track activity lifecycle
        Log.d("Lifecycle", "In onCreate Method");

        // Initializing the button from XML layout
        Button b1 = findViewById(R.id.button);

        // Setting an OnClickListener to handle button clicks
        b1.setOnClickListener(v -> {
            // Intent to navigate to SecondActivity when the button is clicked
            Intent i = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(i);
        });

        // Adjusts padding to accommodate system bars (like status and navigation bars)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.textView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Lifecycle method called when the activity becomes visible
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Lifecycle", "In onStart Method");
    }

    // Lifecycle method called when the activity starts interacting with the user
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Lifecycle", "In onResume Method");
    }

    // Lifecycle method called when the activity is partially visible (e.g., another activity is on top)
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Lifecycle", "In onPause Method");
    }

    // Lifecycle method called when the activity is no longer visible
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Lifecycle", "In onStop Method");
    }

    // Lifecycle method called before the activity is destroyed
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Lifecycle", "In onDestroy Method");
    }

    // Lifecycle method called when the activity is restarting after being stopped
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Lifecycle", "In onRestart Method");
    }
}
