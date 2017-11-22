package com.example.sabila.handymind.shapes;

import android.graphics.Rect;

import com.example.sabila.handymind.Shape;

/**
 * Created by Sabila on 11/20/2017.
 */

public class Rectangle extends Shape {

    private float x;
    private float y;
    private float width;
    private float height;

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

    public float getWidth() {
        return width;
    }

    public Rectangle(float x, float y) {

        this.x = x;
        this.y = y;
    }


    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
    
}
