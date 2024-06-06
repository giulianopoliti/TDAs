package org.example.adt;

import org.example.clazz.Example;

import java.util.Random;
import java.util.Set;

public class SetOfSet {

    private SetOfSetNode first;
    private int count;

    public void add(ISet a) {
        SetOfSetNode current = this.first;
        while(current != null && !Example.equals(current.getValue(), a)) {
            current = current.getNext();
        }
        if(current == null) {
            this.first = new SetOfSetNode(a, this.first);
            this.count++;
        }
    }

    public void remove(ISet a) {
        if(this.first == null) {
            return;
        }
        if(this.first.getNext() == null) {
            if(Example.equals(this.first.getValue(), a)) {
                this.first = null;
                this.count--;
                return;
            }
            return;
        }

        SetOfSetNode backup = this.first;
        SetOfSetNode current = this.first.getNext();
        while(current != null && !Example.equals(current.getValue(), a)) {
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

    public ISet choose() {
        if(this.count == 0) {
            throw new RuntimeException("No se puede elegir un valor de un conjunto vacio");
        }
        int index = new Random().nextInt(count);
        SetOfSetNode current = this.first;
        for(int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getValue();
    }
}
