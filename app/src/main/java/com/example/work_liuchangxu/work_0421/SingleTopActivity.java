package com.example.work_liuchangxu.work_0421;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.work_liuchangxu.R;


public class SingleTopActivity extends AppCompatActivity {

    String TAG = "SingleTopActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid() + "hashCode " + this.hashCode());
        setContentView(R.layout.activity_single_top);

        Button button = findViewById(R.id.button_singletop);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(SingleTopActivity.this, SingleTopActivity.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid() + "hashCode " + this.hashCode());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid() + "hashCode " + this.hashCode());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid() + "hashCode " + this.hashCode());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid() + "hashCode " + this.hashCode());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid() + "hashCode " + this.hashCode());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid() + "hashCode " + this.hashCode());
    }
}