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

import com.example.work_liuchangxu.R;

import java.util.ArrayList;
import java.util.List;

public class DragDetector extends GestureDetector.SimpleOnGestureListener {
    private static final String TAG = "DragDetector";

    private boolean isLongPress = false;

    private final ViewGroup mContainer;

    private final GestureDetector mDetector;

    private View mSelectChild;

    private View mHoverChild;

    private int mSelIndex = -1;

    private final float scale = 1.5f;

    public DragDetector(ViewGroup container) {
        mContainer = container;
        mDetector = new GestureDetector(container.getContext(), this);
        mContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean ret = mDetector.onTouchEvent(event);
                if (event.getActionMasked() == MotionEvent.ACTION_UP ||
                        event.getActionMasked() == MotionEvent.ACTION_CANCEL) {
                    release(event.getX(), event.getY());
                }
                return ret;
            }
        });
    }

    private void selChild(float x, float y) {
        RectF rectF = new RectF();
        for (int i = 0; i < mContainer.getChildCount(); i++) {
            View child = mContainer.getChildAt(i);
            rectF.set(child.getLeft(), child.getTop(), child.getRight(), child.getBottom());
            if (rectF.contains(x, y)) {
                mSelectChild = child;
                mSelIndex = i;
                mContainer.setTag(R.id.edit, i);
                scaleChildLarger(mSelectChild, 25);
                break;
            }
        }
    }

    private int findSelChild(float x, float y) {
        RectF rectF = new RectF();
        for (int i = 0; i < mContainer.getChildCount(); i++) {
            View child = mContainer.getChildAt(i);
            rectF.set(child.getLeft(), child.getTop(), child.getRight(), child.getBottom());
            if (rectF.top > y || rectF.right > x && rectF.bottom > y) {
                return i;
            }
        }
        return -1;
    }

    private void scaleChildLarger(View child, int time) {
        Animation scaleAnimation = new ScaleAnimation(1, scale, 1, scale, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(time);
        scaleAnimation.setFillAfter(true);
        child.setElevation(50);
        child.setSelected(true);
        child.startAnimation(scaleAnimation);
    }

    private void scaleChildLarger2(View child, int time) {
        Animation scaleAnimation = new ScaleAnimation(1, scale, 1, scale, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(time);
        scaleAnimation.setFillAfter(true);
        child.setSelected(true);
        child.startAnimation(scaleAnimation);
    }

    private void scaleChildSmaller(View child, int time) {
        Animation scaleAnimation = new ScaleAnimation(scale, 1, scale, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(time);
        scaleAnimation.setFillAfter(true);
        child.setElevation(0);
        child.startAnimation(scaleAnimation);
        child.setSelected(false);
    }

    private void scaleChildSmaller2(View child, int time) {
        Animation scaleAnimation = new ScaleAnimation(scale, 1, scale, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(time);
        scaleAnimation.setFillAfter(true);
        child.startAnimation(scaleAnimation);
        child.setSelected(false);
    }

    @Override
    public boolean onDown(@NonNull MotionEvent e) {
        Log.w(TAG, "onDown: " + e.getX() + "," + e.getY());
        if (mSelectChild == null) {
            selChild(e.getX(), e.getY());
        }
        return mSelectChild != null;
    }

    @Override
    public boolean onScroll(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
        Log.w(TAG, "onScroll: " + distanceX + "," + distanceY);
        translateSelChild(distanceX, distanceY);

        View hoverChild = findHoverChild(e2.getX(), e2.getY());
        if (hoverChild != mHoverChild) {
            if (mHoverChild != null) {
                scaleChildSmaller2(mHoverChild, 25);
            }
            mHoverChild = hoverChild;
            if (mHoverChild != null) {
                scaleChildLarger2(mHoverChild, 25);
            }
        }
        return mSelectChild != null;
    }
    @Override
    public boolean onSingleTapUp(@NonNull MotionEvent e) {
        if (mSelectChild != null) {
            scaleChildSmaller(mSelectChild, 25);
            mSelectChild.setElevation(0);
            mSelectChild = null;
            mSelIndex = -1;
        }
        return true;
    }

    @Override
    public void onLongPress(@NonNull MotionEvent e) {
        if (mSelectChild == null) {
            selChild(e.getX(), e.getY());
        }
        isLongPress = true;
    }

    private View findHoverChild(float x, float y) {
        RectF rectF = new RectF();
        for (int i = 0; i < mContainer.getChildCount(); i++) {
            View child = mContainer.getChildAt(i);
            rectF.set(child.getLeft(), child.getTop(), child.getRight(), child.getBottom());
            if (rectF.contains(x, y)) {
                return child;
            }
        }
        return null;
    }

    private void translateSelChild(float dx, float dy) {
        Log.w(TAG, "translateSelChild: " + dx + "," + dy);
        if (mSelectChild != null) {
            mSelectChild.setTranslationX(mSelectChild.getTranslationX() - dx);
            mSelectChild.setTranslationY(mSelectChild.getTranslationY() - dy);
        }
    }

    public void release(float x, float y) {
        Log.w(TAG, "release: " + x + "," + y);
        if (mSelectChild != null && mSelIndex >= 0) {
            mSelectChild.setTranslationX(0);
            mSelectChild.setTranslationY(0);
            mSelectChild.clearAnimation();
            mContainer.setTag(R.id.edit, null);
            int index = findSelChild(x, y);
            List<View> movedViews = new ArrayList<>();
            if (index != mSelIndex) {
                if (index < mSelIndex) {
                    index++;
                }
                for (int i = Math.min(index, mSelIndex); i < Math.max(index, mSelIndex); i++) {
                    movedViews.add(mContainer.getChildAt(i));
                }
                mContainer.removeViewAt(mSelIndex);
                mContainer.addView(mSelectChild, index);
            }
            mSelectChild.setElevation(0);
            mSelectChild = null;
            mSelIndex = -1;
            for (View view : movedViews) {
                scaleChildSmaller2(view, 300);
            }
        }
        if (mHoverChild != null) {
            scaleChildSmaller2(mHoverChild, 300);
            mHoverChild = null;
        }
    }
}
