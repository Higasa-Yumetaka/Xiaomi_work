package com.example.work_liuchangxu.work_0427;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class CustomEditView2 extends androidx.appcompat.widget.AppCompatEditText {

    private final Paint mPaint = new Paint();
    private final Rect mRect = new Rect();

    private final Paint mFPaint = new Paint();


    public CustomEditView2(Context context) {
        super(context);
        initPaint();
    }

    public CustomEditView2(Context context, android.util.AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CustomEditView2(Context context, android.util.AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int count = getLineCount();

        Rect rect = mRect;
        Paint paint = mPaint;
        for (int i = 0;i<count;i++){
            int baseLine = getLineBounds(i, rect);
            canvas.drawLine(rect.left, baseLine + 15, rect.right, baseLine + 11, paint);
        }
        if(count > 2){
            getLineBounds(2,rect);
            canvas.drawRect(rect, mFPaint);
        }
        super.onDraw(canvas);




    }

    @Override
    public int getLineCount() {
        return super.getLineCount();
    }

    private void initPaint(){
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2);

        mFPaint.setStyle(Paint.Style.FILL);
        mFPaint.setColor(Color.GREEN);
        mFPaint.setAntiAlias(true);

    }

}
