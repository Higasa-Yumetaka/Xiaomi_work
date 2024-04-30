package com.example.work_liuchangxu.work_0427;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class ShadowDrawable extends Drawable {
    private final Paint shadowPaint;
    private final int shadowRadius;
    private final int shadowOffsetX;
    private final int shadowOffsetY;

    public ShadowDrawable(int shadowColor, int shadowRadius, int shadowOffsetX, int shadowOffsetY) {
        this.shadowRadius = shadowRadius;
        this.shadowOffsetX = shadowOffsetX;
        this.shadowOffsetY = shadowOffsetY;

        shadowPaint = new Paint();
        shadowPaint.setColor(Color.TRANSPARENT);
        shadowPaint.setShadowLayer(shadowRadius, shadowOffsetX, shadowOffsetY, shadowColor);
        shadowPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.drawRect(bounds, shadowPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        shadowPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        shadowPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}