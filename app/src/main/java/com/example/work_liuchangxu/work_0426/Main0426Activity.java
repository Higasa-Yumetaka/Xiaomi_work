package com.example.work_liuchangxu.work_0426;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.work_liuchangxu.R;

public class Main0426Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main0426);

        Button button1 = findViewById(R.id.button_goto_animation_activity_1);

        Button button2 = findViewById(R.id.button_goto_animation_activity_2);

        button1.setOnClickListener(v -> {
            Intent intent = new Intent(Main0426Activity.this, AnimationActivity_1.class);
            startActivity(intent);
        });

        button2.setOnClickListener(v -> {
            Intent intent = new Intent(Main0426Activity.this, AnimationActivity_2.class);
            startActivity(intent);
        });

    }
}