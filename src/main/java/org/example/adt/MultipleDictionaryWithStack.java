package org.example.adt;

import java.util.List;

public interface MultipleDictionaryWithStack {
    void add(int k, int v);
    void remove(int k, int v);
    ISet getKeys();
    IStack get(int k);
    void removeKey(int k);
}
