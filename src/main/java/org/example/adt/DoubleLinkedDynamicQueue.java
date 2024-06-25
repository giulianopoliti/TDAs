package org.example.adt;

public class DoubleLinkedDynamicQueue implements IQueue{
    private DoubleLinkedNode first;
    private DoubleLinkedNode last;

    @Override
    public void add(int a) {
        DoubleLinkedNode doubleLinkedNode = new DoubleLinkedNode(a,this.first, this.last);
        this.first = doubleLinkedNode;
    }

    @Override
    public void remove() {
        if (this.first.getNext() == null) {
            this.first = null;
            return;
        }

    }

    @Override
    public int getFirst() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
