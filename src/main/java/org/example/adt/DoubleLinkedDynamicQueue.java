package org.example.adt;

import java.util.Objects;

public class DoubleLinkedDynamicQueue implements IQueue{
    private DoubleLinkedNode first;
    private DoubleLinkedNode last;
    @Override
    public void add(int a) {
        DoubleLinkedNode doubleLinkedNode = new DoubleLinkedNode(a, null, null);
        if (this.isEmpty()) {
            first = last = doubleLinkedNode;
            first.setNext(first);
            first.setPrevious(first);
        } else {
            doubleLinkedNode.setPrevious(last);
            doubleLinkedNode.setNext(first);
            last.setNext(doubleLinkedNode);
            first.setPrevious(doubleLinkedNode);
            last = doubleLinkedNode;
        }
    }

    @Override
    public void remove() {
        if (this.isEmpty()) {
            throw new RuntimeException("No se puede desapilar una cola vacía");
        }
        if (first == last) {
            first = last = null;
        } else {
            first = first.getNext();
            first.setPrevious(last);
            last.setNext(first);
        }
    }

    @Override
    public int getFirst() {
        if (this.isEmpty()) {
            throw new RuntimeException("No se puede obtener ningun valor de una cola vacia.");
        }
        return this.first.getValue();
    }
    public int getLast (){
        if (this.isEmpty()) {
            throw new RuntimeException("No se puede obtener ningun valor de una cola vacia.");
        }
        return this.last.getValue();
    }

    @Override
    public boolean isEmpty() {
        return Objects.isNull(this.first);
    }
}
