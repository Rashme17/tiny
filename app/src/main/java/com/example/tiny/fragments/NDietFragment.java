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

public class NDietFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_ndiet, container, false);

        // Find the TextView from the layout
        TextView textView = view.findViewById(R.id.textView);

        // Add Normal Weight Diet Recommendations
        String recommendations =
                "1. **Eat a balanced diet**: Ensure your meals contain a mix of carbohydrates, protein, and healthy fats.\n\n" +
                        "2. **Include plenty of vegetables and fruits**: Vegetables and fruits are rich in vitamins, minerals, and fiber. Make half your plate filled with these at each meal.\n\n" +
                        "3. **Include healthy protein sources**: Include proteins like chicken, fish, beans, and nuts.\n\n" +
                        "4. **Whole grains over refined grains**: Choose whole grains like brown rice, whole wheat, oats, and quinoa to improve your overall health.\n\n" +
                        "5. **Control portions**: Even when eating balanced meals, mindful portion control is important to maintain a stable weight.\n\n" +
                        "6. **Eat regular, balanced meals**: Keep your metabolism active by having three balanced meals along with healthy snacks throughout the day.\n\n" +
                        "7. **Stay hydrated**: Drink water throughout the day to stay hydrated and avoid sugary beverages.\n\n" +
                        "8. **Limit processed foods**: Reduce consumption of highly processed foods, junk food, and sugary snacks.\n\n" +
                        "9. **Include healthy fats**: Sources of healthy fats like olive oil, avocado, and nuts should be part of your meals.\n\n" +
                        "10. **Avoid overeating**: Stick to the recommended portion sizes and listen to your hunger cues to avoid overeating.\n";

        // Set the diet recommendations text
        textView.setText(recommendations);

        return view;
    }
}
