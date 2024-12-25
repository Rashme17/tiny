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

public class NGrowthChartFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout
        View view = inflater.inflate(R.layout.fragment_ngrowth_chart, container, false);

        // Reference TextView and set its content
        TextView textView = view.findViewById(R.id.textView);
        textView.setText("Normal Weight Growth Chart Data");

        // Reference ImageView and set its resource
        ImageView imageView = view.findViewById(R.id.growthChartImage);
        imageView.setImageResource(R.drawable.ngrowth);  // Set image programmatically, if needed

        return view;
    }
}
