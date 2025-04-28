package com.example.sharedpreference;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Second extends AppCompatActivity {

    // Declare SharedPreferences object to access stored preferences
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Access the shared preferences file named "mypreference"
        preferences = getSharedPreferences("mypreference", MODE_PRIVATE);

        // Check if the stored "theme" preference is true (dark mode)
        if (preferences.getBoolean("theme", false)) {
            // If true, apply dark theme
            setTheme(android.R.style.ThemeOverlay_Material_Dark);
        } else {
            // If false, apply light theme
            setTheme(android.R.style.ThemeOverlay_Material_Light);
        }

        // Always call super.onCreate() after setting theme
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display (makes UI fit full screen)
        EdgeToEdge.enable(this);

        // Set the layout for this activity
        setContentView(R.layout.activity_second);

        // (Note: setContentView is written twice — the second line is redundant and can be removed.)
    }
}
