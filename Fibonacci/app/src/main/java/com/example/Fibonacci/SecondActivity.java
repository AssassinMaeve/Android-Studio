package com.example.Fibonacci;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    int[] series; // Array to store the Fibonacci series

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Enable edge-to-edge display for a better UI experience
        setContentView(R.layout.activity_second);

        // Retrieve the limit value passed from MainActivity
        int limit1 = getIntent().getIntExtra("limit", 1);

        // Ensure the limit is at least 2 to avoid index out-of-bounds issues
        if (limit1 < 2) {
            limit1 = 2;
        }

        // Initialize the Fibonacci series array
        series = new int[limit1];
        series[0] = 0; // First number in Fibonacci sequence
        series[1] = 1; // Second number in Fibonacci sequence

        // Generate Fibonacci numbers up to the specified limit
        for (int i = 2; i < limit1; i++) {
            series[i] = series[i - 1] + series[i - 2];
        }

        // Convert the Fibonacci array into a List for use in an ArrayAdapter
        List<Integer> fibList = new ArrayList<>();
        for (int num : series) {
            fibList.add(num);
        }

        // Initialize the ListView and set an ArrayAdapter to display the Fibonacci series
        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fibList);
        listView.setAdapter(adapter);

        // Set up an item click listener to display the clicked position as a Toast message
        listView.setOnItemClickListener((parent, view, position, id) ->
                Toast.makeText(SecondActivity.this, "The Position is " + position, Toast.LENGTH_SHORT).show()
        );

        // Adjust layout insets to accommodate system UI elements
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.listView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
