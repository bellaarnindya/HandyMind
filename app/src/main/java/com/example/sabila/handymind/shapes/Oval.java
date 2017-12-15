package com.example.sabila.handymind.shapes;

import android.graphics.Canvas;
import android.graphics.Color;
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

    private float topOnTouch;
    private float leftOnTouch;
    private float rightOnTouch;
    private float bottomOnTouch;

    private Paint drawPaint;

    public Oval(float left, float top) {
        this.left = left;
        this.right = left;
        this.top = top;
        this.bottom = top;

        drawPaint = new Paint();
        drawPaint.setColor(Color.BLACK);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeWidth(4);

        onResize = false;

        CircleResize cTopLeft = new CircleResize(left, top);
        CircleResize cTopRight = new CircleResize(left, top);
        CircleResize cBottomRight = new CircleResize(left, top);
        CircleResize cBottomLeft = new CircleResize(left, top);

        resizingCircle.add(cTopLeft);
        resizingCircle.add(cTopRight);
        resizingCircle.add(cBottomRight);
        resizingCircle.add(cBottomLeft);
    }

    public float getLeft() { return left; }
    public float getTop() {
        return top;
    }
    public float getRight() {
        return right;
    }
    public float getBottom() {
        return bottom;
    }

    public void setLeft(float left) {
        this.left = left;
    }
    public void setTop(float top) {
        this.top = top;
    }
    public void setRight(float right) {
        this.right = right;
    }
    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    public float getX() {
        float width = this.getRight() - this.getLeft();
        return this.getRight() - Math.abs(width/2);
    }

    public float getY() {
        float height = this.getBottom() - this.getTop();
        return this.getBottom() - Math.abs(height/2);
    }

    public float getXRadius() {
        float width = this.getRight() - this.getLeft();
        return Math.abs(width/2);
    }

    public float getYRadius() {
        float height = this.getBottom() - this.getTop();
        return Math.abs(height / 2);
    }

    public float getHeight() {
        return Math.abs(this.getBottom() - this.getTop());
    }

    public float getWidth() {
        return Math.abs(this.getRight() - this.getLeft());
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawOval(left, top, right, bottom, drawPaint);

        if (onResize) {
            this.drawResizingCircles(canvas);
        }
    }


    @Override
    public boolean isTouched(float touchX, float touchY) {

        float height = this.getBottom() - this.getTop();

        return (((touchX - this.getX()) * (touchX - this.getX())) / (this.getXRadius() * this.getXRadius()) +
                ((touchY - this.getY()) * (touchY - this.getY())) / (this.getYRadius() * this.getYRadius())
        ) <= 1;
    }

    @Override
    public void initialMove(float touchX, float touchY){
        xCoordsOnTouch = touchX;
        yCoordsOnTouch = touchY;

        this.topOnTouch = this.getTop();
        this.bottomOnTouch = this.getBottom();
        this.leftOnTouch = this.getLeft();
        this.rightOnTouch = this.getRight();
    }

    @Override
    public void move(float touchX, float touchY) {
        float deltaX = touchX - xCoordsOnTouch;
        float deltaY = touchY - yCoordsOnTouch;

        this.setTop(this.topOnTouch + deltaY);
        this.setBottom(this.bottomOnTouch + deltaY);
        this.setRight(this.rightOnTouch + deltaX);
        this.setLeft(this.leftOnTouch + deltaX);

        this.updatePoint();
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
}
