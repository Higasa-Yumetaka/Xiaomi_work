package com.example.work_liuchangxu.work_0428;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
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
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Main0428Activity_1 extends AppCompatActivity {

    private final OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
    private EditText phoneEditText;
    private Button countdownButton;
    private int secondsRemaining = 60;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main04281);

        phoneEditText = findViewById(R.id.login_phone);
        countdownButton = findViewById(R.id.countdown_button);

        countdownButton.setOnClickListener(v -> {
            String phone = phoneEditText.getText().toString();
            if (!isValidPhoneNumber(phone)) {
                Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                return;
            }
            countdownButton.setEnabled(false);
            startCountdown();
            sendVerificationCode(phone);
        });

        handler = new Handler(getMainLooper(), msg -> {
            if (msg.what == 1) {
                if (secondsRemaining > 0) {
                    countdownButton.setText(getString(R.string.countdown_format, secondsRemaining));
                    secondsRemaining--;
                    handler.sendEmptyMessageDelayed(1, 1000);
                } else {
                    countdownButton.setText(R.string.countdown_finished);
                    countdownButton.setEnabled(true);
                }
                return true;
            }
            return false;
        });
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // 简单的手机号格式验证
        return phoneNumber.length() == 11 && phoneNumber.matches("\\d{11}");
    }

    private void startCountdown() {
        secondsRemaining = 60;
        handler.sendEmptyMessage(1);
    }

    private void sendVerificationCode(String phone) {
        HashMap<String, String> requestBodyMap = new HashMap<>();
        requestBodyMap.put("phone", phone);
        String json = new Gson().toJson(requestBodyMap);
        RequestBody requestBody = RequestBody.create(json, MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .post(requestBody)
                .url("https://hotfix-service-prod.g.mi.com/quick-game/api/auth/sendCode")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() -> {
                    Toast.makeText(Main0428Activity_1.this, "发送失败", Toast.LENGTH_SHORT).show();
                    Log.e("Main0428Activity", "sendVerificationCode onFailure: " + e.getMessage());
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                runOnUiThread(() -> {
                    if (response.isSuccessful()) {
                        Toast.makeText(Main0428Activity_1.this, "发送成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Main0428Activity_1.this, "发送失败", Toast.LENGTH_SHORT).show();
                        Log.e("Main0428Activity", "sendVerificationCode onResponse: " + response.message());
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
