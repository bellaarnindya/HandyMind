package com.example.sabila.handymind;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by nafiar on 08/12/2017.
 */

public abstract class LineHeadBehavior {

    public abstract void drawHead(Canvas canvas, Paint paint, float xStart, float yStart, float xEnd, float yEnd);
}
