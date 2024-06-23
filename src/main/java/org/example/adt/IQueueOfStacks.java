package org.example.adt;

public interface IQueueOfStacks {
    void add(int a);

    void remove();

    int getFirst();

    boolean isEmpty();
    int getNumElementsOfStack();
}
