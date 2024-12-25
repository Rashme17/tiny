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

public class UDietFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_udiet, container, false);

        // Find the TextView from the layout
        TextView textView = view.findViewById(R.id.textView);

        // Add Underweight Diet Recommendations
        String recommendations =
                "1. **Increase calorie intake**: Ensure that you are consuming more calories than you burn by eating energy-dense foods.\n\n" +
                        "2. **Eat nutrient-rich foods**: Focus on foods that provide essential vitamins, minerals, and other nutrients like fruits, vegetables, and whole grains.\n\n" +
                        "3. **Choose healthy fats**: Include foods like avocados, nuts, seeds, and olive oil, as they are calorie-rich and provide healthy fats.\n\n" +
                        "4. **Protein-rich foods**: Incorporate lean meats, fish, eggs, and plant-based proteins like beans, lentils, and tofu to build muscle mass.\n\n" +
                        "5. **Frequent meals**: Eat smaller meals every 3–4 hours to boost calorie intake throughout the day.\n\n" +
                        "6. **Smoothies and shakes**: Consider drinking high-calorie smoothies and shakes made with ingredients like yogurt, fruits, and protein powder.\n\n" +
                        "7. **Avoid empty calories**: While increasing calorie intake, avoid excessive consumption of junk food that offers little nutritional value.\n\n" +
                        "8. **Stay hydrated**: Drink plenty of fluids to stay hydrated, but avoid excessive amounts of caffeinated drinks as they can suppress appetite.\n\n" +
                        "9. **Include dairy products**: Dairy like milk, cheese, and yogurt provides important nutrients and can contribute to your daily calorie intake.\n\n" +
                        "10. **Get enough sleep**: Rest is crucial for overall health, and ensuring good sleep helps with weight gain as it improves appetite regulation.\n";

        // Set the diet recommendations text
        textView.setText(recommendations);

        return view;
    }
}
