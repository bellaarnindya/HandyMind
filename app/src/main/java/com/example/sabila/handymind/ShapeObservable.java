package com.example.sabila.handymind;

import com.example.sabila.handymind.shapes.Line;

import java.util.ArrayList;

/**
 * Created by Sabila on 12/10/2017.
 */

public interface ShapeObservable {
    void attach(ShapeObserver observer);
    void move(float x, float y);
    void notifyAllObservers();
}
