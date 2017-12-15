package com.example.sabila.handymind;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.sabila.handymind.shapes.Circle;
import com.example.sabila.handymind.shapes.Line;
import com.example.sabila.handymind.shapes.Oval;
import com.example.sabila.handymind.shapes.Rectangle;
import com.example.sabila.handymind.shapes.RoundRect;
import com.example.sabila.handymind.shapes.Text;
import com.example.sabila.handymind.tools.RectangleTool;
import com.example.sabila.handymind.tools.TextTool;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Sabila on 11/15/2017.
 */

public class DrawingView extends View {

    private List<Shape> shapes;
    private Shape touchedShape = null;
    private Shape shapeOnCreating = null;
    private Tool tool;

    private boolean isSingleTouch = false;

    private String textMessage;
    public Shape shape;
    private static boolean dashedLine = false;

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

        Log.d(TAG, "onTouchEvent: "+event.getAction());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for (int i = 0; i < shapes.size(); i++) {
                    if (shapes.get(i).isTouched(touchX, touchY)) {
                        touchedShape = shapes.get(i);

                        touchedShape.click();

                        isSingleTouch = true;

                        touchedShape.initialMove(touchX, touchY);

                        Log.i("ACTION_DOWN", "get touched shape");
                    }
                }

                if (touchedShape == null && !isSingleTouch) {
                    Log.i("ACTION_DOWN", "if touched == null");
                    Log.i("ACTION_DOWN", "  add shape");

                    Shape newShape = tool.touchDown(touchX, touchY, this);
                    shapes.add(newShape);
                    shapeOnCreating = newShape;
                    if(newShape instanceof Line && dashedLine) {
                        Log.d("DEBUG", "masuk dashed line");
                        ((Line) shapeOnCreating).setDashedLine();
                    }
                }


                invalidate();
                break;

            case MotionEvent.ACTION_MOVE:

                Log.i("ACTION_MOVE", "move");

                isSingleTouch = false;

                if (shapeOnCreating != null) {
                    tool.touchMove(touchX, touchY);
                } else if (touchedShape != null) {
                    touchedShape.move(touchX, touchY);
                }

                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                shapeOnCreating = null;

                this.tool.touchUp(this);

                if (isSingleTouch) {
                    Log.i("ACTION_UP", "clicked");
                    //touchedShape.click();
                    isSingleTouch = false;
                } else {
                    touchedShape = null;
                }

                invalidate();
                break;
        }

        return true;
    }

    public void setMessage (String messageReceived) {
        Log.i("SET_MESSAGE", "" + messageReceived);
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