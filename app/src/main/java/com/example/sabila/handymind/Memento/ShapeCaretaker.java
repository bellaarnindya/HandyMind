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
    private static int firstIndex;
    private static int lastIndex;
    private static int currentIndex;
    private static int currentMementoNum;
    private final static int maxMemento = 10;

    public ShapeCaretaker() {
        listOfShapeMemento = new ArrayList<ShapeMemento>();
        firstIndex = 1;
        currentIndex = 0;
        lastIndex = 0;
    }

    public void saveState(ShapeMemento memento) {
        if (currentMementoNum < maxMemento) {
            if(listOfShapeMemento.indexOf(currentMemento) == listOfShapeMemento.size()-1) {
                listOfShapeMemento.add(memento);
                currentMemento = memento;
                currentMementoNum += 1;
                Log.d("Debug", "current index : " + listOfShapeMemento.indexOf(currentMemento) + " ukuran list : " + listOfShapeMemento.size());
            }
            else {
                ListIterator<ShapeMemento> it = listOfShapeMemento.listIterator(listOfShapeMemento.indexOf(currentMemento));
//                for(ListIterator<ShapeMemento> it = listOfShapeMemento.listIterator(listOfShapeMemento.indexOf(currentMemento)); it.hasNext(); ) {
////                    listOfShapeMemento.remove(i);
//                    it.next();
//                    it.remove();
//                    currentMementoNum -= 1;
//                    Log.d("Debug", "current index : " + it.previousIndex());
//                }
                while(it.hasNext()) {
//                    ShapeMemento a = it.next();
//                    listOfShapeMemento.remove(a);
                    it.next();
                    it.remove();
                }
                listOfShapeMemento.add(memento);
                currentMemento = memento;
                currentMementoNum += 1;
//                currentIndex += 1;
//                lastIndex = currentIndex;
                Log.d("Debug", "current index : " + listOfShapeMemento.indexOf(currentMemento) + " ukuran list : " + listOfShapeMemento.size());
            }
        }
        else {
            listOfShapeMemento.remove(0);
            listOfShapeMemento.add(memento);
            currentMemento = memento;
            currentMementoNum += 1;
            Log.d("Debug", "current index : " + listOfShapeMemento.indexOf(currentMemento) + " ukuran list : " + listOfShapeMemento.size());
        }
    }

    public ShapeMemento getUndo() {

        Log.d("Debug", "current index : " + currentIndex + " currentmemento index : " + listOfShapeMemento.indexOf(currentMemento));
        ListIterator<ShapeMemento> it = listOfShapeMemento.listIterator(listOfShapeMemento.indexOf(currentMemento));
        if (it.hasPrevious()) {
            Log.d("Debug", "previous index : " + it.previousIndex() + " current index : " + currentIndex + " ukuran list ; " + listOfShapeMemento.size());
            currentMemento = listOfShapeMemento.get(it.previousIndex());
            return currentMemento;
        }
        else {
            return null;
        }
    }

    public ShapeMemento getRedo() {

        ListIterator<ShapeMemento> it = listOfShapeMemento.listIterator(listOfShapeMemento.indexOf(currentMemento));
        if (it.hasNext()) {
            it.next();
            if (it.hasNext()) {
                Log.d("Debug", "previous index : " + it.nextIndex() + " first index : " + firstIndex + " ukuran list ; " + listOfShapeMemento.size());
                currentMemento = listOfShapeMemento.get(it.nextIndex());
            }
            else {
                currentMemento = listOfShapeMemento.get(it.previousIndex());
            }
            return currentMemento;
        }
        else {
            return null;
        }
    }

}
