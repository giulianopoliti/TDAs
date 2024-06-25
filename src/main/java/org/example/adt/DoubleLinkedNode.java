package org.example.adt;

public class DoubleLinkedNode {


    private int value;
    private DoubleLinkedNode next;
    private DoubleLinkedNode previous;

    public DoubleLinkedNode(int value, DoubleLinkedNode next, DoubleLinkedNode previous) {
        this.value = value;
        this.next = next;
        this.previous = previous;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public DoubleLinkedNode getNext() {
        return next;
    }

    public void setNext(DoubleLinkedNode next) {
        this.next = next;
    }

    public DoubleLinkedNode getPrevious() {
        return previous;
    }

    public void setPrevious(DoubleLinkedNode previous) {
        this.previous = previous;
    }
}
