package org.example.adt;

import java.util.Objects;

public class DynamicStackSinPotencias implements IStack{
    private Node first; // se inicializa en null

    public boolean esPotencia (int base, int numero) {
        if (base == 0 && numero == 0) { //O(C)
            return true;
        } if (base != 0 && numero == 0) { // O(C)
            return true;
        } else if (base == 0) { //O(C)
            return false;
        } if (base == numero) { //C
            return true;
        } else if (base == 1) { //C
            return false;
        }
        for (int i = 2; Math.abs(Math.pow(base, i)) > Math.abs(numero) ; i++){ //O(n) Lineal
            if (Math.abs(Math.pow(base, i)) == Math.abs(numero)){
                if (base <= 0 && numero <= 0 || base >= 0 && numero >= 0){
                    return true;
                }
            }
        }
        return false;
    }

    /*public IStack copy (IStack stack){
        IStack aux1 = new DynamicStack();
        IStack aux2 = new DynamicStack();
        while (!this.isEmpty()){
            int value = this.getTop();
            aux1.add(value);
            aux2.add(value);
            this.remove();
        }
        while (!aux2.isEmpty()){
            int value = aux2.getTop();
            this.add(value);
            aux2.remove();
        }
        return aux1;
    }*/
    @Override
    public void add(int a) { // O(C+C+ N**2 +C+C)
        Node node = new Node(a, first); // O(C)
        Node current = first; // O(C)
        while (current != null) { // O (n)
            if (esPotencia(current.getValue(), node.getValue())){ //O(n)
                return;
            }
            current = current.getNext(); // O(C)
        }
        first = node; // O (C)
    }

    @Override
    public void remove() {
        if (this.isEmpty()) {
            throw new RuntimeException("No se puede desapilar una pila vacia");
        } else {
            first = first.getNext();
        }
    }

    @Override
    public int getTop() {
        if (this.isEmpty()){
            throw new RuntimeException("No se puede obtener el tope de una pila vacia");
        }
        return first.getValue();
    }

    @Override
    public boolean isEmpty() {
        return Objects.isNull(first); // por prolijidad, para no escribir la palbra null
    }
}
