package com.example.dialog_demo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText datetxt, timetxt;
    Button aadbtn;
    Calendar cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        datetxt = findViewById(R.id.txtDate);
        timetxt = findViewById(R.id.txtTime);
        aadbtn = findViewById(R.id.button);
        cal = Calendar.getInstance();

        // Date Picker for Date EditText
        datetxt.setOnClickListener(v -> {
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                    (view, year1, month1, dayOfMonth) -> {
                        // Set the selected date
                        datetxt.setText(dayOfMonth + "/" + (month1 + 1) + "/" + year1);
                    }, year, month, day);
            datePickerDialog.show();
        });

        // Time Picker for Time EditText
        timetxt.setOnClickListener(v -> {
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                    (view, hourOfDay, minute1) -> {
                        // Set the selected time
                        timetxt.setText(hourOfDay + ":" + minute1);
                    }, hour, minute, true);
            timePickerDialog.show();
        });

        // Button click to show the selected date and time
        aadbtn.setOnClickListener(v -> {
            String date = datetxt.getText().toString();
            String time = timetxt.getText().toString();

            if (!date.isEmpty() && !time.isEmpty()) {
                // Do something with the selected date and time
                Toast.makeText(MainActivity.this, "Selected Date: " + date + "\nSelected Time: " + time, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Please select both date and time", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
