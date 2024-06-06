package org.example.adt;


public class SetOfSetNode {

    private ISet value;
    private SetOfSetNode next;

    public SetOfSetNode(ISet value, SetOfSetNode next) {
        this.value = value;
        this.next = next;
    }

    public ISet getValue() {
        return value;
    }

    public void setValue(ISet value) {
        this.value = value;
    }

    public SetOfSetNode getNext() {
        return next;
    }

    public void setNext(SetOfSetNode next) {
        this.next = next;
    }
}
