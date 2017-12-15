package com.example.sabila.handymind.tools;

import com.example.sabila.handymind.Shape;
import com.example.sabila.handymind.Tool;
import com.example.sabila.handymind.shapes.Oval;

import java.util.List;

/**
 * Created by Sabila on 12/10/2017.
 */

public class OvalTool extends Tool {
    private Oval oval;

    @Override
    public Shape createShape(float x, float y) {
        oval = new Oval(x, y);
        return oval;
    }

    @Override
    public void drag(float x, float y) {
        float width = x - oval.getLeft();
        float height = y - oval.getTop();

        if (width > 0 && height > 0) {
            oval.setRight(x);
            oval.setBottom(y);
        }
    }

    @Override
    public void touchUp(List<Shape> shapeList) {

    }
}
