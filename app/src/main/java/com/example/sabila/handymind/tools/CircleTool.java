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
    public Shape touchDown(float x, float y, DrawingView drawingView) {
        circle = new Circle(x, y);
        drawingView.addShape(circle);
        return circle;
    }

    @Override
    public void touchMove(float x, float y) {
        double x1 = Double.parseDouble(Float.toString(circle.getCx()));
        double y1 = Double.parseDouble(Float.toString(circle.getCy()));
        double a = Double.parseDouble(Float.toString(x));
        double b = Double.parseDouble(Float.toString(y));
        float radius =  (float) Math.sqrt(Math.pow(x1 - a, 2) + Math.pow(y1 - b, 2));

        if (radius > 0) {
            circle.setRadius(radius);
        }
    }

    @Override
    public void touchUp(DrawingView drawingView) {

    }
}
