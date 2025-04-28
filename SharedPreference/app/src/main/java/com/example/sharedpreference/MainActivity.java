package com.example.sharedpreference;

import static android.view.Gravity.apply; // (This import is unnecessary — can be removed)

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declare button variables
    Button btnDark, btnLight;

    // Static themeId variable (currently not used)
    static int themeId = android.R.style.ThemeOverlay_Material_Light;

    // SharedPreferences objects to store and edit theme settings
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Initialize SharedPreferences with file name "mypreference" in private mode
        preferences = getSharedPreferences("mypreference", MODE_PRIVATE);
        editor = preferences.edit(); // Get an editor to modify preferences

        // Apply the stored theme based on the saved value
        if (preferences.getBoolean("theme", false)) {
            setTheme(android.R.style.ThemeOverlay_Material_Dark); // Apply dark theme
        } else {
            setTheme(android.R.style.ThemeOverlay_Material_Light); // Apply light theme
        }

        // Important: super.onCreate should be called after setting the theme
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge UI layout
        EdgeToEdge.enable(this);

        // Set the layout file for this activity
        setContentView(R.layout.activity_main);

        // (Note: setContentView is written twice — remove the second one, it's redundant!)
    }

    // Method to handle button clicks for theme selection or moving to second activity
    public void findTheme(View view) {
        if (R.id.btn_Dark == view.getId()) {
            // If Dark button clicked, save "theme" preference as true and apply it
            editor.putBoolean("theme", true).apply();
            recreate(); // Recreate activity to apply new theme immediately
        } else if (R.id.btn_Light == view.getId()) {
            // If Light button clicked, save "theme" preference as false and apply it
            editor.putBoolean("theme", false).apply();
            recreate(); // Recreate activity to apply new theme immediately
        } else if (R.id.btnSecond == view.getId()) {
            // If "Second Activity" button clicked, open Second activity
            Intent i = new Intent(this, Second.class);
            startActivity(i);
        }
    }
}
