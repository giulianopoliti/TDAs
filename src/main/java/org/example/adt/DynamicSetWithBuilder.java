package org.example.adt;

import org.example.clazz.Example;

import java.util.Random;

public class DynamicSetWithBuilder{
    private Node first;
    private int count;

    public DynamicSetWithBuilder addAll(ISet set) {
        ISet copy = Example.copy(set);
        while (!copy.isEmpty()) {
            int value = copy.choose();
            this.add(value);
            copy.remove(value);
        }
        return this;
    }
    public DynamicSetWithBuilder add(int a) {
        Node current = this.first;
        while(current != null && current.getValue() != a) {
            current = current.getNext();
        }
        if(current == null) {
            this.first = new Node(a, this.first);
            this.count++;
        } return this;
    }

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

    public boolean isEmpty() {
        return this.count == 0;
    }

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
