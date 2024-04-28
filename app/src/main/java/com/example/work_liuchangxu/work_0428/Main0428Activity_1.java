package com.example.work_liuchangxu.work_0428;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.work_liuchangxu.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Main0428Activity_1 extends AppCompatActivity {

    private final OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
    private static final long COUNTDOWN_TIME_MILLIS = 60 * 1000; // 60秒
    private static final int MSG_UPDATE_TIME = 1;
    private long startTimeMillis;
    private Button countdownButton;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main04281);

        Toast.makeText(Main0428Activity_1.this, "注意！真的可以发送验证码，请不要使用真实手机号", Toast.LENGTH_SHORT).show();

        EditText editText = findViewById(R.id.login_phone);

        countdownButton = findViewById(R.id.countdown_button);

        handler = new Handler(getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == MSG_UPDATE_TIME) {
                    updateCountdownUI((int) msg.obj);
                }
            }
        };
        countdownButton.setOnClickListener(v -> {
            String phone = editText.getText().toString();
            if (phone.length() != 11) {
                Toast.makeText(Main0428Activity_1.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                return;
            }
            countdownButton.setEnabled(false);
            startCountdownThread();
            post(phone);
        });
    }

    private void startCountdownThread() {
        new Thread(() -> {
            startTimeMillis = SystemClock.elapsedRealtime();
            long elapsedTimeMillis;
            long remainingTimeMillis;
            while (true) {
                elapsedTimeMillis = SystemClock.elapsedRealtime() - startTimeMillis;
                remainingTimeMillis = COUNTDOWN_TIME_MILLIS - elapsedTimeMillis;
                if (remainingTimeMillis > 0) {
                    int remainingSeconds = (int) (remainingTimeMillis / 1000);
                    handler.sendMessage(handler.obtainMessage(MSG_UPDATE_TIME, remainingSeconds));
                    SystemClock.sleep(1000);
                } else {
                    handler.sendEmptyMessage(MSG_UPDATE_TIME);
                    break;
                }
            }
        }).start();
    }

    private void updateCountdownUI(int remainingSeconds) {
        if (remainingSeconds > 0) {
            countdownButton.setText(getString(R.string.countdown_format, remainingSeconds));
        } else {
            countdownButton.setText(getString(R.string.countdown_finished));
            countdownButton.setEnabled(true);
        }
    }

    private void post(String phone) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("phone", phone);
        String json = new Gson().toJson(hashMap);
        RequestBody requestBody = RequestBody.create(json, okhttp3.MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .post(requestBody)
                .url("https://hotfix-service-prod.g.mi.com/quick-game/api/auth/sendCode")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() -> Toast.makeText(Main0428Activity_1.this, "发送失败", Toast.LENGTH_SHORT).show());
                Log.w("Main0428Activity", "login: " + e.getMessage());
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                runOnUiThread(() -> {
                    try {
                        if (response.body() != null) {
                            if (response.isSuccessful()) {
                                Toast.makeText(Main0428Activity_1.this, "发送成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Main0428Activity_1.this, "发送失败", Toast.LENGTH_SHORT).show();
                                Message message = new Message();
                                message.what = 1;
                                message.obj = response.body().string();
                                handler.sendMessage(message);
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        });
    }
}
