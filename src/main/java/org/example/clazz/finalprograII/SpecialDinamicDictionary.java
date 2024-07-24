package org.example.clazz.finalprograII;

import org.example.adt.DictionaryNode;
import org.example.adt.DynamicSet;
import org.example.adt.ISet;
import org.example.clazz.finalprograII.ISpecialDictionary;

public class SpecialDinamicDictionary implements ISpecialDictionary {
    private DictionaryNode first;
    private int count;
    @Override
    public void add(int key, int value) {
        DictionaryNode current = this.first;
        while(current != null && current.getKey() != key) {
            current = current.getNext();
        }
        if(current == null) {
            this.first = new DictionaryNode(key, value, this.first);
            this.count++;
        }
    }

    @Override
    public void remove(int key, int value) {

        if(this.first == null) {
            return;
        }
        if(this.first.getNext() == null) {
            if(this.first.getKey() == key && this.first.getValue() == value) {
                this.first = null;
                this.count--;
            }
            return;
        }

        DictionaryNode backup = this.first;
        DictionaryNode current = this.first.getNext();
        while(current != null && current.getKey() != key) {
            backup = current;
            current = current.getNext();
        }

        if(current != null) {
            backup.setNext(current.getNext());
            this.count--;
        }
    }

    @Override
    public ISet getKeys() {

        if(this.first == null) {
            return new DynamicSet();
        }
        ISet keys = new DynamicSet();
        DictionaryNode current = this.first;
        while(current != null) {
            keys.add(current.getKey());
            current = current.getNext();
        }

        return keys;
    }

    @Override
    public int get(int key) {

        if(this.first == null) {
            throw new RuntimeException("No existe un valor asociado a la clave");
        }
        DictionaryNode current = this.first;
        while(current != null) {
            if(current.getKey() == key) {
                return current.getValue();
            }
            current = current.getNext();
        }

        throw new RuntimeException("No existe un valor asociado a la clave");
    }
}
