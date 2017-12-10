package com.example.sabila.handymind;

/**
 * Created by Sabila on 12/10/2017.
 */

public abstract class ShapeObserver extends Shape {
    protected ShapeObservable shapeObservable;

    public abstract void update(float touchX, float touchY);
}
