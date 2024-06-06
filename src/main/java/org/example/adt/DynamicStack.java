package org.example.adt;

import java.util.Objects;

public class DynamicStack implements IStack {

    private Node first;



    @Override
    public void add(int a) {
        Node node = new Node(a, this.first);
        this.first = node;
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
}
