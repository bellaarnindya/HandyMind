package com.example.sabila.handymind.Memento;

import com.example.sabila.handymind.Shape;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nafiar on 12/12/17.
 */

public class ShapeOriginator {
    public List<Shape> shapeList;

    public ShapeOriginator(List<Shape> list) {
        shapeList = new ArrayList<>();
        shapeList = list;
    }

    public void setShapeList(List<Shape> list) {
        shapeList = list;
    }

    public ShapeMemento save() {
        return new ShapeMemento(shapeList);
    }

    public void restore(ShapeMemento memento) {
        shapeList = memento.shapeList;
    }
}
