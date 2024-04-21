package com.example.work;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class startService extends Service {

    String TAG = "startService";
    public startService() {
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

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid());
    }

}