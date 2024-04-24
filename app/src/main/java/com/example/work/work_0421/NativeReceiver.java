package com.example.work.work_0421;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class NativeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.w("NativeReceiver", "onReceive");
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {
            int networkType = intent.getIntExtra(ConnectivityManager.EXTRA_NETWORK_TYPE, -1);
//            Log.w("NativeReceiver", "Connectivity changed" + networkType);
            Toast.makeText(context, "Connectivity changed" + networkType, Toast.LENGTH_SHORT).show();
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                Log.w("NativeReceiver", "Network connected");
            } else {
                Log.w("NativeReceiver", "Network disconnected");
            }
        }
    }

}