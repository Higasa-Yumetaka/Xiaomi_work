package com.example.work_liuchangxu.work_0422;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.work_liuchangxu.R;

public class PagerFragment_1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager_1_0422, container, false);
        TextView textView = view.findViewById(R.id.textView_home);
        textView.setText("Fragment 1");
        return view;
    }
}
