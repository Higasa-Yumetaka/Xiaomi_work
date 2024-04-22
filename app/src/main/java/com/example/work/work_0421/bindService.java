package com.example.work.work_0421;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class bindService extends Service {

    String TAG = "bindService";
    public bindService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid());
        return super.onStartCommand(intent, flags, startId);
    }

    private final MyLocalBinder localBinder = new MyLocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid());
        return localBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid());
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG, "onRebind() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid());
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid());
    }

    class MyLocalBinder extends Binder {
        public bindService getInstance() {
            return bindService.this;
        }
    }

}