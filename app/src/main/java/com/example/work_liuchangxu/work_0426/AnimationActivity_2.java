package com.example.work_liuchangxu.work_0426;

import static com.example.work_liuchangxu.work_0426.AnimationActivity_2.MyAnimationUtils.startCombinedAnimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.work_liuchangxu.R;

public class AnimationActivity_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_activity_0426);

        View myView = findViewById(R.id.my_view);

        Button btnStart = findViewById(R.id.start_button);

        btnStart.setOnClickListener(v -> startCombinedAnimation(myView));
    }

    public static class MyAnimationUtils {
        private static class MyInterpolator1 implements Interpolator {
            @Override
            public float getInterpolation(float input) {
                return (float) (Math.cos((input + 1) * Math.PI) / 2.0f) + 0.5f;
            }
        }
        public static void startCombinedAnimation(final View view) {
            // 围绕X轴旋转
            ObjectAnimator rotateAnimatorX1 = ObjectAnimator.ofFloat(view, View.ROTATION_X, 0f, 360f);
            rotateAnimatorX1.setDuration(1000); // 持续时间为1000ms
            rotateAnimatorX1.setInterpolator(new MyInterpolator1()); // 使用自定义插值器
            // 围绕y轴旋转
            ObjectAnimator rotateAnimatorY2 = ObjectAnimator.ofFloat(view, View.ROTATION_Y, 0f, -1440f);
            rotateAnimatorY2.setDuration(3000); // 持续时间为1000ms
            rotateAnimatorY2.setInterpolator(new MyInterpolator1()); // 使用自定义插值器
            // 向右移动
            PropertyValuesHolder translationX1 = PropertyValuesHolder.ofFloat(View.TRANSLATION_X, 0, 120f);
            ObjectAnimator translateAnimatorX1 = ObjectAnimator.ofPropertyValuesHolder(view, translationX1);
            translateAnimatorX1.setDuration(1000);
            translateAnimatorX1.setInterpolator(new MyInterpolator1());
            // 向左移动
            PropertyValuesHolder translationX2 = PropertyValuesHolder.ofFloat(View.TRANSLATION_X, 240f, -240f);
            ObjectAnimator translateAnimatorX2 = ObjectAnimator.ofPropertyValuesHolder(view, translationX2);
            translateAnimatorX2.setDuration(3000);
            // 向上移动
            PropertyValuesHolder translationY1 = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 0f, -240f);
            ObjectAnimator translateAnimatorY1 = ObjectAnimator.ofPropertyValuesHolder(view, translationY1);
            translateAnimatorY1.setDuration(3000);
            //向下移动
            PropertyValuesHolder translationY2 = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, -240f, 0f);
            ObjectAnimator translateAnimatorY2 = ObjectAnimator.ofPropertyValuesHolder(view, translationY2);
            translateAnimatorY2.setDuration(3000);
            // 向右移动
            PropertyValuesHolder translationX3 = PropertyValuesHolder.ofFloat(View.TRANSLATION_X, -240, 0f);
            ObjectAnimator translateAnimatorX3 = ObjectAnimator.ofPropertyValuesHolder(view, translationX3);
            translateAnimatorX3.setDuration(1000);
            translateAnimatorX3.setInterpolator(new MyInterpolator1());
            // 从不透明变成0.5
            ObjectAnimator alphaAnimator1 = ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0.5f);
            alphaAnimator1.setDuration(500);
            alphaAnimator1.setInterpolator(new MyInterpolator1());
            // 从0.5变成1
            ObjectAnimator alphaAnimator2 = ObjectAnimator.ofFloat(view, View.ALPHA, 0.5f, 1f);
            alphaAnimator2.setDuration(3000);
            alphaAnimator2.setInterpolator(new MyInterpolator1());
            // 估值器
            ValueAnimator.AnimatorUpdateListener updateListener = animation -> {
                float fraction = animation.getAnimatedFraction();
                float x = 120 * fraction;
                float y = (float) Math.sin(Math.toRadians(fraction * 360)) * 100; // 通过正弦函数计算y轴上的位置
                view.setX(x);
                view.setY(y);
            };
            ValueAnimator customAnimator = ValueAnimator.ofFloat(-1.5f, 1.5f);
            customAnimator.setDuration(3000);
            customAnimator.setInterpolator(new MyInterpolator1());
            customAnimator.addUpdateListener(updateListener);

            AnimatorSet animatorSet = new AnimatorSet();

            AnimatorSet animatorSet1 = new AnimatorSet();

            animatorSet1.playTogether(translateAnimatorX1,alphaAnimator1);

            animatorSet.play(rotateAnimatorX1).before(animatorSet1).with(customAnimator);

            animatorSet.start();

        }
    }

}