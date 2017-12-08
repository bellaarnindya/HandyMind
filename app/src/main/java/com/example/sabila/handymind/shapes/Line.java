package com.example.sabila.handymind.shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import com.example.sabila.handymind.LineBodyBehavior;
import com.example.sabila.handymind.LineHeadBehavior;
import com.example.sabila.handymind.Shape;
import com.example.sabila.handymind.lineBehaviors.LineStraightBody;
import com.example.sabila.handymind.lineBehaviors.LineStripedBody;
import com.example.sabila.handymind.lineBehaviors.LineWithoutHead;

/**
 * Created by Sabila on 11/28/2017.
 */

public class Line extends Shape {

    private float xStart;
    private float yStart;
    private float xEnd;
    private float yEnd;
    private float length;
    private LineBodyBehavior bodyBehavior;
    private LineHeadBehavior headBehavior;

    private Paint drawPaint;

    public Line(float x, float y) {
        this.xStart = x;
        this.yStart = y;
        this.xEnd = x;
        this.yEnd = y;
        this.bodyBehavior = new LineStraightBody();
        this.headBehavior = new LineWithoutHead();

        drawPaint = new Paint();
        drawPaint.setColor(Color.BLACK);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeWidth(7);
    }

    public float getxStart() {
        return xStart;
    }

    public void setxStart(float xStart) {
        this.xStart = xStart;
    }

    public float getyStart() {
        return yStart;
    }

    public void setyStart(float yStart) {
        this.yStart = yStart;
    }

    public float getxEnd() {
        return xEnd;
    }

    public void setxEnd(float xEnd) {
        this.xEnd = xEnd;
    }

    public float getyEnd() {
        return yEnd;
    }

    public void setyEnd(float yEnd) {
        this.yEnd = yEnd;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        this.bodyBehavior.drawBody(canvas, paint, xStart, yStart, xEnd, yEnd);
        this.headBehavior.drawHead(canvas, paint, xStart, yStart, xEnd, yEnd);
        canvas.drawLine(xStart, yStart, xEnd, yEnd, paint);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawLine(xStart, yStart, xEnd, yEnd, drawPaint);
    }

    @Override
    public void initialMove(float touchX, float touchY){

    }

    @Override
    public void move(float touchX, float touchY) {

    }

    @Override
    public void finishMove(){}

    @Override
    public void drag(float touchX, float touchY) {
        this.setxEnd(touchX);
        this.setyEnd(touchY);
    }

    @Override
    public void resize(float touchX, float touchY) {

    }

    @Override
    public boolean isTouched(float touchX, float touchY) {
        return false;
    }

    @Override
    public void setActive() {
        drawPaint.setStrokeWidth(7);
    }

    @Override
    public void setInactive() {drawPaint.setStrokeWidth(5); }

    public void setStripedLine() {
        this.bodyBehavior = new LineStripedBody();
    }
}
