package org.example.adt;

public interface SearchBinaryTree {
    int getRootValue();
    SearchBinaryTree getLeft();
    SearchBinaryTree getRight();
    void add (int a);
    void removeLeft ();
    void removeRight ();
    boolean isEmpty();
}
