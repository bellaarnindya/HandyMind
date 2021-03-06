package com.example.sabila.handymind;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;

import com.example.sabila.handymind.shapes.CircleResize;
import com.example.sabila.handymind.shapes.InactiveState;
import com.example.sabila.handymind.shapes.ShapeState;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Sabila on 11/20/2017.
 */

public abstract class Shape implements ShapeObservable{
    private UUID ID;
    public ShapeState currentState;
    protected float xCoordsOnTouch;
    protected float yCoordsOnTouch;

    protected List<CircleResize> resizingCircle;
    protected boolean onResize;

    protected Paint onResizePaint;

    public Shape() {
        this.ID = UUID.randomUUID();
        currentState = new InactiveState();

        resizingCircle = new ArrayList<>();

        onResizePaint = new Paint();
        onResizePaint.setColor(Color.BLACK);
        onResizePaint.setStyle(Paint.Style.FILL);
    }

    public UUID getID() {
        return ID;
    }

    public void setState(ShapeState s) {
        currentState = s;
    }

    public void click() {
        currentState.click(this);
    }

    public ShapeState getCurrentState() {
        return currentState;
    }

    public void draw(Canvas canvas) {}

    public void initialMove(float x, float y){}

    public void move(float x, float y) {}

    public void finishMove(){}

    public void setActive() {}
    public void setInactive() {}

    public boolean isTouched(float touchX, float touchY) { return false; }

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

    public void updatePoint() {
        resizingCircle.get(0).updateCoordiate(getLeft(), getTop());
        resizingCircle.get(1).updateCoordiate(getRight(), getTop());
        resizingCircle.get(2).updateCoordiate(getRight(), getBottom());
        resizingCircle.get(3).updateCoordiate(getLeft(), getBottom());
    }

    public void moveTopLeft(float touchX, float touchY){
        float right = getRight();
        float bottom = getBottom();

        float width = right - touchX;
        float height = bottom - touchY;

        if (width > 0 && height > 0) {
            setTop(touchY);
            setLeft(touchX);
        }
    }

    public void moveTopRight(float touchX, float touchY){
        float yBottom = getBottom();

        float width = touchX - getLeft();
        float height = yBottom - touchY;

        if (width > 0 && height > 0) {
            setTop(touchY);
            setRight(touchX);
        }
    }

    public void moveBottomRight(float touchX, float touchY){
        float width = touchX - getLeft();
        float height = touchY - getTop();

        if (width > 0 && height > 0) {
            setBottom(touchY);
            setRight(touchX);
        }
    }

    public void moveBottomLeft(float touchX, float touchY){
        float yTop = getTop();
        float xRight = getRight();

        float width = xRight - touchX;
        float height = touchY - yTop;

        if (width > 0 && height > 0) {
            setBottom(touchY);
            setLeft(touchX);
        }
    }

    public void resize(int selectedCircle, float touchX, float touchY) {
        switch(selectedCircle) {
            case 0: {
                moveTopLeft(touchX, touchY);
                break;
            }
            case 1: {
                moveTopRight(touchX, touchY);
                break;
            }
            case 2: {
                moveBottomRight(touchX, touchY);
                break;
            }
            case 3: {
                moveBottomLeft(touchX, touchY);
                break;
            }
        }

        this.updatePoint();
        notifyAllObservers();
    }

    protected void drawResizingCircles(Canvas canvas) {
        Log.d("Debug", "size resizing circle : " + resizingCircle.size());
        for (int i = 0; i < resizingCircle.size(); i++) {
            resizingCircle.get(i).draw(canvas);
        }
    }

    public float getLeft(){ return (float) 0.0; }
    public float getRight(){ return (float) 0.0; }
    public float getBottom(){ return (float) 0.0; }
    public float getTop(){ return (float) 0.0; }

    public void setLeft(float x){  }
    public void setRight(float x){  }
    public void setBottom(float y){  }
    public void setTop(float y){  }

    @Override
    public void attach(ShapeObserver observer) {

    }

    @Override
    public void notifyAllObservers() {

    }

    public abstract void delete();

    public PointF getPoint(float xLine, float yLine) {
        float minDistance = 9999999;
        float pointX = 0;
        float pointY = 0;
        float xPos = 0;
        float yPos = 0;

        for (int position = 1; position <= 4; position++) {
            switch (position) {
                case 1:
                    xPos = (getRight() + getLeft()) / 2;
                    yPos = getTop();
                    break;
                case 2:
                    xPos = getRight();
                    yPos = (getTop() + getBottom()) / 2;
                    break;
                case 3:
                    xPos = (getRight() + getLeft()) / 2;
                    yPos = getBottom();
                    break;
                case 4:
                    xPos = getLeft();
                    yPos = (getTop() + getBottom()) / 2;
                    break;
            }
            float distance = distance(xPos, yPos, xLine, yLine);
            if (minDistance > distance) {
                minDistance = distance;
                pointX = xPos;
                pointY = yPos;
            }
        }
        return new PointF(pointX, pointY);
    }
    protected float distance(float xPos, float yPos, float aPos, float bPos) {
        double x = Double.parseDouble(Float.toString(aPos));
        double y = Double.parseDouble(Float.toString(bPos));
        double a = Double.parseDouble(Float.toString(xPos));
        double b = Double.parseDouble(Float.toString(yPos));
        return (float) Math.sqrt(Math.pow(x - a, 2) + Math.pow(y - b, 2));
    }
}
