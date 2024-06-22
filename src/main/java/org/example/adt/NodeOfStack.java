package org.example.adt;

public class NodeOfStack {

    private IStack first;

    private NodeOfStack next;


    public NodeOfStack(IStack first, NodeOfStack next) {
        this.first = first;
        this.next = next;
    }

    public IStack getFirst() {
        return first;
    }

    public void setCurrent(IStack current) {
        this.first = current;
    }

    public NodeOfStack getNext() {
        return next;
    }

    public void setNext(NodeOfStack next) {
        this.next = next;
    }
}
