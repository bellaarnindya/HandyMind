package com.example.sabila.handymind.shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.sabila.handymind.Shape;
import com.example.sabila.handymind.ShapeObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * Created by Sabila on 11/20/2017.
 */

public class Rectangle extends Shape {
    private float x;
    private float y;
    private float width;
    private float height;

    private ArrayList<ShapeObserver> rectObservers;

    private Paint drawPaint;

    public Rectangle(float x, float y) {
        this.x = x;
        this.y = y;
        this.width = (float)0;
        this.height = (float)0;

        rectObservers = new ArrayList<>();

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

        this.updatePoint();
        notifyAllObservers();
    }

    @Override
    public void finishMove(){}

    @Override
    public float getTop() { return this.y; }
    @Override
    public float getBottom() { return this.y + this.height; }
    @Override
    public float getRight() { return this.x + this.width; }
    @Override
    public float getLeft() { return this.x; }

    @Override
    public void setRight(float x) { this.width = x - this.x; }
    @Override
    public void setLeft(float x) {
        this.setWidth(getRight() - x);
        this.x = x;
    }
    @Override
    public void setBottom(float y) { this.height = y - this.y; }
    @Override
    public void setTop(float y) {
        float bottom = getBottom();
        this.setHeight(bottom - y);
        this.y = y;
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
        this.setState(new ActiveState());
    }

    @Override
    public void setInactive() {
        drawPaint.setStrokeWidth(5);
        onResize = false;
        this.setState(new InactiveState());
    }

    @Override
    public void attach(ShapeObserver observer) {
        rectObservers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (ShapeObserver observer : rectObservers) {
            observer.update(this);
        }
    }

    @Override
    public void delete() {
        this.x = -1;
        this.y = -1;
        this.width = 0;
        this.height = 0;
    }
}
