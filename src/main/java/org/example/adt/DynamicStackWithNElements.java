package org.example.adt;

import java.util.Objects;

public class DynamicStackWithNElements implements IStack{
    private int maxElements;
    private Node first;

    public DynamicStackWithNElements(int maxElements) {
        this.maxElements = maxElements;
    }

    @Override
    public void add(int a) {
        if (!isFull(maxElements)) {
            Node node = new Node(a, this.first);
            this.first = node;
        } else {
            throw new RuntimeException("La pila ya esta llena.");
        }
    }

    @Override
    public void remove() {
        if(this.isEmpty()) {
            throw new RuntimeException("No se puede desapilar una pila vacia");
        }
        this.first = this.first.getNext();
    }

    @Override
    public int getTop() {
        if(this.isEmpty()) {
            throw new RuntimeException("No hay topes, esta vacia.");
        }
        return this.first.getValue();
    }

    @Override
    public boolean isEmpty() {
        return Objects.isNull(this.first);
    }


    private boolean isFull (int n) {
        if (this.isEmpty()) {
            return false;
        }
        int count = 1;
        Node current = this.first;
        while(current.getNext() != null) {
            current = current.getNext();
            count++;
        }
        return count >= n;}
}
