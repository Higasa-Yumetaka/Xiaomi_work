package com.example.work_liuchangxu.work_0427;

import android.graphics.RectF;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DragDetector extends GestureDetector.SimpleOnGestureListener {
    private static final String TAG = "DragDetector";

    private final ViewGroup mContainer;

    private final GestureDetector mDetector;

    private View mSelectChild;

    private float scale = 1.5f;

    private void selChild(float x, float y) {
        RectF rectF = new RectF();
        for (int i = 0; i < mContainer.getChildCount(); i++) {
            View child = mContainer.getChildAt(i);
            rectF.set(child.getLeft(), child.getTop(), child.getRight(), child.getBottom());
            if (rectF.contains(x, y)) {
                mSelectChild = child;
                scaleChild(mSelectChild);
                break;
            }
        }
    }

    private void scaleChild(View child){
        Animation scaleAnimation = new ScaleAnimation(1, scale, 1, scale, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(25);
        scaleAnimation.setFillAfter(true);
        child.startAnimation(scaleAnimation);
    }

    public DragDetector(ViewGroup container) {
        mContainer = container;
        mDetector = new GestureDetector(container.getContext(), this);
        mContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean ret=mDetector.onTouchEvent(event);
                if (event.getActionMasked() == MotionEvent.ACTION_UP ||
                        event.getActionMasked()==MotionEvent.ACTION_CANCEL) {
                    release(event.getX(), event.getY());
                }
                return ret;
            }
        });
    }

    @Override
    public boolean onDown(@NonNull MotionEvent e){
        Log.w(TAG, "onDown: " + e.getX() + "," + e.getY());
        if(mSelectChild==null){
            selChild(e.getX(),e.getY());
        }
        return mSelectChild != null;
    }

    @Override
    public boolean onScroll(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY){
        Log.w(TAG, "onScroll: " + distanceX + "," + distanceY);
        translateSelChild(distanceX,distanceY);
        return mSelectChild!=null;
    }

    private void translateSelChild(float dx, float dy){
        Log.w(TAG, "translateSelChild: " + dx + "," + dy);
        if(mSelectChild!=null){
            mSelectChild.setTranslationX(mSelectChild.getTranslationX()-(dx / scale));
            mSelectChild.setTranslationY(mSelectChild.getTranslationY()-(dy / scale));
        }
    }

    public void release(float x, float y) {
        Log.w(TAG, "release: " + x + "," + y);
        if (mSelectChild != null) {
            mSelectChild.clearAnimation();
            mSelectChild.setTranslationX(0);
            mSelectChild.setTranslationY(0);
            mSelectChild = null;
        }
    }
}
