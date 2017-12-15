package com.example.sabila.handymind.tools;

import com.example.sabila.handymind.DrawingView;
import com.example.sabila.handymind.Shape;
import com.example.sabila.handymind.Tool;
import com.example.sabila.handymind.shapes.RoundRect;

import java.util.List;

/**
 * Created by Sabila on 12/10/2017.
 */

public class RoundRectTool extends Tool {
    private RoundRect roundRect;

    @Override
    public Shape touchDown(float x, float y, DrawingView drawingView) {
        roundRect = new RoundRect(x, y);
        drawingView.addShape(roundRect);
        return roundRect;
    }

    @Override
    public void touchMove(float x, float y) {
        float width = x - roundRect.getX();
        float height = y - roundRect.getY();

        if (width > 0 && height > 0) {
            roundRect.setWidth(width);
            roundRect.setHeight(height);
        }
    }

    @Override
    public void touchUp(DrawingView drawingView) {

    }
}
