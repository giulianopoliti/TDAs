package org.example.adt;

import java.util.Objects;

public class DynamicStackWithPriority implements IStackWithPriority{
    private NodeWithPriority first;

    @Override
    public void add(int value, int priority) {
        NodeWithPriority newNode = new NodeWithPriority(value, priority, null);

        // Si la pila está vacía o el nuevo nodo tiene mayor prioridad que el primero
        if (Objects.isNull(this.first) || priority >= this.first.getPriority()) {
            newNode.setNext(this.first);
            this.first = newNode;
        } else {
            // Recorre la pila para encontrar la posición correcta
            NodeWithPriority current = this.first;
            while (current.getNext() != null && current.getNext().getPriority() > priority) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
    }
    @Override
    public void remove() {
        if (this.isEmpty()){
            throw new RuntimeException("No se puede desapilar una pila vacia.");
        }
        this.first = this.first.getNext();
    }

    @Override
    public boolean isEmpty() {
        return Objects.isNull(this.first);
    }

    @Override
    public int getTop() {
        return this.first.getValue();
    }

    @Override
    public int getPriority() {
        return this.first.getPriority();
    }
}
