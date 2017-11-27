package com.example.sabila.handymind;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.sabila.handymind.shapes.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sabila on 11/15/2017.
 */

public class DrawingView extends View {

    private Paint drawPaint;

    private List<Shape> shapes;
    private Shape touchedShape = null;
    private Shape onCreateShape = null;

    private boolean isSingleTouch = false;

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

        shapes = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        for (int i = 0; i < shapes.size(); i++) {
            shapes.get(i).draw(canvas);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                for (int i = 0; i < shapes.size(); i++) {
                    if (shapes.get(i).isTouched(touchX, touchY)) {
                        touchedShape = shapes.get(i);

                        isSingleTouch = true;

                        Log.i("ACTION_DOWN", "get touched shape");
                    }
                }

                if (touchedShape == null && !isSingleTouch) {
                    Log.i("ACTION_DOWN", "if touched == null");
                    Log.i("ACTION_DOWN", "  add shape");

                    Shape newShape = new Rectangle(touchX, touchY);
                    shapes.add(newShape);
                    onCreateShape = newShape;
                }

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                invalidate();
                break;

            case MotionEvent.ACTION_MOVE:
                Log.i("ACTION_MOVE", "move");

                isSingleTouch = false;

                if (onCreateShape != null) {
                    onCreateShape.drag(touchX, touchY);
                } else if (touchedShape != null) {
                    touchedShape.move(touchX, touchY);
                }

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                onCreateShape = null;

                if (isSingleTouch) {
                    Log.i("ACTION_UP", "clicked");
                    touchedShape.click();
                    isSingleTouch = false;
                } else {
                    touchedShape = null;
                }

                invalidate();
                break;
        }

        return true;
    }
}
