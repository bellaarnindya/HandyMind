package com.example.sabila.handymind;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
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

    private static final int INVALID_POINTER_ID = -1;
    private List<Shape> shapes;
    private Shape touchedShape = null;
    private Shape shapeOnCreating = null;
    private Tool tool;

    private boolean isSingleTouch = false;

    private String textMessage;
    public Shape shape;
    private int mActivePointerId = INVALID_POINTER_ID;
//    private static boolean dashedLine = false;

    private float scalePointX, scalePointY, mPosX, mPosY, mLastTouchX, mLastTouchY;
    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;

    public DrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        tool = new RectangleTool();

        shapes = new ArrayList<>();
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        canvas.save();
        canvas.scale(mScaleFactor, mScaleFactor);
        for (int i = 0; i < shapes.size(); i++) {
            shapes.get(i).draw(canvas);
        }
        canvas.restore();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        mScaleDetector.onTouchEvent(event);

        Log.d(TAG, "onTouchEvent: "+event.getAction());
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: {
                final float x = (event.getX() - scalePointX)/mScaleFactor;
                final float y = (event.getY() - scalePointY)/mScaleFactor;

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

                    Shape newShape = tool.createShape(x - mPosX + scalePointX, y - mPosY + scalePointY);
                    shapes.add(newShape);
                    shapeOnCreating = newShape;
                }

                mLastTouchX = x;
                mLastTouchY = y;
                mActivePointerId = event.getPointerId(0);
                invalidate();
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                final int pointerIndex = event.findPointerIndex(mActivePointerId);
                final float x = event.getX(pointerIndex);
                final float y = event.getY(pointerIndex);
                Log.i("ACTION_MOVE", "move");

                isSingleTouch = false;

                if (!mScaleDetector.isInProgress()) {
                    if (shapeOnCreating != null) {
                        tool.drag(touchX, touchY);
                    } else if (touchedShape != null) {
                        touchedShape.move(touchX, touchY);
                    }

                    final float dx = x - mLastTouchX;
                    final float dy = y - mLastTouchY;

                    mPosX += dx;
                    mPosY += dy;

                    invalidate();
                }
                mLastTouchX = x;
                mLastTouchY = y;

//                invalidate();
                break;
            }


            case MotionEvent.ACTION_UP: {
                mActivePointerId = INVALID_POINTER_ID;
                shapeOnCreating = null;

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


            case MotionEvent.ACTION_POINTER_UP: {
                final int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK)
                        >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                final int pointerId = event.getPointerId(pointerIndex);
                if (pointerId == mActivePointerId) {
                    // This was our active pointer going up. Choose a new
                    // active pointer and adjust accordingly.
                    final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                    mLastTouchX = event.getX(newPointerIndex);
                    mLastTouchY = event.getY(newPointerIndex);
                    mActivePointerId = event.getPointerId(newPointerIndex);
                }
                break;
            }
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

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();

            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor,5.0f));

            invalidate();
            return true;
        }
    }

}