package com.example.sabila.handymind.tools;

import com.example.sabila.handymind.Shape;
import com.example.sabila.handymind.Tool;
import com.example.sabila.handymind.observables.ShapeDestinationObservable;
import com.example.sabila.handymind.observables.ShapeSourceObservable;
import com.example.sabila.handymind.observers.LineEndObserver;
import com.example.sabila.handymind.observers.LineStartObserver;
import com.example.sabila.handymind.shapes.Line;

import java.util.List;

/**
 * Created by Sabila on 12/10/2017.
 */

public class LineTool extends Tool {
    private Line line;

    @Override
    public Shape createShape(float x, float y) {
        line = new Line(x, y);
        return line;
    }

    @Override
    public void drag(float x, float y) {
        line.setxEnd(x);
        line.setyEnd(y);
    }

    @Override
    public void touchUp(List<Shape> shapeList) {
        for (Shape shape : shapeList) {
            if (line.intersects(line.getxStart(), line.getyStart(), shape)) {
                ShapeSourceObservable shapeSourceObservable = new ShapeSourceObservable(shape);
                LineStartObserver lineStartObserver = new LineStartObserver(line);
                line.setxStart(shape.getRightX());
                line.setyStart(shape.getRightY());
                shapeSourceObservable.attach(lineStartObserver);
            }
            if (line.intersects(line.getxEnd(), line.getyEnd(), shape)) {
                ShapeDestinationObservable shapeDestinationObservable = new ShapeDestinationObservable(shape);
                LineEndObserver lineEndObserver = new LineEndObserver(line);
                line.setxEnd(shape.getLeftX());
                line.setyEnd(shape.getLeftY());
                shapeDestinationObservable.attach(lineEndObserver);
            }
        }
    }
}
