package com.example.sabila.handymind.Memento;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by nafiar on 12/12/17.
 */

public class ShapeCaretaker {
    private List<ShapeMemento> listOfShapeMemento;
    public ShapeMemento currentMemento ;
    private static int currentMementoNum;
    private final static int maxMemento = 20;

    public ShapeCaretaker() {
        listOfShapeMemento = new ArrayList<>();
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
//            remove_last();
//        }
        listOfShapeMemento.add(memento);
        currentMemento = memento;
        currentMementoNum += 1;
    }

    public boolean getUndo() {

//        ListIterator it = listOfShapeMemento.listIterator(listOfShapeMemento.indexOf(currentMemento));
//        Log.d("Debug", "index sekarang : " + listOfShapeMemento.indexOf(currentMemento));
//        if (it.hasPrevious()) {
//            int prevIndex = it.previousIndex();
//            currentMemento = listOfShapeMemento.get(prevIndex);
//            Log.d("Debug", "index sekarang : " + listOfShapeMemento.indexOf(currentMemento));
//            return currentMemento;
//        }
//        else {
//            return null;
//        }
//        ListIterator it = listOfShapeMemento.listIterator(listOfShapeMemento.indexOf(currentMemento));
        int currentIndex = listOfShapeMemento.indexOf(currentMemento);
        Log.d("Debug", "current index : " + currentIndex);
        ShapeMemento undoMemento = listOfShapeMemento.get(currentIndex-1);
        if (undoMemento != null) {
            currentMemento = undoMemento;
            Log.d("Debug", "undo index : " + listOfShapeMemento.indexOf(currentMemento));
            return true;
        }
        else {
            return false;
        }
    }

    public boolean getRedo() {

        ListIterator it = listOfShapeMemento.listIterator(listOfShapeMemento.indexOf(currentMemento));
        int currentIndex = listOfShapeMemento.indexOf(currentMemento);
        ShapeMemento redoMemento = listOfShapeMemento.get(currentIndex+1);
        if (redoMemento != null) {
            currentMemento = redoMemento;
            return true;
        }
        else {
            return false;
        }

//        Log.d("Debug", "index sekarang : " + listOfShapeMemento.indexOf(currentMemento));
//        if (it.hasNext()) {
//            ShapeMemento getNext = (ShapeMemento) it.next();
//            currentMemento = getNext;
//            Log.d("Debug", "index sekarang : " + listOfShapeMemento.indexOf(currentMemento));
//            return currentMemento;
//        }
//        else {
//            return null;
//        }
    }

    public ShapeMemento getCurrentMemento() {
        Log.d("Debug", "current index : " + listOfShapeMemento.indexOf(currentMemento));
        return currentMemento;
    }

//    public remove_last() {
//
//    }
}
