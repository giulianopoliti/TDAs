package org.example.adt;

public class StaticDictionary implements Dictionary  {

    private static final int MAX = 10000;

    private int[][] array;
    private int count;

    public StaticDictionary() {
        this.array = new int[MAX][2];
        this.count = 0;
    }

    @Override
    public void add(int k, int v) {
        if(count == MAX) {
            throw new RuntimeException("Limite de elementos alcanzado");
        }
        for(int i = 0; i < count; i++) {
            if(this.array[i][0] == k) {
                return;
            }
        }
        this.array[count] = new int[2];
        this.array[count][0] = k;
        this.array[count][1] = v;
        this.count++;
    }

    @Override
    public void remove(int k, int v) {
        for(int i = 0; i < count; i++) {
            if(this.array[i][0] == k) {
                if(this.array[i][1] == v) {
                    this.array[i] = this.array[count - 1];
                    count--;
                }
                return;
            }
        }
    }

    @Override
    public ISet getKeys() {
        ISet set = new StaticSet();
        for(int i = 0; i < count; i++) {
            set.add(this.array[i][0]);
        }
        return set;
    }

    @Override
    public int get(int k) {
        for(int i = 0; i < count; i++) {
            if(this.array[i][0] == k) {
                return array[i][1];
            }
        }
        throw new RuntimeException("No existe un valor asociado a la clave");
    }
}
