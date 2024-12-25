package com.example.tiny.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.tiny.R;

public class NFoodRecommendationFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_nfood_recommendation, container, false);

        // Find the TextView from the layout
        TextView textView = view.findViewById(R.id.textView);

        // Add Normal Weight Food Recommendations
        String foodRecommendations =
                "1. **Whole Grains**: Incorporate whole grains like brown rice, oats, quinoa, and whole wheat bread into your meals for sustained energy.\n\n" +
                        "2. **Lean Proteins**: Add lean protein sources such as chicken, turkey, tofu, eggs, and fish for muscle repair and growth.\n\n" +
                        "3. **Healthy Fats**: Include healthy fats like avocados, nuts, seeds, and olive oil in your diet to support heart health.\n\n" +
                        "4. **Fruits and Vegetables**: Aim to fill half of your plate with colorful fruits and vegetables to boost vitamins, minerals, and fiber.\n\n" +
                        "5. **Dairy Products**: Choose low-fat or fat-free dairy options like yogurt and milk for calcium and vitamin D.\n\n" +
                        "6. **Hydration**: Drink plenty of water to stay hydrated, and try to limit sugary drinks and sodas.\n\n" +
                        "7. **Legumes and Beans**: Incorporate beans, lentils, and chickpeas into meals for plant-based protein and fiber.\n\n" +
                        "8. **Balance Your Plate**: For each meal, aim for a balanced portion of protein, complex carbohydrates, and healthy fats.\n\n" +
                        "9. **Limit Processed Foods**: Minimize highly processed foods that are high in sodium, sugar, and unhealthy fats.\n\n" +
                        "10. **Moderate Portions**: Keep track of portion sizes to ensure you're eating in moderation and maintaining a healthy calorie balance.\n";

        // Set the food recommendations text
        textView.setText(foodRecommendations);

        return view;
    }
}
