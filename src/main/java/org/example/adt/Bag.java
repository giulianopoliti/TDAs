package org.example.adt;

import java.util.Random;

public class Bag implements ISet{
    private static final int MAX = 10000;
    private int c;
    private int[] array;
    private int count;

    public Bag(int c) {
        this.c = c;
        this.array = new int [MAX];
        this.count = 0;
    }

    @Override
    public void add (int a) { // O(C+C+C+C+C+C+C+C+C+C) = O(C) complejidad constante
        if ((a < 0 && -a > c) || a > c) { // O(C+C+C)
            throw new RuntimeException("Bag llena, te sobran unicamente " + (c) + " elementos."); // O(C)
        }
        if (a < 0) { //O(C)
            c -= -a; //O(C)
        }
        else { //O(C)
            c -= a; //O(C)
        }
        this.array[count] = a; //O(C)
        count++; //O(C)
    }

    @Override
    public void remove(int a) {
        for(int i = 0; i < count; i++) {
            if(this.array[i] == a) {
                this.array[i] = this.array[count - 1];
                count--;
                if (a < 0) {
                    this.c += a;
                } else {
                    this.c -= a;
                }
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
            throw new RuntimeException("No hay valores en la mochila.");
        }
        return this.array[new Random().nextInt(this.count)];
    }
}
