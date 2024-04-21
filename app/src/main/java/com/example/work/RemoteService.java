package com.example.work;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class RemoteService extends Service {
    public RemoteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.w("RemoteService", "onBind");
        return (IBinder) iBinder;
    }

    public IMyAidlInterface iBinder = new IMyAidlInterface.Stub() {
        @Override
        public int add(int a, int b) {
            Log.w("RemoteService", "add()" + this.hashCode());
            return a + b;
        }
    };
}