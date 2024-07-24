package org.example.clazz.finalprograII;

import org.example.adt.DynamicSet;
import org.example.adt.ISet;
import org.example.adt.KeyNode;
import org.example.adt.Node;
import org.example.clazz.finalprograII.IMultipleDictionary;

import java.util.LinkedList;
import java.util.List;

public class SpecialMultipleDictionary implements IMultipleDictionary {

    private KeyNode first;
    private int count;

    @Override
    public void add(int key, int value) {
        KeyNode current = this.first;
        while(current != null && current.getKey() != key) {
            current = current.getNext();
        }
        if(current == null) {
            this.first = new KeyNode(key, this.first, new Node(value, null));
            this.count++;
            return;
        }

        Node currentAux = current.getValues();
        while(currentAux.getNext() != null) {
            currentAux = currentAux.getNext();
        }
        currentAux.setNext(new Node(value, null));
    }

    @Override
    public void remove(int key, int value) {
        if(this.first == null) {
            return;
        }
        if(this.first.getNext() == null) {
            if(this.first.getKey() == key) {
                if(this.first.getValues().getNext() == null) {
                    if(this.first.getValues().getValue() == value) {
                        this.first = null;
                        this.count--;
                        return;
                    }
                }
                this.delete(this.first.getValues(), value);
                return;
            }
            return;
        }

        if(this.first.getKey() == key) {
            if(this.first.getValues().getNext() == null) {
                if(this.first.getValues().getValue() == value) {
                    this.first = this.first.getNext();
                    this.count--;
                }
                return;
            }
            this.delete(this.first.getValues(), value);
            return;
        }

        KeyNode backup = this.first;
        KeyNode current = this.first.getNext();
        while(current != null && current.getKey() != key) {
            backup = current;
            current = current.getNext();
        }

        if(current != null) {
            if(current.getKey() == key) {
                if(current.getValues().getNext() == null) {
                    if(current.getValues().getValue() == value) {
                        backup.setNext(current.getNext());
                        this.count--;
                    }
                    return;
                }
                this.delete(this.first.getValues(), value);
            }
        }
    }

    @Override
    public ISet getKeys() {
        ISet set = new DynamicSet();
        if(this.first == null) {
            return set;
        }

        KeyNode current = this.first;
        while(current.getNext() != null) {
            set.add(current.getKey());
            current = current.getNext();
        }

        set.add(current.getKey());

        return set;
    }

    @Override
    public int get(int key) {
        // devuelve el primer valor
        KeyNode current = this.first;
        while(current != null) {
            if(current.getKey() == key) {
                List<Integer> list = new LinkedList<>();
                Node node = current.getValues();
                while(node != null) {
                    list.add(node.getValue());
                    node = node.getNext();
                }
                return list.get(0);
            }
            current = current.getNext();
        }
        throw new RuntimeException("No se encontro la clave");
    }

    @Override
    public List<Integer> getValues(int k) {
        KeyNode current = this.first;
        while(current != null) {
            if(current.getKey() == k) {
                List<Integer> list = new LinkedList<>();
                Node node = current.getValues();
                while(node != null) {
                    list.add(node.getValue());
                    node = node.getNext();
                }
                return list;
            }
            current = current.getNext();
        }
        throw new RuntimeException("No se encontro la clave");
    }


    private void delete(Node node, int value) {
        Node backup = node;
        Node current = node.getNext();
        while(current.getNext() != null) {
            if(current.getValue() == value) {
                backup.setNext(current.getNext());
                return;
            }
            backup = current;
            current = current.getNext();
        }
        if(current.getValue() == value) {
            backup.setNext(current.getNext());
        }
    }
}
