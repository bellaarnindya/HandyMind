package com.example.sabila.handymind;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.example.sabila.handymind.shapes.ActiveState;
import com.example.sabila.handymind.shapes.CircleResize;
import com.example.sabila.handymind.shapes.InactiveState;
import com.example.sabila.handymind.shapes.ShapeState;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Sabila on 11/20/2017.
 */

public abstract class Shape {
    private UUID ID;
    private ShapeState currentState;
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

    public void updatePoint(float touchX, float touchY) {}

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

        this.updatePoint(touchX, touchY);
    }

    protected void drawResizingCircles(Canvas canvas) {
        for (int i = 0; i < resizingCircle.size(); i++) {
            resizingCircle.get(i).draw(canvas);
        }
    }

    protected float getLeft(){ return (float) 0.0; };
    protected float getRight(){ return (float) 0.0; };
    protected float getBottom(){ return (float) 0.0; };
    protected float getTop(){ return (float) 0.0; };

    protected void setLeft(float x){  };
    protected void setRight(float x){  };
    protected void setBottom(float y){  };
    protected void setTop(float y){  };
}
