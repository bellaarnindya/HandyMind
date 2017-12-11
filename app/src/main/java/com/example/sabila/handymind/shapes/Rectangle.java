package com.example.sabila.handymind.shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.example.sabila.handymind.Shape;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sabila on 11/20/2017.
 */

public class Rectangle extends Shape {
    private float x;
    private float y;
    private float width;
    private float height;

    private Paint drawPaint;

    public Rectangle(float x, float y) {
        this.x = x;
        this.y = y;
        this.width = (float)0;
        this.height = (float)0;

        drawPaint = new Paint();
        drawPaint.setColor(Color.BLACK);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeWidth(4);

        onResize = false;

        CircleResize cTopLeft = new CircleResize(x, y);
        CircleResize cTopRight = new CircleResize(x, y);
        CircleResize cBottomRight = new CircleResize(x, y);
        CircleResize cBottomLeft = new CircleResize(x, y);

        resizingCircle.add(cTopLeft);
        resizingCircle.add(cTopRight);
        resizingCircle.add(cBottomRight);
        resizingCircle.add(cBottomLeft);
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
        canvas.drawRect(x, y, width + x, height + y, drawPaint);

        if (onResize) {
            this.drawResizingCircles(canvas);
        }
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

        this.updatePoint(touchX, touchY);
    }

    @Override
    public void finishMove(){}

    @Override
    public void moveTopLeft(float touchX, float touchY){
        float xRight = this.x + this.width;
        float yBottom = this.y + this.height;

        float width = xRight - touchX;
        float height = yBottom - touchY;

        if (width > 0 && height > 0) {
            this.x = touchX;
            this.y = touchY;
            this.width = width;
            this.height = height;
        }
    }

    @Override
    public void moveTopRight(float touchX, float touchY){
        float yBottom = this.y + this.height;

        float width = touchX - this.x;
        float height = yBottom - touchY;

        if (width > 0 && height > 0) {
            this.y = touchY;
            this.width = width;
            this.height = height;
        }
    }

    @Override
    public void moveBottomRight(float touchX, float touchY){
        float width = touchX - this.x;
        float height = touchY - this.y;

        if (width > 0 && height > 0) {
            this.width = width;
            this.height = height;
        }
    }

    @Override
    public void moveBottomLeft(float touchX, float touchY){
        float yTop = this.y;
        float xRight = this.x + width;

        float width = xRight - touchX;
        float height = touchY - yTop;

        if (width > 0 && height > 0) {
            this.x = touchX;
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
        onResize = true;
    }

    @Override
    public void setInactive() {
        drawPaint.setStrokeWidth(5);
        onResize = false;
    }

    private void drawResizingCircles(Canvas canvas) {
        for (int i = 0; i < resizingCircle.size(); i++) {
            resizingCircle.get(i).draw(canvas);
        }
    }

    @Override
    public void updatePoint(float touchX, float touchY) {
        resizingCircle.get(0).updateCoordiate(getX(), getY());
        resizingCircle.get(1).updateCoordiate(getX() + width, getY());
        resizingCircle.get(2).updateCoordiate(getX() + width, getY() + height);
        resizingCircle.get(3).updateCoordiate(getX(), getY() + height);
    }

    @Override
    public int isResizeTouched(float touchX, float touchY) {
        int circleTouched = -1;

        for (int i = 0; i < resizingCircle.size(); i++) {
            if (resizingCircle.get(i).isTouched(touchX, touchY)) {
                circleTouched = i;
                break;
            }
        }

        return circleTouched;
    }
}
