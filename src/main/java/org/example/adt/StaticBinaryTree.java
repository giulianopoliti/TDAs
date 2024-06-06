package org.example.adt;

public class StaticBinaryTree implements BinaryTree{
    private Integer [] array; /* cuando se cree, se crea con "null" en vez de 0 como lo hace int */
    private int indexRoot;

    private StaticBinaryTree (){}; /* se usa solamente aca en esta clase, porque es privado*/
    public StaticBinaryTree (int value){
        this.array = new Integer [(int) Math.pow(2,10) -1];
        this.indexRoot = 0; /*posicion 0 del arbol */
        this.array[0] = value; /* este valor se pisa*/
    }
    /*public StaticBinaryTree (){
        this.array = new int [(int) Math.pow(2,10) -1];
        Para tener un arbol de la cantidad de niveles que yo quiera, si quiero de 10 niveles es esa.
        La formula es niveles = (2**n)  -1
    }*/

    @Override
    public int getRootValue() {
        return this.array[indexRoot];
    }

    @Override
    public BinaryTree getLeft() {
        if (2*this.indexRoot+1 >= this.array.length){
            return null;
        }
        if (this.array [2 * this.indexRoot + 1] == null){
            return null;
        }
        StaticBinaryTree left = new StaticBinaryTree();
        left.array = this.array;
        left.indexRoot = 2 * this.indexRoot + 1;
        return left;
    }

    @Override
    public BinaryTree getRight() {
        if (2*this.indexRoot+2 >= this.array.length){
            return null;
        }
        if (this.array [2 * this.indexRoot + 2] == null){
            return null;
        }
        StaticBinaryTree right = new StaticBinaryTree();
        right.array = this.array;
        right.indexRoot = 2 * this.indexRoot + 2;
        return right;
    }

    @Override
    public void addLeft(int a) {
        if (2*this.indexRoot+1 >= this.array.length){
            throw new RuntimeException("Ya no se pueden agregar hijos izquierdos.");
        }
        if (this.array[2*this.indexRoot+1] != null){
            throw new RuntimeException("Ya existe el hijo izquierdo");
        }
        this.array[2*this.indexRoot+1] = a;
    }

    @Override
    public void addRight(int a) {
        if (2*this.indexRoot+2 >= this.array.length){
            throw new RuntimeException("Ya no se pueden agregar hijos derechos.");
        }
        if (this.array[2*this.indexRoot+2] != null){
            throw new RuntimeException("Ya existe el hijo derecho");
        }
        this.array[2*this.indexRoot+2] = a;
    }

    @Override
    public void removeLeft() {
        if (this.array[2*this.indexRoot+1] >= this.array.length){
            throw new RuntimeException("No hay hijo que eliminar.");
        }
        if (this.array[2*this.indexRoot+1] == null){
            throw new RuntimeException("No hay hijo izquierdo que eliminar.");
        }
        this.array[2*this.indexRoot+1] = null;
        if (this.getLeft() != null){
            this.getLeft().removeLeft();
            this.getLeft().removeRight();
        }
    }

    @Override
    public void removeRight() {
        if (this.array[2*this.indexRoot+2] >= this.array.length){
            throw new RuntimeException("No hay hijo que eliminar.");
        }
        if (this.array[2*this.indexRoot+2] == null){
            throw new RuntimeException("No hay hijo derecho que eliminar.");
        }
        this.array[2*this.indexRoot+2] = null;
        if (this.getRight() != null){
            this.getRight().removeLeft();
            this.getRight().removeRight();
        }
    }
}
