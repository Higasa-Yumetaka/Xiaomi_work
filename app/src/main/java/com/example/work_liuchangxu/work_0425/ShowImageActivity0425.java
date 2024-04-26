package com.example.work_liuchangxu.work_0425;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.work_liuchangxu.R;

import org.greenrobot.eventbus.EventBus;

public class ShowImageActivity0425 extends AppCompatActivity {

    int image;
    boolean stared;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image0425); // 设置布局文件

        // 获取extra
        image = getIntent().getIntExtra("image", R.drawable.great_wall);
        stared = getIntent().getBooleanExtra("stared", false);
        position = getIntent().getIntExtra("position", 0);
        // 设置image
        ImageView imageView = findViewById(R.id.ActivityImageView0425);
        Glide.with(this)
                .load(image)
                .into(imageView);

        ImageButton imageButton = findViewById(R.id.star_button_activity_image0425);
        if (stared) {
            imageButton.setAlpha(1.0f);
        } else {
            imageButton.setAlpha(0.3f);
        }
        imageButton.setOnClickListener(v -> {
            if(stared)
                //Toast.makeText(getContext(), "取消收藏", Toast.LENGTH_SHORT).show();
                imageButton.setAlpha(0.3f);
            else
                //Toast.makeText(getContext(), "收藏成功", Toast.LENGTH_SHORT).show();
                imageButton.setAlpha(1.0f);
            stared = !stared;
            EventBus.getDefault().post(new MyEvent(position, stared));
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
