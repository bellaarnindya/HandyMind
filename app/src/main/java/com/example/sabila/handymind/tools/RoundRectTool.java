package com.example.sabila.handymind.tools;

import com.example.sabila.handymind.DrawingView;
import com.example.sabila.handymind.Tool;
import com.example.sabila.handymind.shapes.RoundRect;

/**
 * Created by Sabila on 12/10/2017.
 */

public class RoundRectTool extends Tool {
    private RoundRect roundRect;

    @Override
    public void touchDown(float x, float y, DrawingView drawingView) {
        roundRect = new RoundRect(x, y);
        drawingView.addShape(roundRect);
        drawingView.saveShapeState();
    }

    @Override
    public void touchMove(float x, float y) {
        float width = x - roundRect.getX();
        float height = y - roundRect.getY();

        if (width > 0 && height > 0) {
            roundRect.setWidth(width);
            roundRect.setHeight(height);
        }

        roundRect.updatePoint();
    }

    @Override
    public void touchUp(DrawingView drawingView) {

    }
}
