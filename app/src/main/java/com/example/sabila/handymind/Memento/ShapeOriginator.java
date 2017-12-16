package com.example.sabila.handymind.Memento;

import com.example.sabila.handymind.Shape;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nafiar on 12/12/17.
 */

public class ShapeOriginator {
    public List<Shape> shapeList;

    public ShapeOriginator() {
        shapeList = new ArrayList<Shape>();
    }

    public void setShapeList(List<Shape> list) {
        shapeList = new ArrayList<Shape>(list);
    }

    public ShapeMemento save() {
        return new ShapeMemento(shapeList);
    }

    public List<Shape> restore() {
        return shapeList;
    }
}
