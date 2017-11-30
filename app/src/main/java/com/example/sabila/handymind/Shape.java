package com.example.sabila.handymind;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.sabila.handymind.shapes.InactiveState;
import com.example.sabila.handymind.shapes.ShapeState;

import java.util.UUID;

/**
 * Created by Sabila on 11/20/2017.
 */

public abstract class Shape {
    private UUID ID;
    private ShapeState currentState;
    protected float xCoordsOnTouch;
    protected float yCoordsOnTouch;

    public Shape() {
        this.ID = UUID.randomUUID();
        currentState = new InactiveState();
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

    public void drag(float x, float y) {}

    public void resize(float x, float y) {}

    public void setActive() {}
    public void setInactive() {}

    public boolean isTouched(float touchX, float touchY) { return false; }
}
