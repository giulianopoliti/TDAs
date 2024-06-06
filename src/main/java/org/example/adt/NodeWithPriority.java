package org.example.adt;

public class NodeWithPriority {

    private int value;
    private int priority;
    private NodeWithPriority next;

    public NodeWithPriority(int value, int priority, NodeWithPriority next) {
        this.value = value;
        this.priority = priority;
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public NodeWithPriority getNext() {
        return next;
    }

    public void setNext(NodeWithPriority next) {
        this.next = next;
    }
}
