package com.example.work_liuchangxu.work_0429;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.work_liuchangxu.R;

public class Main0429Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main0429);

        new MyANRWatchDog(5000, 1000).start();

        Button sleepButton1 = findViewById(R.id.sleep_button_4s_0429);
        sleepButton1.setOnClickListener(v -> {
            Log.w("Main0429Activity", "The main thread is about to sleep for the duration: 4000 ms");
            try{
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Button sleepButton2 = findViewById(R.id.sleep_button_8s_0429);
        sleepButton2.setOnClickListener(v -> {
            Log.w("Main0429Activity", "The main thread is about to sleep for the duration: 8000 ms");
            try{
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}