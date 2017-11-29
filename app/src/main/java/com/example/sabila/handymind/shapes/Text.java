package com.example.sabila.handymind.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.sabila.handymind.Shape;

/**
 * Created by Panji Rimawan on 11/29/2017.
 */

public class Text extends Shape {

    private String textInput="Testing";
    private float x;
    private float y;

    public Text(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawText(textInput, x, y, paint);
    }
}
