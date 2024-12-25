package com.example.tiny.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tiny.R;

// DietaryRecommendationFragment.java

public class DietaryRecommendationFragment extends Fragment {

    private TextView dietaryRecommendationTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dietary_recommendation, container, false);
        dietaryRecommendationTextView = view.findViewById(R.id.dietaryRecommendationTextView);

        // Get BMI passed via the Intent
        Bundle args = getArguments();  // In case you're passing data through bundle
        if (args != null) {
            float bmi = args.getFloat("BMI", -1);  // Default to -1 if BMI is not passed

            if (bmi != -1) {
                // Categorize BMI and show dietary recommendations
                String bmiCategory = categorizeBmi(bmi);
                String recommendation = getDietaryRecommendation(bmiCategory);
                dietaryRecommendationTextView.setText(recommendation);
            } else {
                dietaryRecommendationTextView.setText("BMI data not available.");
            }
        } else {
            dietaryRecommendationTextView.setText("BMI data not available.");
        }

        return view;
    }

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

    private String getDietaryRecommendation(String bmiCategory) {
        if ("Severely Underweight".equals(bmiCategory)) {
            return "Increase calorie intake by consuming higher-calorie, nutrient-rich foods.";
        } else if ("Underweight".equals(bmiCategory)) {
            return "Focus on adding more calories through balanced meals and snacks.";
        } else if ("Normal weight".equals(bmiCategory)) {
            return "Maintain a balanced diet with fruits, vegetables, and proteins.";
        } else if ("Overweight".equals(bmiCategory)) {
            return "Reduce caloric intake with fiber-rich, low-calorie foods.";
        } else if ("Obese".equals(bmiCategory)) {
            return "Aim for weight loss by reducing calorie intake.";
        } else {
            return "BMI data not available.";
        }
    }
}
