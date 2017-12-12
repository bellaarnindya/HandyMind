package com.example.sabila.handymind;

import java.util.List;

/**
 * Created by nafiar on 12/12/17.
 */

public class ShapeMemento {
    public List<Shape> shapeList;

    public ShapeMemento( List<Shape> shapes ){
        shapeList = shapes;
    }
}
