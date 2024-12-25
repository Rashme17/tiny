package com.example.tiny.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import com.example.tiny.R;

public class OGrowthChartFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout
        View view = inflater.inflate(R.layout.fragment_ogrowth_chart, container, false);

        // Find TextView and set the content
        TextView textView = view.findViewById(R.id.textView);
        textView.setText("Overweight Growth Chart Data");

        // Find ImageView and optionally set the image programmatically
        ImageView imageView = view.findViewById(R.id.growthChartImage);
        // Optionally, you can set a different image programmatically if needed
        // imageView.setImageResource(R.drawable.another_ogrowth_chart);

        return view;
    }
}
