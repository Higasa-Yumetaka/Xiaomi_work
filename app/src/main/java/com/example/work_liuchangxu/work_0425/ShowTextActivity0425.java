package com.example.work_liuchangxu.work_0425;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.work_liuchangxu.R;

import org.greenrobot.eventbus.EventBus;

public class ShowTextActivity0425 extends AppCompatActivity {

    String text;
    boolean stared;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_text0425);
        //获取extra
        text = getIntent().getStringExtra("text");
        stared = getIntent().getBooleanExtra("stared", false);
        position = getIntent().getIntExtra("position", 0);
        //设置text
        TextView textView = findViewById(R.id.ActivityTextView0425);
        textView.setText(text);

        ImageButton imageButton = findViewById(R.id.star_button_activity_text0425);
        if (stared) {

            imageButton.setAlpha(1.0f);
            imageButton.setColorFilter(getColor(R.color.red));
        } else {
            imageButton.setAlpha(0.3f);
            imageButton.setColorFilter(getColor(R.color.grey));
        }
        imageButton.setOnClickListener(v -> {
            if(stared){
                imageButton.setAlpha(0.3f);
                imageButton.setColorFilter(getColor(R.color.grey));
            }
            else{
                imageButton.setAlpha(1.0f);
                imageButton.setColorFilter(getColor(R.color.red));
            }
            stared = !stared;
            EventBus.getDefault().post(new MyEvent(position, stared));
        });

    }
}