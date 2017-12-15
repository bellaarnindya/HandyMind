package com.example.sabila.handymind;

/**
 * Created by Sabila on 12/10/2017.
 */

public abstract class ShapeObserver {
    protected ShapeObservable shapeObservable;

    public abstract void update(float x, float y);
}
