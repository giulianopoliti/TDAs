package org.example.adt;

public class SearchBinaryTreeWithCount implements SearchBinaryTree{
    private int root;
    private SearchBinaryTreeWithCount left;
    private SearchBinaryTreeWithCount right;
    private int count;

    public SearchBinaryTreeWithCount (int root){
        this.root = root;
    }
    @Override
    public void add(int a) {
        if (a < this.getRootValue()){
            if (this.getLeft() == null){
                this.left = new SearchBinaryTreeWithCount(a);
                this.left.count++;
            }else {
                this.getLeft().add(a);                                    /* NO TIENE QUE SER INMUTABLE, si no va a hacer una copia.*/
            }
        } else if (a > this.getRootValue()){
            if (this.getRight() == null) {
                this.right = new SearchBinaryTreeWithCount(a);
                this.right.count++;
            } else {
                this.getRight().add(a);
            }
        } else if (a == this.getRootValue()) {
            this.count++;
        }
    }
    @Override
    public int getRootValue() {
        return this.root;
    }
    @Override
    public SearchBinaryTreeWithCount getLeft() {
        return this.left;
    }

    @Override
    public SearchBinaryTreeWithCount getRight() {
        return this.right;
    }

    @Override
    public void removeLeft() {
        if (this.left.count == 1) {
            this.left = null;
        } else this.left.count--;
    }

    @Override
    public void removeRight() {
        if (this.right.count == 1) {
            this.right = null;
        } else this.right.count--;
    }

    public int getCount() {
        return count;
    }
    public boolean isEmpty () {
        return this == null;
    }
    public int getTotalElements () {
        if (this.isEmpty()) {
            return 0;
        }
        int left = 0;
        if (this.getLeft() != null) {
            left = this.getLeft().getTotalElements();
        }
        int right = 0;
        if (this.getRight() != null) {
            right = this.getRight().getTotalElements();
        }
        return getCount() + left + right;
    }
}
