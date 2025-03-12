package com.example.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DivFragment extends Fragment {

    public DivFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Retrieve arguments (numbers) passed from MainActivity
        Bundle bundle = getArguments();
        assert bundle != null; // Ensure the bundle is not null

        double first = bundle.getDouble("FNO"); // Get first number
        double second = bundle.getDouble("SNO"); // Get second number

        // Perform division, ensuring no division by zero
        double div;
        if (second != 0) {
            div = first / second; // Perform division if denominator is non-zero
        } else {
            div = 0; // Set result to 0 if division by zero occurs
        }

        // Inflate the fragment layout
        View v = inflater.inflate(R.layout.fragment_div, container, false);

        // Display the result in the TextView
        TextView result = v.findViewById(R.id.txtdivresult);
        result.setText(String.valueOf(div)); // Convert result to string and display

        return v; // Return the view
    }
}
