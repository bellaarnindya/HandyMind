package com.example.sabila.handymind.tools;

import com.example.sabila.handymind.DrawingView;
import com.example.sabila.handymind.Shape;
import com.example.sabila.handymind.Tool;
import com.example.sabila.handymind.shapes.Circle;

import java.util.List;

/**
 * Created by Sabila on 12/10/2017.
 */

public class CircleTool extends Tool {

    private Circle circle;

    @Override
    public void touchDown(float x, float y, DrawingView drawingView) {
        circle = new Circle(x, y);
        drawingView.addShape(circle);
    }

    @Override
    public void touchMove(float x, float y) {
        if (circle == null) return;

        float radius = circle.distance(x, y, circle.getCx(), circle.getCy());

        if (radius > 0) {
            circle.setRadius(radius);
        }

        circle.updatePoint();
    }

    @Override
    public void touchUp(DrawingView drawingView) {

    }
}
