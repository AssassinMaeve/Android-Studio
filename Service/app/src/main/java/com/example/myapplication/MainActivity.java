package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.example.myapplication.Service.MyMusicService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Enables edge-to-edge display for the activity
        setContentView(R.layout.activity_main); // Set the UI layout for this activity
    }

    // Method to handle button clicks (play and stop buttons)
    // Note: Button's onClick property in XML should be linked to this method
    public void handleButton(View v) {
        // Create an Intent to start or stop the MyMusicService
        Intent intent = new Intent(this, MyMusicService.class);

        // Check which button was clicked using its ID
        if (v.getId() == R.id.btnPlay) {
            // If Play button is clicked
            Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show(); // Show a small "Play" message
            startService(intent); // Start the music service
        }
        else if (v.getId() == R.id.btnStop) {
            // If Stop button is clicked
            Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show(); // Show a small "Stop" message
            stopService(intent); // Stop the music service
        }
    }
}
