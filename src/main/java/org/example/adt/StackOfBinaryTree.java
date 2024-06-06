package org.example.adt;

public class StackOfBinaryTree {

    private static final int MAX = 10000;
    private BinaryTree[] array;
    private int count;

    public StackOfBinaryTree() {
        this.array = new BinaryTree[MAX];
        this.count = 0;
    }

    public void add(BinaryTree a) {
        if(this.count >= MAX) {
            throw new RuntimeException("Limite excedido");
        }
        this.array[this.count++] = a;
    }

    public void remove() {
        if(this.isEmpty()) {
            throw new RuntimeException("Pila vacía");
        }
        this.count--;
    }

    public BinaryTree getTop() {
        if(this.isEmpty()) {
            throw new RuntimeException("Pila vacía");
        }
        return this.array[this.count - 1];
    }

    public boolean isEmpty() {
        return this.count == 0;
    }
}
