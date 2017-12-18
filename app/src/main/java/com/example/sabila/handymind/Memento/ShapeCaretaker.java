package com.example.sabila.handymind.Memento;

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
    private final static int maxMemento = 20;

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
            }
            else {
                ListIterator<ShapeMemento> it = listOfShapeMemento.listIterator(listOfShapeMemento.indexOf(currentMemento));
                while(it.hasNext()) {
                    it.next();
                    it.remove();
                }
                listOfShapeMemento.add(memento);
                currentMemento = memento;
                currentMementoNum += 1;
            }
        }
        else {
            listOfShapeMemento.remove(0);
            listOfShapeMemento.add(memento);
            currentMemento = memento;
            currentMementoNum += 1;
        }
    }

    public ShapeMemento getUndo() {

        ListIterator<ShapeMemento> it = listOfShapeMemento.listIterator(listOfShapeMemento.indexOf(currentMemento));
        if (it.hasPrevious()) {
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
