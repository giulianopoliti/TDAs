package org.example.adt;

import java.util.Objects;
import java.util.Random;

public class DynamicSetWithRepeated implements ISet{
    private DictionaryNode first; // utilizamos DictionaryNode, pero podriamos crear un nuevo nodo con la misma estructura

    private int count;
    @Override
    public void add(int element) {
        DictionaryNode current = this.first;
        while (current != null && current.getKey() != element){
            current = current.getNext();
        } if (current.getKey() == element) {
            current.setValue(current.getValue()+1);
        } else if (current == null){
            this.first = new DictionaryNode(element, 1, this.first);
        }
        this.count++; // el count, cuenta cada elemento con su cantidad, es decir si el elemento 4, tiene de cantidad 2, cuenta doble
    }

    @Override
    public void remove(int element) {
        if(this.first == null) {
        return;
    }
        if(this.first.getNext() == null) {
            if(this.first.getValue() == element) {
                this.first.setValue(this.first.getValue()-1);
                if (this.first.getValue() == 0) {
                    this.first = null;
                }
                this.count--;
                return;
            }
            return;
        }

        if(this.first.getValue() == element) {
            this.first.setValue(this.first.getValue()-1);
            if (this.first.getValue() == 0){
                this.first = this.first.getNext();
            }
            this.count--;
            return;
        }

        DictionaryNode backup = this.first;
        DictionaryNode current = this.first.getNext();
        while(current != null && current.getValue() != element) {
            backup = current;
            current = current.getNext();
        }

        if(current != null) {
            current.setValue(current.getValue()-1);
            if (current.getValue() == 0) {
                backup.setNext(current.getNext());
            }
            this.count--;
        }
    }
    public void removeAllRepeated (int element) {
        if(this.first == null) {
            return;
        }
        if(this.first.getNext() == null) {
            if(this.first.getValue() == element) {
                this.count -= this.first.getValue();
                this.first = null;
                return;
            }
            return;
        }

        if(this.first.getValue() == element) {
            this.count -= this.first.getValue();
            this.first = this.first.getNext();
            return;
        }

        DictionaryNode backup = this.first;
        DictionaryNode current = this.first.getNext();
        while(current != null && current.getValue() != element) {
            backup = current;
            current = current.getNext();
        }

        if(current != null) {
            this.count -= backup.getValue();
            backup.setNext(current.getNext());
        }
    }

    @Override
    public boolean isEmpty() {
        return Objects.isNull(this.first);
    }

    @Override
    public int choose() { // preguntar sobre este, para saber si el choose devuelve la dupla, o solamente el elemento
        if(this.count == 0) {
            throw new RuntimeException("No se puede elegir un valor de un conjunto vacio");
        }
        int index = new Random().nextInt(count);
        DictionaryNode current = this.first;
        for(int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getValue();
    }
    }

