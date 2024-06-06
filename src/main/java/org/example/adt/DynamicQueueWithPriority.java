package org.example.adt;

import java.util.Objects;

public class DynamicQueueWithPriority implements IQueueWithPriority {

    private NodeWithPriority first;


    @Override
    public void add(int a, int priority) {
        if(this.first == null || this.first.getPriority() > priority) {
            this.first = new NodeWithPriority(a, priority, this.first);
            return;
        }

        NodeWithPriority last = this.getLast();
        if(last.getPriority() < priority) {
            last.setNext(new NodeWithPriority(a, priority, null));
            return;
        }

        NodeWithPriority candidate = this.first;
        NodeWithPriority current = this.first.getNext();
        while(current != null) {
            if(current.getPriority() > priority) {
                candidate.setNext(new NodeWithPriority(a, priority, current));
                break;
            }
            candidate = current;
            current = current.getNext();
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
    public int getFirst() {
        if(this.isEmpty()) {
            throw new RuntimeException("No se puede desapilar una pila vacia");
        }
        return this.first.getValue();
    }

    @Override
    public int getPriority() {
        return this.first.getPriority();
    }


    @Override
    public boolean isEmpty() {
        return Objects.isNull(this.first);
    }

    private NodeWithPriority getLast() {
        NodeWithPriority current = this.first;
        while(current.getNext() != null) {
            current = current.getNext();
        }
        return current;
    }
}
