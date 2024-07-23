package org.example.adt;

import java.util.Objects;

public class DynamicStackWithBuilder {
    private Node first;

    public DynamicStackWithBuilder add(int a) {
        Node node = new Node(a, this.first);
        this.first = node;
        return this;
    }

    public void remove() {
        if(this.isEmpty()) {
            throw new RuntimeException("No se puede desapilar una pila vacia");
        }
        this.first = this.first.getNext();
    }

    public int getTop() {
        if(this.isEmpty()) {
            throw new RuntimeException("No hay topes, esta vacia.");
        }
        return this.first.getValue();
    }

    public boolean isEmpty() {
        return Objects.isNull(this.first);
    }
}
