package com.example.sabila.handymind.tools;

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
    public Shape createShape(float x, float y) {
        roundRect = new RoundRect(x, y);
        return roundRect;
    }

    @Override
    public void drag(float x, float y) {
        float width = x - roundRect.getX();
        float height = y - roundRect.getY();

        if (width > 0 && height > 0) {
            roundRect.setWidth(width);
            roundRect.setHeight(height);
        }
    }

    @Override
    public void touchUp(List<Shape> shapeList) {

    }
}
