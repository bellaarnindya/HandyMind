package com.example.sabila.handymind.Memento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nafiar on 12/12/17.
 */

public class ShapeCaretaker {
    private List<ShapeMemento> listOfShapeMemento;
    public ShapeMemento currentMemento ;
    public static int currentMementoNum;
    public final static int maxMemento = 20;

    public ShapeCaretaker() {
        listOfShapeMemento = new ArrayList<ShapeMemento>();
    }

    public void saveState(ShapeMemento memento) {
//        if (currentMementoNum < maxMemento) {
//            if(currentState != lasState) {
//
//            }
//            else {
//                currentMemento = currentMemento + 1;
//                listOfShapeMemento.add(memento);
//            }
//        }
//        else {
//            remove_after();
//        }
        listOfShapeMemento.add(memento);
        currentMementoNum += 1;
    }

    public ShapeMemento undo() {

        int index = listOfShapeMemento.indexOf(currentMemento);
        ShapeMemento getMemento = listOfShapeMemento.get(index-1);
        currentMemento = getMemento;

        return currentMemento;
    }

    public ShapeMemento redo() {

        int index = listOfShapeMemento.indexOf(currentMemento);
        ShapeMemento getMemento = listOfShapeMemento.get(index+1);
        currentMemento = getMemento;

        return currentMemento;
    }

//    public remove_after() {
//
//    }
}
