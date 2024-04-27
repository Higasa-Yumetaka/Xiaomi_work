package com.example.work_liuchangxu.work_0427;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.work_liuchangxu.R;

import java.util.List;

public class TagCloud extends ViewGroup {

    private final int hMargin = 30;
    private final int vMargin = 30;

    public TagCloud(Context context) {
        super(context);
        addDrag();
    }

    public TagCloud(Context context, android.util.AttributeSet attrs) {
        super(context, attrs);
        addDrag();
    }

    public TagCloud(Context context, android.util.AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addDrag();
    }

    public void addDrag(){
        new DragDetector(this);
    }

    public void setTags(List<String> tags){
        for (String tag:tags) {
            TextView textView = new TextView(getContext());
            textView.setText(" " + tag + " ");
            textView.setTextSize(35);
            textView.setTextColor(0xff000000);
            textView.setBackground(getResources().getDrawable(R.drawable.textview_background_0427));
            addView(textView);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View child;
        int left = hMargin, top = vMargin, maxLineHeight = 0, maxEnd = getMeasuredWidth() - hMargin;
        for (int i = 0; i < getChildCount(); i++) {
            child = getChildAt(i);
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            if (left > hMargin && left + childWidth > maxEnd) {
                left = hMargin;
                top += maxLineHeight + vMargin;
                maxLineHeight = 0;
            }
            maxLineHeight = Math.max(maxLineHeight, childHeight);
            child.layout(left, top, left + childWidth, top + childHeight);
            left += hMargin + childWidth;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int childWidthSpec, childHeightSpec;
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int hSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        int hSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.AT_MOST || widthSpecMode == MeasureSpec.EXACTLY) {
            int chidWidthSize = widthSpecSize - 2 * hMargin;
            childWidthSpec = MeasureSpec.makeMeasureSpec(chidWidthSize, widthSpecMode);
        } else {
            childWidthSpec = widthMeasureSpec;
        }

        if(hSpecMode == MeasureSpec.AT_MOST || hSpecMode == MeasureSpec.EXACTLY){
            childHeightSpec = MeasureSpec.makeMeasureSpec(hSpecSize - vMargin, hSpecMode);
        } else {
            childHeightSpec = heightMeasureSpec;
        }

        measureChildren(childWidthSpec, childHeightSpec);

        if(widthSpecMode == MeasureSpec.AT_MOST || widthSpecMode == MeasureSpec.EXACTLY) {


            View child;
            int left = hMargin, top = vMargin, maxLineHeight = 0, maxEnd = widthSpecSize - hMargin;
            for (int i = 0; i < getChildCount(); i++) {
                child = getChildAt(i);
                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();
                if (left > hMargin && left + childWidth > maxEnd) {
                    left = hMargin;
                    top = maxLineHeight + vMargin + top;
                    maxLineHeight = 0;
                }
                maxLineHeight = Math.max(maxLineHeight, childHeight);
                left += hMargin + childWidth;

            }

            int bottom = top + maxLineHeight + vMargin;
            int measuredHeight = switch (hSpecMode) {
                case MeasureSpec.AT_MOST -> Math.min(hSpecSize, bottom);
                case MeasureSpec.EXACTLY -> hSpecSize;
                default -> bottom;
            };

            setMeasuredDimension(widthSpecSize, measuredHeight);
        }else {
            int measureWith = 2*hMargin, maxLineHeight = 0, childHeight;
            for (int i = 0; i < getChildCount(); i++){
                View child = getChildAt(i);
                measureWith += child.getMeasuredWidth() + hMargin;
                childHeight = child.getMeasuredHeight();
                maxLineHeight = Math.max(maxLineHeight, childHeight);
            }
            int contentHeight = maxLineHeight + 2*vMargin;
            int measureHeight;
            if(hSpecMode == MeasureSpec.AT_MOST){
                measureHeight = Math.min(hSpecSize, contentHeight);
            } else if (hSpecMode == MeasureSpec.EXACTLY){
                measureHeight = hSpecSize;
            } else {
                measureHeight = contentHeight;
            }
            setMeasuredDimension(measureWith, measureHeight);
        }
    }
}
