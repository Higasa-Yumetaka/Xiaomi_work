package com.example.work_liuchangxu.work_0421;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.example.work_liuchangxu.IMyAidlInterface;

public class RemoteService extends Service {
    public RemoteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
//        Log.w("RemoteService", "onBind");
        Toast.makeText(this, "onBind() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid(), Toast.LENGTH_SHORT).show();
        return (IBinder) iBinder;
    }

    public IMyAidlInterface iBinder = new IMyAidlInterface.Stub() {
        @Override
        public int add(int a, int b) {
//            Log.w("RemoteService", "add()" + this.hashCode());
            Toast.makeText(RemoteService.this, "add() pid" + android.os.Process.myPid() + " tid " + android.os.Process.myTid(), Toast.LENGTH_SHORT).show();
            return a + b;
        }
    };
}