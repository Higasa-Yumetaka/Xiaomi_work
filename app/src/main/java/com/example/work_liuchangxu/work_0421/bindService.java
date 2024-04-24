package com.example.work_liuchangxu.work_0421;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class bindService extends Service {

    String TAG = "bindService";
    public bindService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "onCreate() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "onStartCommand() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid(), Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    private final MyLocalBinder localBinder = new MyLocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "onBind() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid(), Toast.LENGTH_SHORT).show();
        return localBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "onUnbind() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid(), Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Toast.makeText(this, "onRebind() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid(), Toast.LENGTH_SHORT).show();
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid(), Toast.LENGTH_SHORT).show();
    }

    class MyLocalBinder extends Binder {
        public bindService getInstance() {
            return bindService.this;
        }
    }

}
