package com.example.sabila.handymind;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.sabila.handymind.shapes.Rectangle;

/**
 * Created by Sabila on 11/15/2017.
 */

public class DrawingView extends View {

    private Paint drawPaint;
    private Rectangle rect;
    private boolean isTouched = false;
    private static final int STROKE_SIZE = 7;

    public DrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        drawPaint = new Paint();
        drawPaint.setColor(Color.BLACK);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeWidth(STROKE_SIZE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        if (isTouched) {
            canvas.drawRect(rect.getX(), rect.getY(), rect.getWidth()+rect.getX(), rect.getHeight()+rect.getY(), drawPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        isTouched = true;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                rect = new Rectangle(touchX, touchY);
                invalidate();
                break;

            case MotionEvent.ACTION_MOVE:
                if (rect != null) {
                    float width = event.getX() - this.rect.getX();
                    float height = event.getY() - this.rect.getY();

                    if (width > 0 && height > 0) {
                        rect.setWidth(width);
                        rect.setHeight(height);
                    }
                    invalidate();
                }
                break;

            case MotionEvent.ACTION_UP:
                invalidate();
                break;

            default:
                return false;
        }
        return true;
    }

}
