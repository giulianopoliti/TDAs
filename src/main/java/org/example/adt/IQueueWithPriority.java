package org.example.adt;

public interface IQueueWithPriority {

    void add(int a, int priority);

    void remove();

    int getFirst();
    int getPriority();

    boolean isEmpty();

}
