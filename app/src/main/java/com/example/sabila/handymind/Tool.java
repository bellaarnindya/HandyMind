package com.example.sabila.handymind;

import java.util.List;

/**
 * Created by Sabila on 12/10/2017.
 */

public abstract class Tool {

    public abstract void touchDown(float x, float y, DrawingView drawingView);
    public abstract void touchMove(float x, float y);
    public abstract void touchUp(DrawingView drawingView);
}
