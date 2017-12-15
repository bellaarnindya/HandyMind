package com.example.sabila.handymind.tools;

import com.example.sabila.handymind.DrawingView;
import com.example.sabila.handymind.Shape;
import com.example.sabila.handymind.Tool;
import com.example.sabila.handymind.shapes.ActiveState;

import java.util.List;

/**
 * Created by Sabila on 12/15/2017.
 */

public class DeleteTool extends Tool {
    private Shape shapeToDelete = null;

    @Override
    public void touchDown(float x, float y, DrawingView drawingView) {
        List<Shape> shapeList = drawingView.getShapes();
        for (Shape shape : shapeList) {
            if (shape.isTouched(x, y)) {
                shape.setActive();
                shapeToDelete = shape;
                break;
            }
        }
        int indexOfDelete = shapeList.indexOf(shapeToDelete);
        shapeToDelete.delete();
        drawingView.deleteShape(indexOfDelete);
        shapeToDelete.notifyAllObservers();
    }

    @Override
    public void touchMove(float x, float y) {

    }

    @Override
    public void touchUp(DrawingView drawingView) {

    }
}
