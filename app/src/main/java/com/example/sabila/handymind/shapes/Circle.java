package com.example.sabila.handymind.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.sabila.handymind.Shape;

/**
 * Created by Sabila on 11/28/2017.
 */

public class Circle extends Shape {

    private float cx;
    private float cy;
    private float radius;

    public Circle(float cx, float cy) {
        this.cx = cx;
        this.cy = cy;
    }

    public float getCx() {
        return cx;
    }

    public void setCx(float cx) {
        this.cx = cx;
    }

    public float getCy() {
        return cy;
    }

    public void setCy(float cy) {
        this.cy = cy;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawCircle(cx, cy, radius, paint);
    }
}
