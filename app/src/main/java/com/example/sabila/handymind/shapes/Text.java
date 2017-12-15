package com.example.sabila.handymind.shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.example.sabila.handymind.Shape;

/**
 * Created by Panji Rimawan on 11/29/2017.
 */

public class Text extends Shape {

    private String textInput;
    private float x;
    private float y;

    private Paint drawPaint;

    public Text(float x, float y, String inputMessage) {
        this.x = x;
        this.y = y;
        this.textInput = inputMessage;

        drawPaint = new Paint();

        drawPaint.setColor(Color.BLACK);
        drawPaint.setStyle(Paint.Style.FILL);
        drawPaint.setTextAlign(Paint.Align.CENTER);
        drawPaint.setTextSize(50);
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

    public String getTextInput() {
        return textInput;
    }

    public void setTextInput(String textInput) {
        this.textInput = textInput;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawText(textInput, x, y, drawPaint);
    }

    @Override
    public void initialMove(float touchX, float touchY) {
        xCoordsOnTouch = touchX - x;
        yCoordsOnTouch = touchY - y;
    }

    @Override
    public void move(float touchX, float touchY) {
        this.x = touchX - xCoordsOnTouch;
        this.y = touchY - yCoordsOnTouch;
    }

    @Override
    public void finishMove(){}

    @Override
    public void resize(int selectedCircle, float x, float y) {}

    @Override
    public boolean isTouched(float touchX, float touchY) {
        double x = Double.parseDouble(Float.toString(this.getX()));
        double y = Double.parseDouble(Float.toString(this.getY()));
        double a = Double.parseDouble(Float.toString(touchX));
        double b = Double.parseDouble(Float.toString(touchY));
        float distance = (float) Math.sqrt(Math.pow(x - a, 2) + Math.pow(y - b, 2));

        if (distance <= 50.0) {
            return true;
        }

        return false;
    }

    @Override
    public void setActive() {
        drawPaint.setStrokeWidth(7);
    }

    @Override
    public void setInactive() {
        drawPaint.setStrokeWidth(5);
    }

    @Override
    public float getRightX() {
        return 0;
    }

    @Override
    public float getRightY() {
        return 0;
    }

    @Override
    public float getLeftX() {
        return 0;
    }

    @Override
    public float getLeftY() {
        return 0;
    }
}
