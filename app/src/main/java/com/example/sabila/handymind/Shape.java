package com.example.sabila.handymind;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

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

    public void draw(Canvas canvas) {}

    public void initialMove(float x, float y){}

    public void move(float x, float y) {}

    public void finishMove(){}

    public void resize(float x, float y) {}

    public void setActive() {}
    public void setInactive() {}

    public boolean isTouched(float touchX, float touchY) { return false; }

}
