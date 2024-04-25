package com.example.work_liuchangxu.work_0422;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.work_liuchangxu.R;

public class PagerFragment_2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager_2_0422, container, false);
        TextView textView = view.findViewById(R.id.textView_dashboard);
        textView.setText("Fragment 2");
        return view;
    }
}

