package com.example.sabila.handymind.lineBehaviors;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by nafiar on 08/12/2017.
 */

public class LineStraightBody extends LineBodyBehavior {

    @Override
    public void drawBody(Canvas canvas, Paint paint, float xStart, float yStart, float xEnd, float yEnd) {
        canvas.drawLine(xStart, yStart, xEnd, yEnd, paint);
    }
}
