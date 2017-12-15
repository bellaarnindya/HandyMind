package com.example.sabila.handymind.tools;

import com.example.sabila.handymind.DrawingView;
import com.example.sabila.handymind.Shape;
import com.example.sabila.handymind.Tool;
import com.example.sabila.handymind.shapes.Text;

import java.util.List;

/**
 * Created by Sabila on 12/10/2017.
 */

public class TextTool extends Tool {

    private Text text;
    private String message;

    @Override
    public Shape touchDown(float x, float y, DrawingView drawingView) {
        text = new Text(x, y, message);
        drawingView.addShape(text);
        return text;
    }

    @Override
    public void touchMove(float x, float y) {

    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void touchUp(DrawingView drawingView) {

    }
}
