package com.example.liuchangxu.work_0421;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class StaticReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
//        Log.d("StaticReceiver", "Received broadcast");
        Toast.makeText(context, "Received broadcast", Toast.LENGTH_SHORT).show();
    }
}