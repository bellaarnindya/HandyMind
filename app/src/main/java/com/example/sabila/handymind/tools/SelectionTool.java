package com.example.sabila.handymind.tools;

import com.example.sabila.handymind.DrawingView;
import com.example.sabila.handymind.Shape;
import com.example.sabila.handymind.Tool;

import java.util.List;

/**
 * Created by Sabila on 12/15/2017.
 */

public class SelectionTool extends Tool {

    private boolean touchOnShape = false;
    private boolean isSingleTouch = false;
    private boolean isResizing = false;
    private boolean isMoving = false;
    private Shape touchedShape = null;
    private int selectedCircle = -1;

    @Override
    public void touchDown(float x, float y, DrawingView drawingView) {
        List<Shape> shapeList = drawingView.getShapes();
        for (Shape shape : shapeList) {
            if (shape.isTouched(x, y)) {
                touchedShape = shape;
                touchedShape.setActive();

                touchOnShape = true;

                isSingleTouch = true;
                isMoving = true;

                touchedShape.initialMove(x, y);
            }
            else {
                shape.setInactive();
            }
        }

        if (touchedShape != null &&
                (selectedCircle = touchedShape.isResizeTouched(x, y)) != -1) {
            isResizing = true;
        }
        if (!touchOnShape && !isResizing) {
            touchedShape = null;
        }
    }

    @Override
    public void touchMove(float x, float y) {
        if (isResizing) {
            touchedShape.resize(selectedCircle, x, y);
        }
        else if (isMoving) {
            touchedShape.move(x, y);
        }
    }

    @Override
    public void touchUp(DrawingView drawingView) {
        if (isSingleTouch) {
            isSingleTouch = false;
        }

        isResizing = false;
    }
}
