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

public class UFoodRecommendationFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_ufood_recommendation, container, false);

        // Find the TextView from the layout
        TextView textView = view.findViewById(R.id.textView);

        // Add Underweight Food Recommendations
        String recommendations =
                "1. **Nutrient-dense foods**: Include foods that are high in calories and rich in vitamins and minerals like whole grains, dairy, and lean proteins.\n\n" +
                        "2. **Healthy fats**: Focus on adding healthy fats such as those from avocado, nuts, seeds, olive oil, and fatty fish to your meals.\n\n" +
                        "3. **Protein-rich foods**: Include foods like eggs, fish, lean meats, beans, lentils, and legumes to help build and repair tissues.\n\n" +
                        "4. **Full-fat dairy products**: Choose whole milk, full-fat yogurt, and cheese to add extra calories and nutrients.\n\n" +
                        "5. **Smoothies and shakes**: Make high-calorie smoothies using ingredients like whole milk, nut butter, fruits, oats, and protein powder to increase calorie intake.\n\n" +
                        "6. **Frequent meals**: Eat five to six small meals a day instead of two or three large meals to ensure a steady intake of calories throughout the day.\n\n" +
                        "7. **Complex carbohydrates**: Include carbs like whole grains, potatoes, brown rice, quinoa, and pasta to boost your calorie consumption.\n\n" +
                        "8. **Nut butters and seeds**: Peanut butter, almond butter, and other nut-based spreads can be used as high-calorie snacks.\n\n" +
                        "9. **Add extra toppings**: Add extra oil, cheese, or cream to dishes like casseroles, salads, and soups to increase calorie intake.\n\n" +
                        "10. **Hydration**: Drink smoothies, milk, and healthy beverages that provide extra calories rather than just water.\n";

        // Set the food recommendations text
        textView.setText(recommendations);

        return view;
    }
}
