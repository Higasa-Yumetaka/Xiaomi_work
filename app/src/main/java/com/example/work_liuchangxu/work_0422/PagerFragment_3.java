package com.example.liuchangxu.work_0422;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.liuchangxu.R;

public class PagerFragment_3 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager_3_0422, container, false);
        TextView textView = view.findViewById(R.id.textView_notification);
        textView.setText("Fragment 3");
        return view;
    }
}

