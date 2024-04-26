package com.example.work_liuchangxu.work_0426;

import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.work_liuchangxu.R;

public class AnimationActivity_1 extends AppCompatActivity {

    private static final String TAG = "MyAnimationActivity";

    int repeatCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.animation_activity_0426);

        final ImageView myView = findViewById(R.id.my_view);

        myView.setImageResource(R.drawable.lanten);

        Button btnStart = findViewById(R.id.start_button);

        // 放大动画
        ScaleAnimation scaleXAnimator = new ScaleAnimation(1f, 1.5f, 1f, 1.5f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleXAnimator.setRepeatCount(2);
        // 旋转动画
        RotateAnimation rotationAnimator = new RotateAnimation(0, -720,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotationAnimator.setRepeatCount(2);
        // 透明度动画
        AlphaAnimation alphaAnimator = new AlphaAnimation(0f, 0.8f);
        alphaAnimator.setRepeatCount(2);
        // 组合动画
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(scaleXAnimator);
        animationSet.addAnimation(rotationAnimator);
        animationSet.addAnimation(alphaAnimator);
        animationSet.setDuration(2000);

        scaleXAnimator.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d(TAG, "onAnimationStart");
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d(TAG, "onAnimationEnd");
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.d(TAG, "onAnimationRepeat重复了" + (repeatCount + 1) + " 次");
                repeatCount ++;
            }
        });

        btnStart.setOnClickListener(v -> myView.startAnimation(animationSet));

    }
}
