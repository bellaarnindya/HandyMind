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

import com.example.sabila.handymind.shapes.Circle;
import com.example.sabila.handymind.shapes.Line;
import com.example.sabila.handymind.shapes.Rectangle;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Sabila on 11/15/2017.
 */

public class DrawingView extends View {

    private Paint drawPaint;
    private boolean isTouched = false;
    private static final int STROKE_SIZE = 7;
    private String selectedShape;
    public Shape shape;

    public DrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
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
            shape.draw(canvas, drawPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        Log.d(TAG, "onTouchEvent: "+event.getAction());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchDown(touchX, touchY);
                invalidate();
                break;

            case MotionEvent.ACTION_MOVE:
                Log.d("move", "onTouchEvent: ");
                touchMove(event.getX(), event.getY());
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                invalidate();
                break;

            default:
                return false;
        }
        return true;
    }



    public void setSelectedShape(String selectedShape) {
        this.selectedShape = selectedShape;
    }

    private void touchDown (float x, float y) {
        if (selectedShape != null) {
            isTouched = true;

            if (selectedShape.equals("rectangle")) {
                shape = new Rectangle(x, y);
            }
            else if (selectedShape.equals("circle")) {
                shape = new Circle(x, y);
            }
            else if (selectedShape.equals("line")) {
                shape = new Line(x, y);
            }
        }
    }

    private void touchMove (float x, float y) {
        if (shape != null) {
           if (shape instanceof Rectangle) {
               float width = x - ((Rectangle) shape).getX();
               float height = y - ((Rectangle) shape).getY();

               if (width > 0 && height > 0) {
                   ((Rectangle) shape).setWidth(width);
                   ((Rectangle) shape).setHeight(height);
               }
           }
           else if (shape instanceof Circle) {
               float radius = distance(((Circle) shape).getCx(), ((Circle) shape).getCy(), x, y);

               if (radius > 0) {
                   ((Circle) shape).setRadius(radius);
               }
           }
           else if (shape instanceof Line) {
               ((Line) shape).setxEnd(x);
               ((Line) shape).setyEnd(y);
               float length = distance(((Line) shape).getxStart(), ((Line) shape).getyStart(), x, y);

               if (length > 0) {
                   ((Line) shape).setLength(length);
               }
           }
        }
    }

    private float distance (float x1, float y1, float x2, float y2) {
        double x = Double.parseDouble(Float.toString(x1));
        double y = Double.parseDouble(Float.toString(y1));
        double a = Double.parseDouble(Float.toString(x2));
        double b = Double.parseDouble(Float.toString(y2));
        return (float) Math.sqrt(Math.pow(x - a, 2) + Math.pow(y - b, 2));
    }

}