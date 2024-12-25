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

public class UGrowthChartFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout
        View view = inflater.inflate(R.layout.fragment_ugrowth_chart, container, false);

        // Find TextView and set its content
        TextView textView = view.findViewById(R.id.textView);
        textView.setText("Underweight Growth Chart Data");

        // Find ImageView and optionally change the image programmatically if needed
        ImageView imageView = view.findViewById(R.id.growthChartImage);
        // Optionally, set a different image resource programmatically
        // imageView.setImageResource(R.drawable.another_ugrowth_chart);

        return view;
    }
}
