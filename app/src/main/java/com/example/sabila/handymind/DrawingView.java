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

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Sabila on 11/15/2017.
 */

public class DrawingView extends View {

    private Paint drawPaint;

    private List<Shape> shapes;
    private Shape touchedShape = null;
    private Shape shapeOnCreating = null;

    private boolean isSingleTouch = false;

    private String selectedShape, textMessage;
    public Shape shape;

    public DrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        selectedShape = "rectangle";

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

                        isSingleTouch = true;

                        touchedShape.initialMove(touchX, touchY);

                        Log.i("ACTION_DOWN", "get touched shape");
                    }
                }

                if (touchedShape == null && !isSingleTouch) {
                    Log.i("ACTION_DOWN", "if touched == null");
                    Log.i("ACTION_DOWN", "  add shape");

                    Shape newShape = createShape(touchX, touchY);
                    shapes.add(newShape);
                    shapeOnCreating = newShape;
                }


                invalidate();
                break;

            case MotionEvent.ACTION_MOVE:

                Log.i("ACTION_MOVE", "move");

                isSingleTouch = false;

                if (shapeOnCreating != null) {
                    shapeOnCreating.drag(touchX, touchY);
                } else if (touchedShape != null) {
                    touchedShape.move(touchX, touchY);
                }

                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                shapeOnCreating = null;

                if (isSingleTouch) {
                    Log.i("ACTION_UP", "clicked");
                    touchedShape.click();
                    isSingleTouch = false;
                } else {
                    touchedShape = null;
                }

                invalidate();
                break;
        }

        return true;
    }

    public void getMessage (String messageReceived) {
        Log.i("SET_MESSAGE", "" + messageReceived);
        textMessage = messageReceived;
        setSelectedShape("text");
    }

    public void setSelectedShape(String selectedShape) {
        this.selectedShape = selectedShape;
    }

    private Shape createShape(float x, float y) {
        Shape newShape = null;

        if (selectedShape.equals("rectangle")) {
            newShape = new Rectangle(x, y);
        }
        else if (selectedShape.equals("circle")) {
            newShape = new Circle(x, y);
        }
        else if (selectedShape.equals("line")) {
            newShape = new Line(x, y);
        }
        else if (selectedShape.equals("roundrect")) {
            newShape = new RoundRect(x, y);
        }
        else if (selectedShape.equals("oval")) {
            newShape = new Oval(x, y);
        }
        else if (selectedShape.equals("text")) {
            newShape = new Text(x, y, textMessage);
        }

        return newShape;
    }
}