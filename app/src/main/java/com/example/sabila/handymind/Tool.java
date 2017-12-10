package com.example.sabila.handymind;

/**
 * Created by Sabila on 12/10/2017.
 */

public abstract class Tool {
    public abstract Shape createShape(float x, float y);
    public abstract void drag(float x, float y);
}
