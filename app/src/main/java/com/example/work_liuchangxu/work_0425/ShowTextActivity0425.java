package com.example.work_liuchangxu.work_0425;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.work_liuchangxu.R;

public class ShowTextActivity0425 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_text0425);
        //获取extra
        String text = getIntent().getStringExtra("text");
        //设置text
        TextView textView = findViewById(R.id.ActivityTextView0425);
        textView.setText(text);

    }
}