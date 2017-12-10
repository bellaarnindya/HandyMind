package com.example.sabila.handymind.tools;

import com.example.sabila.handymind.Shape;
import com.example.sabila.handymind.Tool;
import com.example.sabila.handymind.shapes.Text;

/**
 * Created by Sabila on 12/10/2017.
 */

public class TextTool extends Tool {

    private Text text;
    private String message;

    @Override
    public Shape createShape(float x, float y) {
        text = new Text(x, y, message);
        return text;
    }

    @Override
    public void drag(float x, float y) {
        text.setX(x);
        text.setY(y);
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
