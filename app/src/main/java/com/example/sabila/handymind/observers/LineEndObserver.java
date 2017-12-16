package com.example.sabila.handymind.observers;

import android.graphics.PointF;
import android.util.Log;

import com.example.sabila.handymind.Shape;
import com.example.sabila.handymind.ShapeObserver;
import com.example.sabila.handymind.shapes.Line;

/**
 * Created by Sabila on 12/15/2017.
 */

public class LineEndObserver extends ShapeObserver {

    private Line lineObserver;

    public LineEndObserver(Line line) {
        this.lineObserver = line;
    }

    @Override
    public void update(Shape shape) {
        PointF point = shape.getPoint(lineObserver.getxEnd(), lineObserver.getyEnd());
        float x = point.x;
        float y = point.y;

        lineObserver.setxEnd(x);
        lineObserver.setyEnd(y);

        if (x < 0 || y < 0) {
            lineObserver.delete();
        }
    }
}
