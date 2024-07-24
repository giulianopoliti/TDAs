package org.example.clazz.finalprograII;

import org.example.adt.ISet;

public interface ISpecialDictionary {
    void add(int key, int value);
    void remove(int key, int value);
    ISet getKeys();
    int get(int key);
}
