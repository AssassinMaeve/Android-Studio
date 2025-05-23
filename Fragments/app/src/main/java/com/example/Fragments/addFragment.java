package com.example.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class addFragment extends Fragment {

    public addFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Retrieve arguments (numbers) passed from MainActivity
        Bundle bundle = getArguments();
        assert bundle != null; // Ensure the bundle is not null

        double first = bundle.getDouble("FNO"); // Get first number
        double second = bundle.getDouble("SNO"); // Get second number
        double sum = first + second; // Perform addition

        // Inflate the fragment layout
        View v = inflater.inflate(R.layout.fragment_addfragment, container, false);

        // Display the result in the TextView
        TextView result = v.findViewById(R.id.txtaddresult);
        result.setText(sum + ""); // Convert result to string and display

        return v; // Return the view
    }
}
