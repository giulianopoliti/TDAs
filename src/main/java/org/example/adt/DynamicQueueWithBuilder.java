package org.example.adt;

import java.util.Objects;

public class DynamicQueueWithBuilder {

    private Node first;
    public DynamicQueueWithBuilder (int ... values) {
        for (int value: values) {
            Node node = new Node (value, this.first);
            this.first = node;
        }

    }

    public void add(int a) {
        Node node = new Node(a, this.first);
        this.first = node;
    }

    public void remove() {
        if(this.isEmpty()) {
            throw new RuntimeException("No se puede desapilar una cola vacia");
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

    public int getFirst() {
        if(this.isEmpty()) {
            throw new RuntimeException("No se puede desapilar una pila vacia");
        }
        return this.getLast().getValue();
    }

    public boolean isEmpty() {
        return Objects.isNull(this.first);
    }

    private Node getLast() {
        Node current = this.first;
        while(current.getNext() != null) {
            current = current.getNext();
        }
        return current;
    }
}
