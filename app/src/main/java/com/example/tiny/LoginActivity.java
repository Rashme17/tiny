package com.example.tiny;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private EditText usernameEditText, passwordEditText;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        usernameEditText = findViewById(R.id.emailInput);
        passwordEditText = findViewById(R.id.passwordInput);
        Button loginButton = findViewById(R.id.loginButton);
        Button signupButton = findViewById(R.id.signupButton);

        databaseHelper = new DatabaseHelper(this);

        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (databaseHelper.checkUser(username, password)) {
                int userId = databaseHelper.getUserId(username, password);
                Log.d(TAG, "Login successful for user ID: " + userId);

                // Check if user has child data
                Cursor cursor = databaseHelper.getChildByUserId(userId);
                if (cursor != null && cursor.moveToFirst()) {
                    // Child exists, get BMI data
                    float bmi = cursor.getFloat(cursor.getColumnIndexOrThrow("BMI"));
                    String bmiCategory = categorizeBmi(bmi);

                    // Log the fetched BMI and category for debugging
                    Log.d(TAG, "Fetched BMI: " + bmi + " | Category: " + bmiCategory);

                    // Navigate directly to the corresponding BMI page
                    Intent intent;
                    if ("Overweight".equals(bmiCategory)) {
                        intent = new Intent(LoginActivity.this, OverweightActivity.class);
                    } else if ("Underweight".equals(bmiCategory)) {
                        intent = new Intent(LoginActivity.this, UnderweightActivity.class);
                    } else  {
                        intent = new Intent(LoginActivity.this, NormalweightActivity.class);
                    }

                    // Pass the user ID and BMI category to the next activity
                    intent.putExtra("USER_ID", userId);
                    intent.putExtra("BMI_CATEGORY", bmiCategory);
                    startActivity(intent);
                } else {
                    // No child data, go to ChildDetailsActivity
                    Log.d(TAG, "No child data found, navigating to Child Details");
                    Intent intent = new Intent(LoginActivity.this, ChildDetailsActivity.class);
                    intent.putExtra("USER_ID", userId);
                    startActivity(intent);
                }

                if (cursor != null) {
                    cursor.close();
                }
                finish(); // Optional: Close the login activity to prevent navigating back to it
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });

        signupButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }

    // Categorize BMI into different ranges
    private String categorizeBmi(float bmi) {
        if (bmi < 16) {
            return "Severely Underweight";
        } else if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal weight";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}
