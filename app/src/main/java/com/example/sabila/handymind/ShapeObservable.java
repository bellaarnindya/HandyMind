package com.example.sabila.handymind;

import com.example.sabila.handymind.shapes.Line;

import java.util.ArrayList;

/**
 * Created by Sabila on 12/10/2017.
 */

public abstract class ShapeObservable extends Shape {
    public abstract void attach(ShapeObserver observer);
    public abstract void move(float x, float y);
    public abstract void notifyAllObservers(float touchX, float touchY);

}
