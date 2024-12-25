package com.example.tiny;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

// Import the normal weight-specific fragments
import com.example.tiny.fragments.NGrowthChartFragment;
import com.example.tiny.fragments.NDietFragment;
import com.example.tiny.fragments.NFoodRecommendationFragment;

public class NormalweightActivity extends AppCompatActivity {
    private static final String TAG = "NormalweightActivity"; // Tag for logging
    private BottomNavigationView bottomNav;
    private TextView childDetailsTextView;
    private int currentUserId;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        // Get the user ID from the Intent
        currentUserId = getIntent().getIntExtra("USER_ID", -1);
        if (currentUserId == -1) {
            Toast.makeText(this, "Invalid user ID!", Toast.LENGTH_SHORT).show();
            finish(); // End activity if user ID is not passed
            return;
        }

        // Initialize database helper
        dbHelper = new DatabaseHelper(this);

        // Initialize the TextView and Bottom Navigation
        childDetailsTextView = findViewById(R.id.tvChildDetails);
        bottomNav = findViewById(R.id.bottom_navigation);

        setupBottomNavigation();
        displayChildDetails();

        // Load the default fragment (e.g., Normal Growth Chart)
        loadFragment(new NGrowthChartFragment());
    }

    private void displayChildDetails() {
        // Fetch data for the current user
        Cursor cursor = dbHelper.getChildByUserId(currentUserId);
        if (cursor != null) {
            StringBuilder details = new StringBuilder();
            if (cursor.moveToFirst()) {
                // Fetch column indices
                int nameIndex = cursor.getColumnIndex("ChildName");
                int ageIndex = cursor.getColumnIndex("Age");
                int weightIndex = cursor.getColumnIndex("Weight");
                int heightIndex = cursor.getColumnIndex("Height");
                int bmiIndex = cursor.getColumnIndex("BMI");
                int statusIndex = cursor.getColumnIndex("NutritionStatus");

                if (nameIndex >= 0 && ageIndex >= 0 && weightIndex >= 0 && heightIndex >= 0 && bmiIndex >= 0 && statusIndex >= 0) {
                    // Fetch data from cursor and append to details
                    String name = cursor.getString(nameIndex);
                    int age = cursor.getInt(ageIndex);
                    float weight = cursor.getFloat(weightIndex);
                    float height = cursor.getFloat(heightIndex);
                    float bmi = cursor.getFloat(bmiIndex);
                    String status = cursor.getString(statusIndex);

                    details.append("Name: ").append(name)
                            .append("\nAge: ").append(age)
                            .append("\nWeight: ").append(weight).append(" kg")
                            .append("\nHeight: ").append(height).append(" cm")
                            .append("\nBMI: ").append(bmi)
                            .append("\nStatus: ").append(status)
                            .append("\n\n");
                } else {
                    // Log an error if any of the required columns are missing
                    Log.e(TAG, "One or more columns are missing in the cursor.");
                    details.append("Error: Missing data columns.");
                }
            } else {
                details.append("No data found for this user.");
            }
            // Display child details in the TextView
            childDetailsTextView.setText(details.toString());
            cursor.close();
        } else {
            childDetailsTextView.setText("No child data available.");
        }
    }

    private void setupBottomNavigation() {
        // Setup the BottomNavigationView to navigate between fragments
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            int itemId = item.getItemId();
            if (itemId == R.id.nav_growth) {
                selectedFragment = new NGrowthChartFragment(); // Normal Growth Chart
            } else if (itemId == R.id.nav_diet) {
                selectedFragment = new NDietFragment(); // Normal Dietary Recommendations
            } else if (itemId == R.id.nav_food) {
                selectedFragment = new NFoodRecommendationFragment(); // Normal Food Recommendations
            }

            // Load the selected fragment
            if (selectedFragment != null) {
                loadFragment(selectedFragment);
                return true;
            }
            return false;
        });
    }

    private void loadFragment(Fragment fragment) {
        // Load a fragment into the FragmentContainer
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }
}
