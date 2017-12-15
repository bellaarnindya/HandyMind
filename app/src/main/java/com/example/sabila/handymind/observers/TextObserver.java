package com.example.sabila.handymind.observers;

import com.example.sabila.handymind.Shape;
import com.example.sabila.handymind.ShapeObserver;
import com.example.sabila.handymind.shapes.Text;

/**
 * Created by Sabila on 12/15/2017.
 */

public class TextObserver extends ShapeObserver {
    private Text textObserver;

    public TextObserver(Text textObserver) {
        this.textObserver = textObserver;
    }

    @Override
    public void update(Shape shapeObservable) {
        float midX = (shapeObservable.getRight() + shapeObservable.getLeft()) / 2;
        float midY = (shapeObservable.getBottom() + shapeObservable.getTop()) / 2;
        textObserver.setX(midX);
        textObserver.setY(midY);
    }
}
