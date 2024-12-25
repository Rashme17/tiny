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

public class OFoodRecommendationFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ofood_recommendation, container, false);
        TextView textView = view.findViewById(R.id.textView);

        // Overweight Food Recommendations
        String recommendations =
                "1. **Leafy greens**: Include plenty of green leafy vegetables like spinach, kale, and lettuce. They are rich in nutrients but low in calories.\n\n" +
                        "2. **Lean proteins**: Include lean protein sources like chicken breast, turkey, fish (especially salmon), and plant-based options like tofu and lentils.\n\n" +
                        "3. **Whole grains**: Swap out refined grains for whole grains like quinoa, brown rice, oats, and whole-wheat pasta for better nutrition and longer satiety.\n\n" +
                        "4. **Berries and citrus fruits**: Include antioxidant-rich fruits like blueberries, strawberries, and citrus fruits such as oranges and grapefruit. They are low in calories and high in fiber.\n\n" +
                        "5. **Avocados**: Though calorie-dense, avocados are packed with healthy fats that help promote satiety, reduce hunger, and support nutrient absorption.\n\n" +
                        "6. **Healthy fats**: Incorporate healthy fats into meals such as olive oil, nuts (like almonds, walnuts), seeds (like chia and flax seeds), and fatty fish.\n\n" +
                        "7. **Legumes and beans**: Beans, lentils, and chickpeas are high in protein and fiber, which help curb appetite and promote digestion.\n\n" +
                        "8. **Greek yogurt**: Choose unsweetened, low-fat Greek yogurt for its high protein content, which can help with satiety and digestion.\n\n" +
                        "9. **Green tea**: Drink green tea, which contains antioxidants and may help in boosting metabolism and fat burning.\n\n" +
                        "10. **Water-rich foods**: Add water-rich foods like cucumbers, tomatoes, and celery to meals. These foods can help promote hydration and fullness with minimal calories.\n";

        // Set the food recommendations text
        textView.setText(recommendations);

        return view;
    }
}
