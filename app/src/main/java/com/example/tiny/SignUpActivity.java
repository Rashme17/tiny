package com.example.tiny;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private DatabaseHelper databaseHelper;
    private static final String TAG = "SignUpActivity";  // For logging

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize the views
        usernameEditText = findViewById(R.id.signupUsernameEditText);
        passwordEditText = findViewById(R.id.signupPasswordEditText);
        Button signupButton = findViewById(R.id.confirmSignUpButton);

        // Initialize DatabaseHelper to interact with the database
        databaseHelper = new DatabaseHelper(this);

        // Set up the button click listener to handle sign-up action
        signupButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            // Validate input fields
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Debug Log
            Log.d(TAG, "Attempting to sign up with username: " + username);

            // Call the database helper method to insert the user into the database
            if (databaseHelper.addUser(username, password)) {
                Toast.makeText(this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                // Optionally navigate to login or another activity
                finish();
            } else {
                Toast.makeText(this, "Registration Failed. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
