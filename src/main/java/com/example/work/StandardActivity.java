package com.example.work;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;



public class StandardActivity extends AppCompatActivity {

    static String TAG = "StandardActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid());
        setContentView(R.layout.activity_standard);

        Button button = findViewById(R.id.button_standard);
        Button btn_bind_service_2 = findViewById(R.id.button_2_bind_service_2);
        Button btn_unbind_service_2 = findViewById(R.id.button_2_unbind_service_2);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(StandardActivity.this, StandardActivity.class);
            startActivity(intent);
        });

        btn_bind_service_2.setOnClickListener(v -> {
            bindService(new Intent(StandardActivity.this, bindService.class), myServiceConnection, BIND_AUTO_CREATE);
        });

        btn_unbind_service_2.setOnClickListener(v -> {
            unbindService(myServiceConnection);
        });

    }

    MainActivity.startServiceConnection myServiceConnection = new MainActivity.startServiceConnection();


    public static class startServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid());
    }
}