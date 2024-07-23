package org.example.adt;

public interface BinaryTree {
    int getRootValue();
    BinaryTree getLeft();
    BinaryTree getRight();
    void addLeft (int a);
    void addRight (int a);
    void removeLeft ();
    void removeRight ();
    boolean isEmpty();

}
