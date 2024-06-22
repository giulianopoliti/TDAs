package org.example.adt;

public class StackOfNElements implements IStack {

    private int[] array;
    private int count;
    private int n;

    public StackOfNElements(int n) {
        this.n = n;
        this.array = new int[n];
        this.count = 0;
    }

    @Override
    public void add(int a) {
        if(this.count >= n) {
            throw new RuntimeException("Limite excedido");
        }
        this.array[this.count++] = a;
    }

    @Override
    public void remove() {
        if(this.isEmpty()) {
            throw new RuntimeException("Pila vacía");
        }
        this.count--;
    }

    @Override
    public int getTop() {
        if(this.isEmpty()) {
            throw new RuntimeException("Pila vacía");
        }
        return this.array[this.count - 1];
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    public boolean isFull(){
        return this.count == n;
    }
}
