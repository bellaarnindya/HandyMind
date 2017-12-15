package com.example.sabila.handymind.shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.sabila.handymind.Shape;
import com.example.sabila.handymind.ShapeObservable;
import com.example.sabila.handymind.ShapeObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sabila on 11/28/2017.
 */

public class Circle extends Shape {

    private float cx;
    private float cy;
    private float radius;
    private List<ShapeObserver> circleObservers;

    private Paint drawPaint;

    public Circle(float cx, float cy) {
        this.cx = cx;
        this.cy = cy;

        circleObservers = new ArrayList<>();

        drawPaint = new Paint();
        drawPaint.setColor(Color.BLACK);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeWidth(4);

        onResize = false;

        CircleResize cTopLeft = new CircleResize(cx, cy);
        CircleResize cTopRight = new CircleResize(cx, cy);
        CircleResize cBottomRight = new CircleResize(cx, cy);
        CircleResize cBottomLeft = new CircleResize(cx, cy);

        resizingCircle.add(cTopLeft);
        resizingCircle.add(cTopRight);
        resizingCircle.add(cBottomRight);
        resizingCircle.add(cBottomLeft);
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

        if (onResize) {
            this.drawResizingCircles(canvas);
        }
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

        this.updatePoint();
        notifyAllObservers();
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
    public float getTop() { return this.cy - this.radius; }
    @Override
    public float getBottom() { return this.cy + this.radius; }
    @Override
    public float getRight() { return this.cx + this.radius; }
    @Override
    public float getLeft() { return this.cx - this.radius; }

    @Override
    public void setRight(float x) { this.setRadius(x - this.cx); }
    @Override
    public void setLeft(float x) { this.setRadius(this.cx - x); }
    @Override
    public void setBottom(float y) { this.setRadius(y - this.cy); }
    @Override
    public void setTop(float y) { this.setRadius(this.cy - y); }

    @Override
    public void attach(ShapeObserver observer) {
        circleObservers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (ShapeObserver observer : circleObservers) {
            observer.update(this);
        }
    }
}
