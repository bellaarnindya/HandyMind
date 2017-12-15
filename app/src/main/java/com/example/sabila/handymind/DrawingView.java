package com.example.sabila.handymind;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.sabila.handymind.Memento.ShapeCaretaker;
import com.example.sabila.handymind.Memento.ShapeMemento;
import com.example.sabila.handymind.Memento.ShapeOriginator;
import com.example.sabila.handymind.shapes.ActiveState;
import com.example.sabila.handymind.tools.RectangleTool;
import com.example.sabila.handymind.tools.TextTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sabila on 11/15/2017.
 */

public class DrawingView extends View {

    private List<Shape> shapes;
    private Shape touchedShape = null;
    private Shape shapeOnCreating = null;
    private Tool tool;

    private boolean isSingleTouch = false;
    private boolean isResizing = false;
    private boolean isMoving = false;

    private String textMessage;
    public Shape shape;

    private int selectedCircle = -1;

    private ShapeCaretaker caretaker;
    private ShapeOriginator originator;

    public DrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        tool = new RectangleTool();
        shapes = new ArrayList<>();
        caretaker = new ShapeCaretaker();
        originator = new ShapeOriginator();
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

        boolean touchOnShape = false;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (touchedShape != null &&
                        (selectedCircle = touchedShape.isResizeTouched(touchX, touchY)) != -1) {
//                    Log.i("DrawingView", "isResizing = true");
                    isResizing = true;
                } else {
                    for (int i = 0; i < shapes.size(); i++) {
                        if (shapes.get(i).isTouched(touchX, touchY)) {
                            touchedShape = shapes.get(i);
                            touchedShape.setActive();

                            touchOnShape = true;

                            isSingleTouch = true;
                            isMoving = true;

//                            Log.i("DrawingView", "touched shape " + i);
//                            Log.i("DrawingView", "Set Active shape " + i);

                            touchedShape.initialMove(touchX, touchY);
                        } else {
//                            Log.i("DrawingView", "Set Inactive shape " + i);
                            shapes.get(i).setInactive();
                        }
                    }
                }

                if (!touchOnShape && !isResizing) {
//                    Log.i("DrawingView", "touchedShape = null");
                    touchedShape = null;
                }

                if (touchedShape == null && !isSingleTouch) {
                    Shape newShape = tool.createShape(touchX, touchY);
                    shapes.add(newShape);
                    shapeOnCreating = newShape;
                    saveShapeState();
                }

                invalidate();
                break;

            case MotionEvent.ACTION_MOVE:
//                Log.i("MOVE", "isMoving");
                isSingleTouch = false;

                if (isResizing) {
                    touchedShape.resize(selectedCircle, touchX, touchY);
                } else if (shapeOnCreating != null) {
                    tool.drag(touchX, touchY);
                } else if (isMoving) {
                    touchedShape.move(touchX, touchY);
                }

                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                shapeOnCreating = null;

                if (isSingleTouch) {
                    isSingleTouch = false;
                }

                isResizing = false;

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

    public void deleteShape() {

        if (touchedShape != null && touchedShape.currentState instanceof ActiveState) {
            
            int indexOfDelete = shapes.indexOf(touchedShape);
            touchedShape = null;
            shapes.remove(indexOfDelete);
            saveShapeState();

            invalidate();
        }
    }

    public void saveShapeState() {
        originator.setShapeList(shapes);
        caretaker.saveState( originator.save() );
    }

    public void undo() {
        Log.d("Debug", "masuk fungsi undo");

        if (caretaker.getUndo()) {
            Log.d("Debug", "bisa undo");
            shapes = new ArrayList<>();
            shapes = caretaker.getCurrentMemento().shapeList;
            originator.setShapeList(shapes);
            invalidate();
        }
        else {
            Log.d("Debug", "undo memento kosong");
            shapes = originator.restore();
            setFalse();
            invalidate();
        }

        invalidate();
    }

    public void redo() {

        ShapeMemento coba = caretaker.getCurrentMemento();
        shapes = coba.shapeList;
//        invalidate();
//        ShapeMemento redoMemento = caretaker.getRedo();
//        if (redoMemento != null) {
//            shapes = redoMemento.shapeList;
//            originator.setShapeList(shapes);
//            setFalse();
//        }
//        else {
//            shapes = originator.restore();
//            setFalse();
//        }


        invalidate();
    }

    private void setFalse() {
        touchedShape = null;
        shapeOnCreating = null;
        isSingleTouch = false;
        isResizing = false;
        isMoving = false;
    }
}