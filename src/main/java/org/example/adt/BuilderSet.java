package org.example.adt;

public class BuilderSet {

    private static final int MAX = 10000;

    private int[] array;
    private int count;
    public BuilderSet () {
        this.array = new int[MAX];
        this.count = 0;
    }

    public BuilderSet add (int a) {
        if(count == MAX) {
            throw new RuntimeException("Limite de elementos alcanzado");
        }
        for(int i = 0; i < count; i++) {
            if(this.array[i] == a) {
                return this;
            }
        }
        this.array[count] = a;
        this.count++;
        return this;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
