package com.example.work_liuchangxu.work_0425;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.work_liuchangxu.R;

public class Fragment_self_0425 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_self_layout_0425, container, false);
        TextView textView = view.findViewById(R.id.textView_0425);
        textView.setText("这是我的页面");
        return view;
    }
}
