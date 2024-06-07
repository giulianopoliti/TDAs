package org.example.adt;

public interface IStackWithPriority {
    void add (int a, int priority);
    void remove ();
    boolean isEmpty();
    int getTop();
    int getPriority();

}
