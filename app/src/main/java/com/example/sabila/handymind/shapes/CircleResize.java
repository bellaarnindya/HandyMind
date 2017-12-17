package com.example.sabila.handymind.shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class CircleResize {
    private Paint drawPaint;
    private float radius;

    private float x;
    private float y;

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void updateCoordiate(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public CircleResize(float x, float y) {
        this.x = x;
        this.y = y;

        drawPaint = new Paint();
        drawPaint.setColor(Color.BLACK);
        drawPaint.setStyle(Paint.Style.FILL);

        radius = (float) 20;
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, drawPaint);
    }

    public boolean isTouched(float touchX, float touchY) {
        double x = Double.parseDouble(Float.toString(this.x));
        double y = Double.parseDouble(Float.toString(this.y));
        double a = Double.parseDouble(Float.toString(touchX));
        double b = Double.parseDouble(Float.toString(touchY));
        float distance = (float) Math.sqrt(Math.pow(x - a, 2) + Math.pow(y - b, 2));

        if (distance <= this.radius + 50) {
            return true;
        }
        return false;
    }
}
