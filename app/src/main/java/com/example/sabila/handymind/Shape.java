package com.example.sabila.handymind;

import com.example.sabila.handymind.shapes.InactiveState;
import com.example.sabila.handymind.shapes.ShapeState;

import java.util.UUID;

/**
 * Created by Sabila on 11/20/2017.
 */

public abstract class Shape {
    private UUID ID;
    private ShapeState currentState;

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
}
