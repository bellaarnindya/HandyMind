package com.example.sabila.handymind.lineBehaviors;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.example.sabila.handymind.LineBodyBehavior;

/**
 * Created by nafiar on 08/12/2017.
 */

public class LineStraightBody extends LineBodyBehavior {

    @Override
    public void drawBody(Canvas canvas, Paint paint, float xStart, float yStart, float xEnd, float yEnd) {
        Log.d("DEBUG", "masuk straight line");
        canvas.drawLine(xStart, yStart, xEnd, yEnd, paint);
    }
}
