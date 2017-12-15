package com.example.sabila.handymind.shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

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

    private Paint drawPaint;

    public RoundRect(float x, float y) {
        this.x = x;
        this.y = y;

        drawPaint = new Paint();
        drawPaint.setColor(Color.BLACK);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeWidth(4);
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
    public void draw(Canvas canvas) {
        canvas.drawRoundRect(x, y, width + x, height + y, RX, RY, drawPaint);
    }

    @Override
    public void initialMove(float touchX, float touchY){
        xCoordsOnTouch = touchX - x;
        yCoordsOnTouch = touchY - y;
    }

    @Override
    public void move(float touchX, float touchY) {
        this.x = touchX - xCoordsOnTouch;
        this.y = touchY - yCoordsOnTouch;
    }

    @Override
    public void finishMove(){}


    @Override
    public void resize(float touchX, float touchY) {
        float width = touchX - this.x;
        float height = touchY - this.y;

        if (width > 0 && height > 0) {
            this.width = width;
            this.height = height;
        }
    }

    @Override
    public boolean isTouched(float touchX, float touchY) {
        return (touchX > this.x &&
                touchX < this.x + this.width &&
                touchY > this.y &&
                touchY < this.y + this.height);
    }

    @Override
    public void setActive() {
        drawPaint.setStrokeWidth(7);
    }

    @Override
    public void setInactive() {drawPaint.setStrokeWidth(5); }

    @Override
    public float getRightX() {
        return this.x + this.width;
    }

    @Override
    public float getRightY() {
        return this.height / 2;
    }

    @Override
    public float getLeftX() {
        return this.x;
    }

    @Override
    public float getLeftY() {
        return this.height / 2;
    }
}
