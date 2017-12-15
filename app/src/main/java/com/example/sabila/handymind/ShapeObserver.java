package com.example.sabila.handymind;

/**
 * Created by Sabila on 12/10/2017.
 */

public abstract class ShapeObserver {
    protected ShapeObservable shapeObservable;
    protected Shape shape;

    public abstract void setShape(Shape shape);
    public abstract void update(float touchX, float touchY);
}
