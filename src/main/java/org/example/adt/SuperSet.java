package org.example.adt;

import java.util.Random;

public class SuperSet implements ISet{
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

    public boolean isSubSet (ISet set) {
        ISet copySuperSet = copy(this);
        ISet copyPosibleSubSet = copy(set);
        while (!copyPosibleSubSet.isEmpty()) {
            int value = copyPosibleSubSet.choose();
            if (!in(value,copySuperSet)) {
                return false;
            }
            copyPosibleSubSet.remove(value);
        }
        return true;
    }

    public ISet complement (ISet set) {
        if (!isSubSet(set)) {
            throw new RuntimeException("No se puede hacer, el conjunto dado no es subconjunto de superset");
        }
        ISet copy = copy(this);
        ISet complement = new DynamicSet();
        while(!copy.isEmpty()) {
            int value = copy.choose();
            if(!in(value, copy)) {
                complement.add(value);
            }
            copy.remove(value);
        }
        return complement;
    }

    private ISet copy (ISet set) {
        // primero hacemos una copia de cada uno
        ISet copy = new DynamicSet();
        ISet copy2 = new DynamicSet();
        while (!set.isEmpty()){
            int value = set.choose();
            copy.add(value);
            copy2.add(value);
            set.remove(value);
        }
        while (!copy2.isEmpty()){
            int value = copy2.choose();
            set.add(value);
            copy2.remove(value);
        }
        return copy;
    }

    private  boolean in(int a, ISet set) {
        ISet copy = copy(set);
        while(!copy.isEmpty()) {
            int value = copy.choose();
            if(value == a) {
                return true;
            }
            copy.remove(value);
        }

        return false;
    }
}
