package com.example.sabila.handymind.lineBehaviors;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.example.sabila.handymind.LineBodyBehavior;

import static android.content.ContentValues.TAG;

/**
 * Created by pclp on 08/12/2017.
 */

public class LineStripedBody extends LineBodyBehavior {
    @Override
    public void drawBody(Canvas canvas, Paint paint, float xStart, float yStart, float xEnd, float yEnd) {
        Log.d("DEBUG", "masuk striped line");
        float x1 = xStart;
        float y1 = yStart;
        float x2, y2;
        float width = Math.abs(xEnd - xStart);
        float height = Math.abs(yEnd - yStart);
        int max = (int)((float)Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2)) / 15);
        for(int i = 0; i < max; i++) {
            float distance = (float)Math.sqrt(Math.pow(Math.abs(xEnd - x1),2) + Math.pow(Math.abs(yEnd - y1), 2));
            x2 = findPoint(x1, 15, distance, xEnd);
            y2 = findPoint(y1, 15, distance, yEnd);
            if(i%2 == 0) {
                canvas.drawLine(x1, y1, x2, y2, paint);
            }
            x1 = x2;
            y1 = y2;
        }
    }

    private float findPoint(float coorStart, float offset, float distance, float coorEnd) {
        return coorStart + ((offset / distance) * Math.abs(coorEnd - coorStart));
    }
}
