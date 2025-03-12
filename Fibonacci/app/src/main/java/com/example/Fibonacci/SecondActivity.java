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

    int[] series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        int limit1 = getIntent().getIntExtra("limit", 1);

        // Ensure limit1 is at least 2 to avoid index issues
        if (limit1 < 2) {
            limit1 = 2;
        }

        series = new int[limit1];
        series[0] = 0;
        series[1] = 1;

        for (int i = 2; i < limit1; i++) {
            series[i] = series[i - 1] + series[i - 2];
        }

        List<Integer> fibList = new ArrayList<>();
        for (int num : series) {
            fibList.add(num);
        }

        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fibList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) ->
                Toast.makeText(SecondActivity.this, "The Position is " + position, Toast.LENGTH_SHORT).show()
        );

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.listView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
