package org.example.adt;

import java.util.List;

public interface MultipleDictionary {

    void add(int k, int v);
    void remove(int k, int v);
    ISet getKeys();
    List<Integer> get(int k);

}
