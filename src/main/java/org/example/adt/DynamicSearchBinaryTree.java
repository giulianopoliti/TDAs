package org.example.adt;

public class DynamicSearchBinaryTree implements SearchBinaryTree {

    private final BinaryTree binaryTree;

    private DynamicSearchBinaryTree(BinaryTree binaryTree) {
        this.binaryTree = binaryTree;
    }
    public DynamicSearchBinaryTree(int a){
        this.binaryTree = new DynamicBinaryTree(a);
    }


    @Override
    public int getRootValue() {
        if (this.isEmpty()) {
            System.out.println("SearchBinaryTree is not initialized");
            return -1;
        }
        return this.binaryTree.getRootValue();
    }

    @Override
    public boolean isEmpty() {
        return this.binaryTree == null || this.binaryTree.isEmpty();
    }

    @Override
    public void add(int element) {
        if (binaryTree.isEmpty()) {
            new DynamicBinaryTree(element);
            return;
        }
        if (element > binaryTree.getRootValue()) {
            if (binaryTree.getRight() == null) {
                binaryTree.addRight(element);
                return;
            }
            SearchBinaryTree searchBinaryTree = new DynamicSearchBinaryTree(binaryTree.getRight());
            searchBinaryTree.add(element);
            return;
        }

        if (binaryTree.getLeft() == null) {
            binaryTree.addLeft(element);
            return;
        }
        SearchBinaryTree searchBinaryTree = new DynamicSearchBinaryTree(binaryTree.getLeft());
        searchBinaryTree.add(element);
    }

    @Override
    public void removeLeft() {
        this.binaryTree.removeLeft();
    }

    @Override
    public void removeRight() {
        this.binaryTree.removeRight();
    }

    @Override
    public SearchBinaryTree getLeft() {
        try {
            SearchBinaryTree candidate = new DynamicSearchBinaryTree(this.binaryTree.getLeft());
            if (candidate.isEmpty()) {
                return null;
            }
            return candidate;
        } catch (RuntimeException e) {
            throw new RuntimeException("The search binary tree is empty");
        }
    }

    @Override
    public SearchBinaryTree getRight() {
        try {
            SearchBinaryTree candidate = new DynamicSearchBinaryTree(this.binaryTree.getRight());
            if (candidate.isEmpty()) {
                return null;
            }
            return candidate;
        } catch (RuntimeException e) {
            throw new RuntimeException("The search binary tree is empty");
        }
    }

}
