package com.example.sabila.handymind.tools;

import com.example.sabila.handymind.DrawingView;
import com.example.sabila.handymind.Shape;
import com.example.sabila.handymind.Tool;

import java.util.List;

/**
 * Created by Sabila on 12/15/2017.
 */

public class SelectionTool extends Tool {

    @Override
    public Shape touchDown(float x, float y, DrawingView drawingView) {
        List<Shape> shapeList = drawingView.getShapes();
        for (Shape shape : shapeList) {
            if (shape.isTouched(x, y));
        }
        return null;
    }

    @Override
    public void touchMove(float x, float y) {

    }

    @Override
    public void touchUp(DrawingView drawingView) {

    }
}
