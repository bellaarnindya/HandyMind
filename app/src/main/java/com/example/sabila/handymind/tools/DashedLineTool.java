package com.example.sabila.handymind.tools;

import com.example.sabila.handymind.DrawingView;
import com.example.sabila.handymind.Shape;
import com.example.sabila.handymind.Tool;
import com.example.sabila.handymind.observers.LineEndObserver;
import com.example.sabila.handymind.observers.LineStartObserver;
import com.example.sabila.handymind.shapes.Line;

import java.util.List;

/**
 * Created by nafiar on 12/12/17.
 */

public class DashedLineTool extends Tool {
    private Line line;

    @Override
    public void touchDown(float x, float y, DrawingView drawingView) {
        line = new Line(x, y);
        line.setDashedLine();
        drawingView.addShape(line);
    }

    @Override
    public void touchMove(float x, float y) {
        if (line == null) return;

        line.setxEnd(x);
        line.setyEnd(y);
    }

    @Override
    public void touchUp(DrawingView drawingView) {
        if (line == null) return;

        List<Shape> shapeList = drawingView.getShapes();
        for (Shape shape : shapeList) {
            if (shape instanceof Line) continue;

            if (line.intersects(line.getxStart(), line.getyStart(), shape)) {

                LineStartObserver lineStartObserver = new LineStartObserver(line);

                lineStartObserver.update(shape);
                shape.attach(lineStartObserver);
            }
            if (line.intersects(line.getxEnd(), line.getyEnd(), shape)) {

                LineEndObserver lineEndObserver = new LineEndObserver(line);

                lineEndObserver.update(shape);
                shape.attach(lineEndObserver);
            }
        }

    }
}
