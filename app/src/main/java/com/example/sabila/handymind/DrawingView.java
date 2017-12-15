package com.example.sabila.handymind;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.sabila.handymind.tools.RectangleTool;
import com.example.sabila.handymind.tools.TextTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sabila on 11/15/2017.
 */

public class DrawingView extends View {

    private List<Shape> shapes;
    private Tool tool;

    private String textMessage;
    public Shape shape;

    public DrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        tool = new RectangleTool();

        shapes = new ArrayList<>();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        for (int i = 0; i < shapes.size(); i++) {
            shapes.get(i).draw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                if (this.tool != null) {
                    this.tool.touchDown(touchX, touchY, this);
                }

                invalidate();
                break;

            case MotionEvent.ACTION_MOVE:
                if (this.tool != null) {
                    this.tool.touchMove(touchX, touchY);
                }

                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                if (this.tool != null) {
                    this.tool.touchUp(this);
                }

                invalidate();
                break;
        }

        return true;
    }

    public void setMessage (String messageReceived) {
        textMessage = messageReceived;
        ((TextTool) tool).setMessage(textMessage);
    }


    public void setActiveTool(Tool activeTool) {
        this.tool = activeTool;

    }

    public List<Shape> getShapes() {
        return this.shapes;
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

}