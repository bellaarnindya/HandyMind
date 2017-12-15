package com.example.sabila.handymind.tools;

import android.util.Log;

import com.example.sabila.handymind.Shape;
import com.example.sabila.handymind.Tool;
import com.example.sabila.handymind.shapes.Rectangle;

import java.util.List;

/**
 * Created by Sabila on 12/10/2017.
 */

public class RectangleTool extends Tool {

    private Rectangle rectangle;

    @Override
    public Shape createShape(float x, float y) {
        rectangle = new Rectangle(x, y);
        return rectangle;
    }

    @Override
    public void drag(float x, float y) {
        float width = x - rectangle.getX();
        float height = y - rectangle.getY();

        if (width > 0 && height > 0) {
            rectangle.setWidth(width);
            rectangle.setHeight(height);
        }
        
        rectangle.updatePoint();
    }

    @Override
    public void touchUp(List<Shape> shapeList) {

    }
}
