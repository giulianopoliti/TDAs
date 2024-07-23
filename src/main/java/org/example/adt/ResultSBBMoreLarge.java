package org.example.adt;

public class ResultSBBMoreLarge {
    private BinaryTree binaryTree;
    private int count;


    public ResultSBBMoreLarge(BinaryTree binaryTree, int count) {
        this.binaryTree = binaryTree;
        this.count = count;
    }

    public BinaryTree getBinaryTree() {
        return binaryTree;
    }

    public void setBinaryTree(BinaryTree binaryTree) {
        this.binaryTree = binaryTree;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
