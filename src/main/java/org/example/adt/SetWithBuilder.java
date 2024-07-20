package org.example.adt;

import java.util.Random;

public class SetWithBuilder implements ISet{
    private static final int MAX = 10000;

    private int[] array;
    private int count;
    private BuilderSet builderSet;

    public SetWithBuilder() {
        this.array = new int[MAX];
        this.count = 0;
        this.builderSet = new BuilderSet();
    }

    @Override
    public void add(int a) {
        builderSet.add(a);
    }
    @Override
    public void remove(int a) {
        for(int i = 0; i < count; i++) {
            if(this.array[i] == a) {
                this.array[i] = this.array[count - 1];
                count--;
                return;
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    @Override
    public int choose() {
        if(this.count == 0) {
            throw new RuntimeException("No se puede elegir un valor de un conjunto vacio");
        }
        return this.array[new Random().nextInt(this.count)];
    }
}
