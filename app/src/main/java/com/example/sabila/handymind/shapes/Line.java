package com.example.sabila.handymind.shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.sabila.handymind.Shape;
import com.example.sabila.handymind.lineBehaviors.LineBodyBehavior;
import com.example.sabila.handymind.lineBehaviors.LineDashedBody;
import com.example.sabila.handymind.lineBehaviors.LineHeadBehavior;
import com.example.sabila.handymind.lineBehaviors.LineStraightBody;
import com.example.sabila.handymind.lineBehaviors.LineWithArrowHead;
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
    private final float EPSILON = 20;
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

        CircleResize startPoint = new CircleResize(xStart, yStart);
        CircleResize endPoint = new CircleResize(xEnd, yEnd);

        resizingCircle.add(startPoint);
        resizingCircle.add(endPoint);
    }

    public void setxStart(float xStart) {
        this.xStart = xStart;
    }

    public void setyStart(float yStart) {
        this.yStart = yStart;
    }

    public void setxEnd(float xEnd) {
        this.xEnd = xEnd;
    }

    public void setyEnd(float yEnd) {
        this.yEnd = yEnd;
    }

    public float getxStart() {
        return xStart;
    }

    public float getyStart() {
        return yStart;
    }

    public float getxEnd() {
        return xEnd;
    }

    public float getyEnd() {
        return yEnd;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    @Override
    public void draw(Canvas canvas) {
        this.bodyBehavior.drawBody(canvas, drawPaint, xStart, yStart, xEnd, yEnd);
        this.headBehavior.drawHead(canvas, drawPaint, xStart, yStart, xEnd, yEnd);

        if (onResize) {
            this.drawResizingCircles(canvas);
        }
    }

    @Override
    public void initialMove(float touchX, float touchY){
        xCoordsOnTouch = touchX - xStart;
        yCoordsOnTouch = touchY - yStart;
    }

    @Override
    public void move(float touchX, float touchY) {
        float diffX = (touchX - xCoordsOnTouch) - this.xStart;
        float diffY = (touchY - yCoordsOnTouch) - this.yStart;
        this.xStart = touchX - xCoordsOnTouch;
        this.yStart = touchY - yCoordsOnTouch;
        this.xEnd = this.xEnd + diffX;
        this.yEnd = this.yEnd +diffY;

        updatePoint();
    }

    private void moveStart(float touchX, float touchY) {
        setxStart(touchX);
        setyStart(touchY);
    }

    private void moveEnd(float touchX, float touchY) {
        setxEnd(touchX);
        setyEnd(touchY);
    }

    @Override
    public void finishMove(){}

    @Override
    public void resize(int selectedCircle, float touchX, float touchY) {
        switch (selectedCircle) {
            case 0 : {
                moveStart(touchX, touchY);
                break;
            }
            case 1 : {
                moveEnd(touchX, touchY);
                break;
            }
        }

        updatePoint();
    }

    @Override
    public boolean isTouched(float touchX, float touchY) {
        float m = getSlope();
        float c = yEnd - m * xEnd;
        float y = m * touchX + c;

        if (Math.abs(touchY - y) < EPSILON) {
            return true;
        }

        return false;
    }

    private float getSlope() {
        float m = (yEnd - yStart)/(xEnd - xStart);
        return m;
    }

    @Override
    public void setActive() {
        drawPaint.setStrokeWidth(7);
        onResize = true;
        this.setState(new ActiveState());
    }

    @Override
    public void setInactive() {
        drawPaint.setStrokeWidth(5);
        onResize = false;
        this.setState(new InactiveState());
    }

    public void setDashedLine() {
        this.bodyBehavior = new LineDashedBody();
    }

    public void setArrowHead() {
        this.headBehavior = new LineWithArrowHead();
    }


    public boolean intersects(float x, float y, Shape shape){
        return shape.isTouched(x, y);
    }

    @Override
    public void notifyAllObservers() {
        super.notifyAllObservers();
    }

    @Override
    public void delete() {
        this.xStart = -1;
        this.yStart = -1;
        this.xEnd = -1;
        this.yEnd = -1;
        this.length = 0;
    }

    @Override
    public void updatePoint() {
        resizingCircle.get(0).updateCoordiate(getxStart(), getyStart());
        resizingCircle.get(1).updateCoordiate(getxEnd(), getyEnd());
    }
}
