package com.example.tiny;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ChildDetailsActivity extends AppCompatActivity {
    private static final String TAG = "ChildDetailsActivity";
    private EditText childNameEditText, ageEditText, weightEditText, heightEditText;
    private DatabaseHelper databaseHelper;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_details);

        // Get userId from intent
        userId = getIntent().getIntExtra("USER_ID", -1);
        Log.d(TAG, "Received USER_ID: " + userId);

        if (userId == -1) {
            Toast.makeText(this, "Invalid user session", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Initialize views
        childNameEditText = findViewById(R.id.childNameEditText);
        ageEditText = findViewById(R.id.ageEditText);
        weightEditText = findViewById(R.id.weightEditText);
        heightEditText = findViewById(R.id.heightEditText);
        Button saveButton = findViewById(R.id.saveButton);

        databaseHelper = new DatabaseHelper(this);

        saveButton.setOnClickListener(v -> saveChildDetails());
    }

    private void saveChildDetails() {
        try {
            String childName = childNameEditText.getText().toString().trim();
            String ageStr = ageEditText.getText().toString().trim();
            String weightStr = weightEditText.getText().toString().trim();
            String heightStr = heightEditText.getText().toString().trim();

            Log.d(TAG, "Attempting to save - Name: " + childName +
                    ", Age: " + ageStr +
                    ", Weight: " + weightStr +
                    ", Height: " + heightStr);

            // Validate inputs
            if (childName.isEmpty() || ageStr.isEmpty() || weightStr.isEmpty() || heightStr.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int age = Integer.parseInt(ageStr);
            float weight = Float.parseFloat(weightStr);
            float height = Float.parseFloat(heightStr);

            // Validate ranges
            if (age <= 0 || age > 240) { // 0-240 months
                Toast.makeText(this, "Please enter a valid age (0-240 months)", Toast.LENGTH_SHORT).show();
                return;
            }
            if (weight <= 0 || weight > 150) { // 0-150 kg
                Toast.makeText(this, "Please enter a valid weight (0-150 kg)", Toast.LENGTH_SHORT).show();
                return;
            }
            if (height <= 0 || height > 200) { // 0-200 cm
                Toast.makeText(this, "Please enter a valid height (0-200 cm)", Toast.LENGTH_SHORT).show();
                return;
            }

            // Calculate BMI
            float bmi = calculateBMI(weight, height);

            // Determine BMI Category and navigate accordingly
            String bmiCategory = determineBMICategory(bmi);

            // Save child details
            boolean success = databaseHelper.addChild(userId, childName, age, weight, height);
            Log.d(TAG, "Database save result: " + success);

            if (success) {
                Toast.makeText(this, "Child details saved successfully", Toast.LENGTH_SHORT).show();

                // Navigate to the appropriate activity based on BMI category
                navigateToBMICategoryActivity(bmiCategory);
            } else {
                Toast.makeText(this, "Error saving child details", Toast.LENGTH_SHORT).show();
            }

        } catch (NumberFormatException e) {
            Log.e(TAG, "Number format error: ", e);
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Error saving child details: ", e);
            Toast.makeText(this, "An unexpected error occurred", Toast.LENGTH_SHORT).show();
        }
    }

    private float calculateBMI(float weight, float height) {
        // Convert height from cm to meters
        float heightInMeters = height / 100;
        return weight / (heightInMeters * heightInMeters);
    }

    private String determineBMICategory(float bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Normal weight";
        } else {
            return "Overweight";
        }
    }

    private void navigateToBMICategoryActivity(String bmiCategory) {
        Intent intent;
        switch (bmiCategory) {
            case "Overweight":
                intent = new Intent(this, OverweightActivity.class);
                break;
            case "Underweight":
                intent = new Intent(this, UnderweightActivity.class);
                break;
            case "Normal weight":
            default:
                intent = new Intent(this, NormalweightActivity.class);
                break;
        }
        intent.putExtra("USER_ID", userId); // Pass userId to next activity if needed
        startActivity(intent);
        finish(); // Finish this activity to prevent the user from returning to it
    }
}
