package com.example.sabila.handymind.observers;

import android.graphics.PointF;
import android.util.Log;

import com.example.sabila.handymind.Shape;
import com.example.sabila.handymind.ShapeObserver;
import com.example.sabila.handymind.shapes.Line;

/**
 * Created by Sabila on 12/15/2017.
 */

public class LineStartObserver extends ShapeObserver {

    private Line lineObserver;

    public LineStartObserver(Line line) {
        this.lineObserver = line;
    }

    @Override
    public void update(Shape shape) {
        PointF point = shape.getPoint(lineObserver.getxStart(), lineObserver.getyStart());
        float x = point.x;
        float y = point.y;

        lineObserver.setxStart(x);
        lineObserver.setyStart(y);

        if (x < 0 || y < 0) {
            lineObserver.delete();
        }
    }
}
