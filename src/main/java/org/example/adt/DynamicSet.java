package org.example.adt;

import java.util.Random;

public class DynamicSet implements ISet {

    private Node first;
    private int count;

    @Override
    public void add(int a) {
        Node current = this.first;
        while(current != null && current.getValue() != a) {
            current = current.getNext();
        }
        if(current == null) {
            this.first = new Node(a, this.first);
            this.count++;
        }
    }

    @Override
    public void remove(int a) {
        if(this.first == null) {
            return;
        }
        if(this.first.getNext() == null) {
            if(this.first.getValue() == a) {
                this.first = null;
                this.count--;
                return;
            }
            return;
        }

        if(this.first.getValue() == a) {
            this.first = this.first.getNext();
            this.count--;
            return;
        }

        Node backup = this.first;
        Node current = this.first.getNext();
        while(current != null && current.getValue() != a) {
            backup = current;
            current = current.getNext();
        }

        if(current != null) {
            backup.setNext(current.getNext());
            this.count--;
        }
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    @Override
    public int choose() {
        if(this.count == 0) {
            throw new RuntimeException("No se puede elegir un valor de un conjunto vacio");
        }
        int index = new Random().nextInt(count);
        Node current = this.first;
        for(int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getValue();
    }

}
