package org.example.adt;

public interface Dictionary {

    void add(int k, int v);
    void remove(int k, int v);
    ISet getKeys();
    int get(int k);

}
