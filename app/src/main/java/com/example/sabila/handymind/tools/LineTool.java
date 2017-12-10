package com.example.sabila.handymind.tools;

import com.example.sabila.handymind.Shape;
import com.example.sabila.handymind.Tool;
import com.example.sabila.handymind.shapes.Line;

/**
 * Created by Sabila on 12/10/2017.
 */

public class LineTool extends Tool {
    private Line line;

    @Override
    public Shape createShape(float x, float y) {
        line = new Line(x, y);
        return line;
    }

    @Override
    public void drag(float x, float y) {
        line.setxEnd(x);
        line.setyEnd(y);
    }
}
