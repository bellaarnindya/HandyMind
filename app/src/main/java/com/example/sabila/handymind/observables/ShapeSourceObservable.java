package com.example.sabila.handymind.observables;

import com.example.sabila.handymind.Shape;
import com.example.sabila.handymind.ShapeObservable;
import com.example.sabila.handymind.ShapeObserver;
import com.example.sabila.handymind.observers.LineEndObserver;
import com.example.sabila.handymind.observers.LineStartObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sabila on 12/15/2017.
 */

public class ShapeSourceObservable extends ShapeObservable {

    List<ShapeObserver> shapeObservers;

    public ShapeSourceObservable(Shape shape) {
        shapeObservers = new ArrayList<>();
        this.shape = shape;
    }

    @Override
    public void setShape(Shape shape) {
         this.shape = shape;
    }

    @Override
    public void attach(ShapeObserver observer) {
        shapeObservers.add(observer);
    }

    @Override
    public void move(float x, float y) {

    }

    @Override
    public void notifyAllObservers(float touchX, float touchY) {

    }
}
