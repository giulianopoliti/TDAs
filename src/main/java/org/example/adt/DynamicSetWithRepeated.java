package org.example.adt;

import java.util.Objects;

public class DynamicSetWithRepeated implements ISet{

    private DictionaryNode first; // utilizamos DictionaryNode, pero podríamos crear un nuevo nodo con la misma estructura
    private int count;

    @Override
    public void add(int element) {
        DictionaryNode current = this.first;
        while (current != null && current.getKey() != element) {
            current = current.getNext();
        }
<<<<<<< HEAD
        this.count++; // el count, cuenta cada elemento con su cantidad, es decir si el elemento 4, tiene de cantidad 2, cuenta doble
=======
        if (current == null) {
            this.first = new DictionaryNode(element, 1, this.first);
        } else if (current.getKey() == element) {
            current.setValue(current.getValue() + 1);
        }
        this.count++; // el count, cuenta cada elemento con su cantidad
>>>>>>> 4e873c15798cc371de2a179c266475f89cec1929
    }

    @Override
    public void remove(int element) {
        if (this.first == null) {
            return;
        }

        if (this.first.getNext() == null) {
            if (this.first.getKey() == element) {
                this.first.setValue(this.first.getValue() - 1);
                if (this.first.getValue() == 0) {
                    this.first = null;
                }
                this.count--;
                return;
            }
            return;
        }

        if (this.first.getKey() == element) {
            this.first.setValue(this.first.getValue() - 1);
            if (this.first.getValue() == 0) {
                this.first = this.first.getNext();
            }
            this.count--;
            return;
        }

        DictionaryNode backup = this.first;
        DictionaryNode current = this.first.getNext();
        while (current != null && current.getKey() != element) {
            backup = current;
            current = current.getNext();
        }

        if (current != null) {
            current.setValue(current.getValue() - 1);
            if (current.getValue() == 0) {
                backup.setNext(current.getNext());
            }
            this.count--;
        }
    }

    @Override
    public boolean isEmpty() {
        return Objects.isNull(this.first);
    }

    @Override
    public int choose() {
        if (this.count == 0) {
            throw new RuntimeException("No se puede elegir un valor de un conjunto vacío.");
        }

        double randomValue = Math.random();
        double probabilityAcumulator = 0.0; // use este acumulador, para generar rangos entre 0 y 1 para cada numero

        DictionaryNode current = this.first;

        while (current != null) {
            probabilityAcumulator += (double) current.getValue() / this.count;
            if (randomValue <= probabilityAcumulator) {
                return current.getKey();
            }
            current = current.getNext();
        }

        throw new RuntimeException("No se pudo elegir un valor válido.");
    }
    public void removeAllRepeated(int element) { /// metodo opcional
        if (this.first == null) {
            return;
        }
        if (this.first.getNext() == null) {
            if (this.first.getKey() == element) {
                this.count -= this.first.getValue();
                this.first = null;
                return;
            }
            return;
        }

        if (this.first.getKey() == element) {
            this.count -= this.first.getValue();
            this.first = this.first.getNext();
            return;
        }

        DictionaryNode backup = this.first;
        DictionaryNode current = this.first.getNext();
        while (current != null && current.getKey() != element) {
            backup = current;
            current = current.getNext();
        }

        if (current != null) {
            this.count -= current.getValue();
            backup.setNext(current.getNext());
        }
    }
}
