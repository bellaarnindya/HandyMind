package com.example.sabila.handymind.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.sabila.handymind.Shape;

/**
 * Created by Sabila on 11/29/2017.
 */

public class Oval extends Shape {

    private float left;
    private float top;
    private float right;
    private float bottom;

    public Oval(float left, float top) {
        this.left = left;
        this.top = top;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public float getBottom() {
        return bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawOval(left, top, right, bottom, paint);
    }
}
