package com.example.sabila.handymind.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.sabila.handymind.Shape;

/**
 * Created by Panji Rimawan on 11/29/2017.
 */

public class Text extends Shape {

    private String textInput;
    private float x;
    private float y;

    public Text(float x, float y, String inputMessage) {
        this.x = x;
        this.y = y;
        this.textInput = inputMessage;
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
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawText(textInput, x, y, paint);
    }
}
