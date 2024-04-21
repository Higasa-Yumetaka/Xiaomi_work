package com.example.work;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_standard = findViewById(R.id.button_2_standard);
        Button btn_singletop = findViewById(R.id.button_2_singletop);
        Button btn_singletask = findViewById(R.id.button_2_singletask);
        Button btn_singleinstance = findViewById(R.id.button_2_singleinstance);
        Button btn_start_service = findViewById(R.id.button_2_start_service);
        Button btn_stop_service = findViewById(R.id.button_2_stop_service);
        Button btn_bind_service_1 = findViewById(R.id.button_2_bind_service_1);
        Button btn_unbind_service_1 = findViewById(R.id.button_2_unbind_service_1);
        Button btn_send_static_broadcast = findViewById(R.id.send_static_broadcast);

        btn_standard.setOnClickListener(new ClickListener());
        btn_singletop.setOnClickListener(new ClickListener());
        btn_singletask.setOnClickListener(new ClickListener());
        btn_singleinstance.setOnClickListener(new ClickListener());
        btn_start_service.setOnClickListener(new ClickListener());
        btn_stop_service.setOnClickListener(new ClickListener());
        btn_bind_service_1.setOnClickListener(new ClickListener());
        btn_unbind_service_1.setOnClickListener(new ClickListener());
        btn_send_static_broadcast.setOnClickListener(new ClickListener());
    }

    private class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.button_2_standard) {
                Intent intent = new Intent(MainActivity.this, StandardActivity.class);
                startActivity(intent);
            }
            if (v.getId() == R.id.button_2_singletop) {
                Intent intent = new Intent(MainActivity.this, SingleTopActivity.class);
                startActivity(intent);
            }
            if (v.getId() == R.id.button_2_singletask) {
                Intent intent = new Intent(MainActivity.this, SingleTaskActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            if (v.getId() == R.id.button_2_singleinstance) {
                Intent intent = new Intent(MainActivity.this, SingleInstanceActivity.class);
                startActivity(intent);
            }
            if (v.getId() == R.id.button_2_start_service) {
                Intent intent = new Intent(MainActivity.this, startService.class);
                startService(intent);
            }
            if (v.getId() == R.id.button_2_stop_service) {
                Intent intent = new Intent(MainActivity.this, startService.class);
                stopService(intent);
            }
            if (v.getId() == R.id.button_2_bind_service_1) {
                bindService(new Intent(MainActivity.this, bindService.class), myServiceConnection, BIND_AUTO_CREATE);
            }
            if (v.getId() == R.id.button_2_unbind_service_1) {
                unbindService(myServiceConnection);
            }
            if (v.getId() == R.id.send_static_broadcast) {
                Intent intent = new Intent();
                intent.putExtra(Intent.EXTRA_TEXT, "Hello, this is a message from MainActivity");
                intent.setAction("com.example.work.Static_Action");
                intent.setType("text/plain");
                sendBroadcast(intent);
            }
        }
    }

    startServiceConnection myServiceConnection = new startServiceConnection();


    public static class startServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bindService instance = ((bindService.MyLocalBinder) service).getInstance();
            Log.i(TAG, "onServiceConnected-"+instance.hashCode());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected");
        }
    }

}