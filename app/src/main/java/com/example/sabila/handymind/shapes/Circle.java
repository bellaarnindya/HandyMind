package com.example.sabila.handymind.shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.sabila.handymind.Shape;

/**
 * Created by Sabila on 11/28/2017.
 */

public class Circle extends Shape {

    private float cx;
    private float cy;
    private float radius;

    private Paint drawPaint;

    public Circle(float cx, float cy) {
        this.cx = cx;
        this.cy = cy;

        drawPaint = new Paint();
        drawPaint.setColor(Color.BLACK);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeWidth(4);
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
    public void draw(Canvas canvas) {
        canvas.drawCircle(cx, cy, radius, drawPaint);
    }


    @Override
    public void initialMove(float touchX, float touchY){
        xCoordsOnTouch = touchX - cx;
        yCoordsOnTouch = touchY - cy;
    }

    @Override
    public void move(float touchX, float touchY) {
        this.cx = touchX - xCoordsOnTouch;
        this.cy = touchY - yCoordsOnTouch;
    }

    @Override
    public void finishMove(){}


    @Override
    public boolean isTouched(float touchX, float touchY) {
        double x = Double.parseDouble(Float.toString(this.getCx()));
        double y = Double.parseDouble(Float.toString(this.getCy()));
        double a = Double.parseDouble(Float.toString(touchX));
        double b = Double.parseDouble(Float.toString(touchY));
        float distance = (float) Math.sqrt(Math.pow(x - a, 2) + Math.pow(y - b, 2));

        if (distance <= this.getRadius()) {
            return true;
        }
        return false;
    }

    @Override
    public void setActive() {
        drawPaint.setStrokeWidth(7);
    }

    @Override
    public void setInactive() {drawPaint.setStrokeWidth(5); }

    @Override
    public float getRightX() {
        return (float) (this.cx + (radius * Math.cos(180)));
    }

    @Override
    public float getRightY() {
        return (float) (this.cy + (radius * Math.sin(180)));
    }

    @Override
    public float getLeftX() {
        return (float) (this.cx + (radius * Math.cos(0)));
    }

    @Override
    public float getLeftY() {
        return (float) (this.cy + (radius * Math.sin(0)));
    }
}
