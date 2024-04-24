package com.example.work.work_0421;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.work.IMyAidlInterface;
import com.example.work.R;

public class Work0421MainActivity extends AppCompatActivity {

    static String TAG = "MainActivity";

    private NativeReceiver nativeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nativeReceiver = new NativeReceiver();

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
                Intent intent = new Intent(Work0421MainActivity.this, StandardActivity.class);
                startActivity(intent);
            }
            if (v.getId() == R.id.button_2_singletop) {
                Intent intent = new Intent(Work0421MainActivity.this, SingleTopActivity.class);
                startActivity(intent);
            }
            if (v.getId() == R.id.button_2_singletask) {
                Intent intent = new Intent(Work0421MainActivity.this, SingleTaskActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            if (v.getId() == R.id.button_2_singleinstance) {
                Intent intent = new Intent(Work0421MainActivity.this, SingleInstanceActivity.class);
                startActivity(intent);
            }
            if (v.getId() == R.id.button_2_start_service) {
                Intent intent = new Intent(Work0421MainActivity.this, startService.class);
                startService(intent);
            }
            if (v.getId() == R.id.button_2_stop_service) {
                Intent intent = new Intent(Work0421MainActivity.this, startService.class);
                stopService(intent);
            }
            if (v.getId() == R.id.button_2_bind_service_1) {
                bindService(new Intent(Work0421MainActivity.this, bindService.class), myServiceConnection, BIND_AUTO_CREATE);
            }
            if (v.getId() == R.id.button_2_unbind_service_1) {
                unbindService(myServiceConnection);
            }
            if (v.getId() == R.id.send_static_broadcast) {
                bindService(new Intent(Work0421MainActivity.this, RemoteService.class), myRemoteServiceConnection, BIND_AUTO_CREATE);
            }
        }
    }

    startServiceConnection myServiceConnection = new startServiceConnection();

    public static class startServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bindService instance = ((bindService.MyLocalBinder) service).getInstance();
//            Log.i(TAG, "onServiceConnected-"+instance.hashCode());
            Toast.makeText(instance, "onServiceConnected-" + instance.hashCode(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
//            Log.i(TAG, "onServiceDisconnected");
            Toast.makeText(null, "onServiceDisconnected", Toast.LENGTH_SHORT).show();
        }
    }

    MyRemoteServiceConnection myRemoteServiceConnection = new MyRemoteServiceConnection();

    public static class MyRemoteServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IMyAidlInterface instance = IMyAidlInterface.Stub.asInterface(service);
//            Log.w("MainActivity", "Service connected：" + instance.hashCode());
            Toast.makeText(null, "Service connected：" + instance.hashCode(), Toast.LENGTH_SHORT).show();
            try {
//                Log.w("MainActivity", "Service connected add：" + instance.add(2, 3));
                Toast.makeText(null, "Service connected add：" + instance.add(2, 3), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

        @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(nativeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }
}