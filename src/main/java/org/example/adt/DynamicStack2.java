package org.example.adt;

import java.util.Objects;

public class DynamicStack2 implements IStack {

    private Node first;


    @Override
    public void add(int a) {
        if(this.isEmpty()) {
            this.first = new Node(a, this.first);
            return;
        }

        this.getLast().setNext(new Node(a, null));
    }

    @Override
    public void remove() {
        if(this.isEmpty()) {
            throw new RuntimeException("No se puede desapilar una pila vacia");
        }

        if(this.first.getNext() == null) {
            this.first = null;
            return;
        }

        Node candidate = this.first;
        Node current = this.first.getNext();
        while(current.getNext() != null) {
            candidate = current;
            current = current.getNext();
        }

        candidate.setNext(null);
    }

    @Override
    public int getTop() {
        if(this.isEmpty()) {
            throw new RuntimeException("No se puede desapilar una pila vacia");
        }
        return getLast().getValue();
    }

    private Node getLast() {
        Node current = this.first;
        while(current.getNext() != null) {
            current = current.getNext();
        }
        return current;
    }

    @Override
    public boolean isEmpty() {
        return Objects.isNull(this.first);
    }
}
