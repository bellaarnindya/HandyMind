package com.example.sabila.handymind.shapes;

import android.util.Log;

import com.example.sabila.handymind.Shape;

/**
 * Created by syukronrm on 26/11/17.
 */

public class ActiveState implements ShapeState {
    @Override
    public void click(Shape wrapper) {
        wrapper.setInactive();
        wrapper.setState(new InactiveState());
    }
}