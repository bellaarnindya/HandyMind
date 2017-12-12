package com.example.sabila.handymind.lineBehaviors;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by pclp on 08/12/2017.
 */

public class LineDashedBody extends LineBodyBehavior {
    @Override
    public void drawBody(Canvas canvas, Paint paint, float xStart, float yStart, float xEnd, float yEnd) {
        float x1 = xStart;
        float y1 = yStart;
        float x2, y2;
        float width = Math.abs(xEnd - xStart);
        float height = Math.abs(yEnd - yStart);
        int max = (int)((float)Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2)) / 30);
        for(int i = 0; i < max; i++) {
            float distance = (float)Math.sqrt(Math.pow((xEnd - x1),2) + Math.pow((yEnd - y1), 2));
            x2 = findPoint(x1, 30, distance, xEnd);
            y2 = findPoint(y1, 30, distance, yEnd);
            if(i%2 == 0) {
                canvas.drawLine(x1, y1, x2, y2, paint);
            }
            x1 = x2;
            y1 = y2;
        }
    }

    private float findPoint(float coorStart, float offset, float distance, float coorEnd) {
        return coorStart + ((offset / distance) * (coorEnd - coorStart));
    }
}
