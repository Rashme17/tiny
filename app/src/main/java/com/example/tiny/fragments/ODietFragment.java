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

public class ODietFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_odiet, container, false);

        // Find the TextView from the layout
        TextView textView = view.findViewById(R.id.textView);

        // Add Overweight Diet Recommendations
        String recommendations =
                "1. **Focus on a balanced diet**: Ensure the diet contains a good balance of protein, healthy fats, and complex carbohydrates.\n\n" +
                        "2. **Eat more fruits and vegetables**: Aim to fill half of your plate with fruits and vegetables as they are low in calories and high in nutrients.\n\n" +
                        "3. **Choose whole grains**: Opt for whole grains such as brown rice, whole wheat, oats, and quinoa instead of refined grains.\n\n" +
                        "4. **Limit sugary foods and beverages**: Cut down on high-sugar snacks, sodas, and other sugary beverages.\n\n" +
                        "5. **Include lean proteins**: Choose lean meats like chicken, turkey, or fish, and include plant-based protein sources like beans, lentils, and tofu.\n\n" +
                        "6. **Portion control**: Keep an eye on portion sizes to avoid overeating, even healthy foods.\n\n" +
                        "7. **Avoid fried foods**: Choose baked or grilled dishes instead of those fried in unhealthy oils.\n\n" +
                        "8. **Drink water**: Stay hydrated with water, and try to avoid sugary drinks.\n\n" +
                        "9. **Small, frequent meals**: Instead of three large meals, try eating smaller meals more frequently throughout the day to maintain energy levels.\n\n" +
                        "10. **Regular meals**: Don't skip meals. Eating on a regular schedule helps maintain metabolic balance.\n";

        // Set the diet recommendations text
        textView.setText(recommendations);

        return view;
    }
}
