package com.example.sabila.handymind.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.example.sabila.handymind.Shape;

/**
 * Created by Sabila on 11/28/2017.
 */

public class Line extends Shape {

    private float xStart;
    private float yStart;
    private float xEnd;
    private float yEnd;
    private float length;


    public Line(float x, float y) {
        this.xStart = x;
        this.yStart = y;
        this.xEnd = x;
        this.yEnd = y;
    }

    public float getxStart() {
        return xStart;
    }

    public void setxStart(float xStart) {
        this.xStart = xStart;
    }

    public float getyStart() {
        return yStart;
    }

    public void setyStart(float yStart) {
        this.yStart = yStart;
    }

    public float getxEnd() {
        return xEnd;
    }

    public void setxEnd(float xEnd) {
        this.xEnd = xEnd;
    }

    public float getyEnd() {
        return yEnd;
    }

    public void setyEnd(float yEnd) {
        this.yEnd = yEnd;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawLine(xStart, yStart, xEnd, yEnd, paint);
    }
}
