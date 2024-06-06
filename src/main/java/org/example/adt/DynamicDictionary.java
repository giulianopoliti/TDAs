package org.example.adt;

public class DynamicDictionary implements Dictionary {

    private DictionaryNode first;
    private int count;

    @Override
    public void add(int k, int v) {
        DictionaryNode current = this.first;
        while(current != null && current.getKey() != k) {
            current = current.getNext();
        }
        if(current == null) {
            this.first = new DictionaryNode(k, v, this.first);
            this.count++;
        }
    }

    @Override
    public void remove(int k, int v) {
        if(this.first == null) {
            return;
        }
        if(this.first.getNext() == null) {
            if(this.first.getKey() == k && this.first.getValue() == v) {
                this.first = null;
                this.count--;
            }
            return;
        }

        DictionaryNode backup = this.first;
        DictionaryNode current = this.first.getNext();
        while(current != null && current.getKey() != k) {
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
    public int get(int k) {
        if(this.first == null) {
            throw new RuntimeException("No existe un valor asociado a la clave");
        }
        DictionaryNode current = this.first;
        while(current != null) {
            if(current.getKey() == k) {
                return current.getValue();
            }
            current = current.getNext();
        }

        throw new RuntimeException("No existe un valor asociado a la clave");
    }
}
