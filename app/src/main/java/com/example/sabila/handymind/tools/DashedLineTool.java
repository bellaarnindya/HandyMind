package com.example.sabila.handymind.tools;

import android.util.Log;

import com.example.sabila.handymind.Shape;
import com.example.sabila.handymind.Tool;
import com.example.sabila.handymind.shapes.Line;

/**
 * Created by nafiar on 12/12/17.
 */

public class DashedLineTool extends Tool {
    private Line line;

    @Override
    public Shape createShape(float x, float y) {
        line = new Line(x, y);
        line.setDashedLine();
        Log.d("DEBUG","berhasil set dashedline");
        return line;
    }

    @Override
    public void drag(float x, float y) {
        line.setxEnd(x);
        line.setyEnd(y);
    }
}
