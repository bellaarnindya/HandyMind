package com.example.sabila.handymind.tools;

import com.example.sabila.handymind.DrawingView;
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
    public void touchDown(float x, float y, DrawingView drawingView) {
        oval = new Oval(x, y);
        drawingView.addShape(oval);
    }

    @Override
    public void touchMove(float x, float y) {
        float width = x - oval.getLeft();
        float height = y - oval.getTop();

        if (width > 0 && height > 0) {
            oval.setRight(x);
            oval.setBottom(y);
        }

        oval.updatePoint();
    }

    @Override
    public void touchUp(DrawingView drawingView) {

    }
}
