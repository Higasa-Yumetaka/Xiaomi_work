package com.example.work_liuchangxu.class_0425;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.work_liuchangxu.R;

public class Class0425MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class0425_main);

        ImageView imageView = findViewById(R.id.class0425imageView);

        Glide.with(this)
                .load(R.mipmap.ic_launcher_round)
                .into(imageView);
    }
}