package org.example.adt;

import org.example.clazz.Example;

import java.util.Objects;

import org.example.clazz.Example;

import java.util.Objects;
public class DynamicQueueOfStacks implements IQueueOfStacks {
    private final int numElementsOfStack;
    private int numElementsMax;
    private NodeOfStack first;

    public DynamicQueueOfStacks(int numElementsOfStack) {
        this.numElementsOfStack = numElementsOfStack;
        this.numElementsMax = (int) Math.pow(numElementsOfStack, 2);
        this.first = null;         // Inicializa first como null
    }

    public void add(int a) {
        // Si la cola está vacía, crea la primera pila.
        if (this.first == null) {
            IStack stack = new StackOfNElements(numElementsOfStack);
            NodeOfStack nodeOfStack = new NodeOfStack(stack, this.first);
            this.first = nodeOfStack;
        }
        if (numElementsMax == 0) {
            throw new RuntimeException("Limite alcanzado");
        }
        try {
            // Intenta agregar el elemento a la primera pila.
            this.first.getFirst().add(a);
            numElementsMax--;
        } catch (RuntimeException e) {
            // Si la pila está llena, crea una nueva pila y agrega el elemento a ella.
            IStack stack = new StackOfNElements(numElementsOfStack);
            NodeOfStack nodeOfStack = new NodeOfStack(stack, first);
            this.first = nodeOfStack;
            this.first.getFirst().add(a);
            numElementsMax--;
        }
    }


    @Override
    public void remove() {
        if(this.isEmpty()) {
            throw new RuntimeException("No se puede desapilar una pila vacia");
        }

        if(this.first.getNext() == null) {
            if (this.first.getFirst().isEmpty()) {
                return;
            }
        }

        NodeOfStack current = this.first;
        NodeOfStack previous = this.first.getNext();
        while(current.getNext() != null) {
            previous = current;
            current = current.getNext();
        }
        current.getFirst().remove();
        if (current.getFirst().isEmpty()) {
            if (previous != null) {
                previous.setNext(null);
            } else {
                this.first = null;
            }
        }
    }

    @Override
    public int getFirst() {
        if (this.isEmpty() || this.first.getFirst().isEmpty()) {
            throw new RuntimeException("No hay valores para obtener.");
        }
        return this.getLast().getFirst().getTop(); // Devuelve el top de la primera pila
    }

    @Override
    public boolean isEmpty() {
        return this.first == null;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int getNumElementsOfStack() {
        return numElementsOfStack;
    }


    private NodeOfStack getLast() {
        NodeOfStack current = this.first;
        while(current.getNext() != null) {
            current = current.getNext();
        }
        return current;
    }
}