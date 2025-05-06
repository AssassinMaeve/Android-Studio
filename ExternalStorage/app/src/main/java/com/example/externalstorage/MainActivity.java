package com.example.externalstorage;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    // Name of the file to be saved/read
    private static final String FILE_NAME = "exampleExternal.txt";

    // EditText for user input
    EditText editTextExternal;

    // File and folder objects to manage file paths
    File myFile, folder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enables full edge-to-edge UI display
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Link the EditText from XML layout
        editTextExternal = findViewById(R.id.editText);

        // Check for storage permissions and request if not granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 123);
        }
    }

    // Called when "Save" button is clicked
    public void saveDataExternal(View view) {
        // Create a folder named "AIMIT" in the Downloads directory
        folder = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), "AIMIT");

        // Create the folder if it doesn't exist
        if (!folder.exists()) {
            folder.mkdir();
        }

        // Create a file inside the folder
        myFile = new File(folder, FILE_NAME);
        try {
            // Open the file and write data from EditText into it
            FileWriter writer = new FileWriter(myFile);
            writer.write(editTextExternal.getText().toString());
            writer.close();
            Toast.makeText(this, "Data saved to External Storage", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            // Handle exceptions and show error message
            e.printStackTrace();
            Toast.makeText(this, "Error saving file", Toast.LENGTH_SHORT).show();
        }
    }

    // Called when "Load" button is clicked
    public void loadExternalData(View view) {
        // Create a file reference pointing to the same file
        myFile = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS) + "/AIMIT", FILE_NAME);

        // If the file doesn't exist, show a message and exit
        if (!myFile.exists()) {
            Toast.makeText(this, "File does not exist", Toast.LENGTH_SHORT).show();
            return;
        }

        // Read data from the file line-by-line and show it in EditText
        StringBuilder data = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(myFile));
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }
            reader.close();

            // Display the loaded text in EditText
            editTextExternal.setText(data.toString());
        } catch (IOException e) {
            // Handle exceptions and show error message
            e.printStackTrace();
            Toast.makeText(this, "Error reading file", Toast.LENGTH_SHORT).show();
        }
    }
}
