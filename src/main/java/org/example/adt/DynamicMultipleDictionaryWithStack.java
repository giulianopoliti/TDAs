package org.example.adt;

import java.util.LinkedList;
import java.util.List;

public class DynamicMultipleDictionaryWithStack implements MultipleDictionaryWithStack{
    private KeyNode first;
    private int count;
    @Override
    public void add(int k, int v) {
        KeyNode current = this.first;
        while(current != null && current.getKey() != k) {
            current = current.getNext();
        }
        if(current == null) {
            this.first = new KeyNode(k, this.first, new Node(v, null));
            this.count++;
            return;
        }
        Node currentAux = current.getValues();
        Node newNode = new Node(v,currentAux);
        this.first.setValues(newNode);
    }
/*
    public void removeKey (int key){
        if (this.first == null){
            return;
        }
        KeyNode backup = this.first;
        KeyNode current = this.first.getNext();
        while (!getKeys().isEmpty()){
            if (backup.getKey() == key){
                backup.setNext(current.getNext());
                return;
            }
            backup = current;
            current = current.getNext();
        }
    }
    */

    //Borra el primer valor de la pila.
    @Override
    public void remove(int key) {
        if(this.first == null) {
            return;
        }
        KeyNode currentKeyNode = this.first;
        // Recorre la lista de KeyNode para encontrar el nodo con la clave
        while (currentKeyNode != null) {
            if (currentKeyNode.getKey() == key) {
                Node firstValueNode = currentKeyNode.getValues();
                if (firstValueNode != null) {
                    currentKeyNode.setValues(firstValueNode.getNext());
                    this.count--;
                }
                if (firstValueNode == null){
                    currentKeyNode = currentKeyNode.getNext();
                }
                return;  // Salir después de encontrar y eliminar el valor
            }
            currentKeyNode = currentKeyNode.getNext();
        }
        throw new RuntimeException("No se encontro la llave.");
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
    public IStack get(int k) {
        KeyNode current = this.first;
        while(current != null) {
            if(current.getKey() == k) {
                IStack stack = new DynamicStack();
                Node node = current.getValues();
                while(node != null) {
                    stack.add(node.getValue());
                    node = node.getNext();
                }
                return stack;
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
