package org.example.adt;

public class DynamicSearchBinaryTree implements SearchBinaryTree{
    private BinaryTree binaryTree;
    private DynamicSearchBinaryTree (BinaryTree binaryTree){
        this.binaryTree = binaryTree;
    }

    public DynamicSearchBinaryTree (int a){
        this.binaryTree = new DynamicBinaryTree(a);
    }

    @Override
    public void add(int a) {
        if (a < this.binaryTree.getRootValue()){
            if (this.binaryTree.getLeft() == null){
                this.binaryTree.addLeft(a);
            }else {
                this.getLeft().add(a);                                    /* NO TIENE QUE SER INMUTABLE, si no va a hacer una copia.*/
            }
        } else {
            if (this.binaryTree.getRight() == null) {
                this.binaryTree.addRight(a);
            } else {
                this.getRight().add(a);
            }
        }
    }

    @Override
    public int getRootValue() {
        return this.binaryTree.getRootValue();
    }

    @Override
    public SearchBinaryTree getLeft() {
        return new DynamicSearchBinaryTree(this.binaryTree.getLeft());
    }

    @Override
    public SearchBinaryTree getRight() {
        return new DynamicSearchBinaryTree(this.binaryTree.getRight());
    }

    @Override
    public void removeLeft() {
        this.binaryTree.removeLeft();
    }

    @Override
    public void removeRight() {
        this.binaryTree.removeRight();
    }
}
