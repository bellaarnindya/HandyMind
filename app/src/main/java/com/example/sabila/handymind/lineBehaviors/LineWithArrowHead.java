package com.example.sabila.handymind.lineBehaviors;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.sabila.handymind.LineHeadBehavior;

/**
 * Created by pclp on 08/12/2017.
 */

public class LineWithArrowHead extends LineHeadBehavior {
    @Override
    public void drawHead(Canvas canvas, Paint paint, float xStart, float yStart, float xEnd, float yEnd) {
        float x1 = xEnd;
        float y1 = yEnd;
        float x2 = xStart;
        float y2 = yStart;
        float distance = (float)Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2));
        float x3 = x1 - ((60/distance)*(x2 - x1));
        float y3 = y1 - ((60/distance)*(y2 - y1));
        float distance2 = (float)Math.sqrt(Math.pow((x2-x3),2) + Math.pow((y2-y3),2));
        float x4 = x3 - ((60/distance2)*(x2-x3));
        float y4 = y3 + ((60/distance2)*(y2-y3));
        float x5 = x3 + ((60/distance2)*(x2-x3));
        float y5 = y3 - ((60/distance2)*(y2-y3));
        canvas.drawLine(x1, y1, x4, y4, paint);
        canvas.drawLine(x1, y1, x5, y5, paint);
        canvas.drawLine(x5, y5, x4, y4, paint);
    }
}
