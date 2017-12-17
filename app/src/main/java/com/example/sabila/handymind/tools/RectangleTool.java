package com.example.sabila.handymind.tools;

import com.example.sabila.handymind.DrawingView;
import com.example.sabila.handymind.Tool;
import com.example.sabila.handymind.shapes.Rectangle;

/**
 * Created by Sabila on 12/10/2017.
 */

public class RectangleTool extends Tool {

    private Rectangle rectangle;

    @Override
    public void touchDown(float x, float y, DrawingView drawingView) {
        rectangle = new Rectangle(x, y);
        drawingView.addShape(rectangle);
        drawingView.saveShapeState();
    }

    @Override
    public void touchMove(float x, float y) {
        float width = x - rectangle.getX();
        float height = y - rectangle.getY();

        if (width > 0 && height > 0) {
            rectangle.setWidth(width);
            rectangle.setHeight(height);
        }
        
        rectangle.updatePoint();
    }

    @Override
    public void touchUp(DrawingView drawingView) {
    }
}
