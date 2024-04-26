package com.example.work_liuchangxu.work_0425;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.work_liuchangxu.R;

public class ShowImageActivity0425 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image0425);

        // 获取extra
        int image = getIntent().getIntExtra("image", 0);
        // 设置image
        ImageView imageView = findViewById(R.id.ActivityImageView0425);
        imageView.setImageResource(image);
    }
}