package com.example.sabila.handymind.tools;

import android.util.Log;

import com.example.sabila.handymind.DrawingView;
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
    public Shape touchDown(float x, float y, DrawingView drawingView) {
        line = new Line(x, y);
        drawingView.addShape(line);
        return line;
    }

    @Override
    public void touchMove(float x, float y) {
        line.setxEnd(x);
        line.setyEnd(y);
    }

    @Override
    public void touchUp(DrawingView drawingView) {
        List<Shape> shapeList = drawingView.getShapes();
        for (Shape shape : shapeList) {
            if (shape instanceof Line) continue;

            if (line.intersects(line.getxStart(), line.getyStart(), shape)) {

                ShapeSourceObservable shapeSourceObservable = new ShapeSourceObservable(shape);
                LineStartObserver lineStartObserver = new LineStartObserver(line);

                float midY = (shape.getBottom() + shape.getTop()) / 2;

                lineStartObserver.update(shape.getRight(), midY);
                shapeSourceObservable.attach(lineStartObserver);
            }
            if (line.intersects(line.getxEnd(), line.getyEnd(), shape)) {

                ShapeDestinationObservable shapeDestinationObservable = new ShapeDestinationObservable(shape);
                LineEndObserver lineEndObserver = new LineEndObserver(line);

                float midY = (shape.getBottom() + shape.getTop()) / 2;

                lineEndObserver.update(shape.getLeft(), midY);
                shapeDestinationObservable.attach(lineEndObserver);
            }
        }
    }
}
