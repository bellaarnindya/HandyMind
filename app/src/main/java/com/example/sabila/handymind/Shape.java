package com.example.sabila.handymind;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.UUID;

/**
 * Created by Sabila on 11/20/2017.
 */

public abstract class Shape {

    private UUID ID;

    public Shape() {
        this.ID = UUID.randomUUID();
    }

    public UUID getID() {
        return ID;
    }

    public abstract void draw(Canvas canvas, Paint paint);
}
