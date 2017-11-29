package com.example.sabila.handymind.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.sabila.handymind.Shape;

/**
 * Created by Sabila on 11/29/2017.
 */

public class RoundRect extends Shape {

    private float x;
    private float y;
    private static final float RX = 30;
    private static final float RY = 30;
    private float width;
    private float height;

    public RoundRect(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawRoundRect(x, y, x+width, y+height, RX, RY, paint);
    }
}
