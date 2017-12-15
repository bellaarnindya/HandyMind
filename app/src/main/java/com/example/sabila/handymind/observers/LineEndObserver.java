package com.example.sabila.handymind.observers;

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
    public void update(float x, float y) {
        lineObserver.setxEnd(x);
        lineObserver.setyEnd(y);
    }
}
