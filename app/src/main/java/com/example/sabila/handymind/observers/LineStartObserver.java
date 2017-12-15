package com.example.sabila.handymind.observers;

import com.example.sabila.handymind.Shape;
import com.example.sabila.handymind.ShapeObserver;

/**
 * Created by Sabila on 12/15/2017.
 */

public class LineStartObserver extends ShapeObserver {


    public LineStartObserver(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void update(float touchX, float touchY) {

    }
}
