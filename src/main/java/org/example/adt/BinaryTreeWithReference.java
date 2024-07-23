package org.example.adt;

public class BinaryTreeWithReference implements BinaryTree{
    private BinaryTreeWithReference father;
    private int root;
    private BinaryTreeWithReference left;
    private BinaryTreeWithReference right;
    public BinaryTreeWithReference (int root){
        this.root = root;
        this.father = null;
    }
    public BinaryTreeWithReference (int root, BinaryTreeWithReference father){
        this.root = root;
        this.father = father;
    }
    public BinaryTreeWithReference getFather (){
        return this.father;
    }

    @Override
    public int getRootValue() {
        return this.root;
    }

    @Override
    public BinaryTree getLeft() {
        return this.left;
    }

    @Override
    public BinaryTree getRight() {
        return this.right;
    }

    @Override
    public void addLeft(int a) {
        if (this.left != null){
            throw new RuntimeException("Ya existe el hijo izquierdo");
        }
        this.left = new BinaryTreeWithReference(a, this);
    }

    @Override
    public void addRight(int a) {
        if (this.right != null){
            throw new RuntimeException("Ya existe el hijo izquierdo");
        }
        this.right = new BinaryTreeWithReference(a, this);
    }

    @Override
    public void removeLeft() {
        this.left = null;
    }

    @Override
    public void removeRight() {
        this.right = null;
    }

    @Override
    public boolean isEmpty() {
        return this == null;
    }
}
