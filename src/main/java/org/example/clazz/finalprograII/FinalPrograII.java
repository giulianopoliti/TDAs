package org.example.clazz.finalprograII;

import org.example.adt.DynamicQueue;
import org.example.adt.DynamicSet;
import org.example.adt.IQueue;
import org.example.adt.ISet;

public class FinalPrograII {


    //TEORIA.
    /*
    1) Explique como se podrıa generalizar la implementacion estatica de un
    arbol binario para un ´arbol 4-ario.
    Podriamos tener una interfaz que sea tree, va a tener los metodos,

    Tree obtenerHijo (int position);
    void agregarHijo(int position, int value);
    void removerHijo (int position);
    int getRootValue;
    boolean isEmpty ();


    Esta interfaz la van a implementar los dos. De esta forma podemos generalizar.
    Despues va a haber algunos cambios mas, por ejemplo al inicializarlo. debemos hacer this.array = new Integer [(int) Math.pow(2,10) -1] para el arbol binario
    y         this.array = new Integer[(int) Math.pow(4, 10) - 1]; // Ejemplo con altura 10


    2) Explique por qu´e el algoritmo de burbujeo tendr´ıa la misma complejidad
    con nodos que con arreglos, mientras que no sucede esto con el algoritmo
    de b´usqueda binaria.

    Esto es debido a que, el algoritmo de burbujeo compara todos los pares y los intercambia si estan en el lugar incorrecto,
    de todas formas tiene que recorrer todos los nodos, ya sea que esten en un array primitivo o enlazados entre si.
    La complejidad computacional no va a variar. Va a ser lineal en el mejor caso, y cuadratica en el peor ya que tiene un ciclo adentro de otro.
    En el caso de busqueda binaria, la complejidad computacional es logaritmica.





     */

    public static ISpecialDictionary mergeDictionaries (ISpecialDictionary dict1, ISpecialDictionary dict2) {
        IMultipleDictionary multipleDictionary = new SpecialMultipleDictionary();
        ISpecialDictionary copy1 = copyDictionary(dict1);
        ISpecialDictionary copy2 = copyDictionary(dict2);
        boolean canBeSimple = true;
        while (!copy2.getKeys().isEmpty()) {
            int key = copy1.getKeys().choose();
            if (!elementNotInDictionary(key,copy1)) {
                canBeSimple = false;
                multipleDictionary.add(key, copy1.get(key));
                multipleDictionary.add(key, copy2.get(key));
                copy1.remove(key, copy1.get(key));
                copy2.remove(key, copy2.get(key));
            } else {
                multipleDictionary.add(key,copy2.get(key));
                copy2.remove(key, copy2.get(key));
            }
        }
        while (!copy1.getKeys().isEmpty()) {
            int key = copy1.getKeys().choose();
            multipleDictionary.add(key, copy1.get(key));
            copy1.remove(key, copy1.get(key));
        }
        if (canBeSimple) {
            ISpecialDictionary specialDictionary = new SpecialDinamicDictionary();
            while (!multipleDictionary.getKeys().isEmpty()) {
                int key = multipleDictionary.getKeys().choose();
                specialDictionary.add(key , multipleDictionary.get(key));
                multipleDictionary.remove(key, multipleDictionary.get(key));
            }
            return specialDictionary;
        } else return multipleDictionary;
    }
    private static boolean elementNotInDictionary (int key, ISpecialDictionary dictionary) {
        ISpecialDictionary copy = copyDictionary(dictionary);
        while (!copy.getKeys().isEmpty()) {
            int key2 = dictionary.getKeys().choose();
            if (key == key2) {
                return false;
            }
        }
        return true;
    }

    public static int elementIsSumOfTwoElements (ISet set) {
        // precondicion es que debe haber un elemento que es suma de otros dos elementos en el conjunto.
        ISet copy = copy(set);
        while (!copy.isEmpty()) {
            int valueSet = copy.choose();
            IQueue queue = setAQueue(set);
            while (!queue.isEmpty()) {
                int value1 = queue.getFirst();
                queue.remove();
                IQueue copyQueue = copy(queue);
                while (!copyQueue.isEmpty()) {
                    int value2 = copyQueue.getFirst();
                    if (value1 + value2 == valueSet) {
                        return valueSet;
                    } else {
                        copyQueue.remove();
                    }
                }
            }
            copy.remove(valueSet);
        }
        throw new RuntimeException("No hay valores repetidos.");
    }
    public static IQueue setAQueue (ISet set) {
        ISet copy = copy(set);
        IQueue queue = new DynamicQueue();
        while (!copy.isEmpty()) {
            int value = copy.choose();
            queue.add(value);
            copy.remove(value);
        }
        return queue;
    }
    public static ISpecialDictionary copyDictionary (ISpecialDictionary dictionary) {
        ISpecialDictionary copy = new SpecialDinamicDictionary();
        ISpecialDictionary copy2 = new SpecialDinamicDictionary();
        while (!dictionary.getKeys().isEmpty()) {
            int key = dictionary.getKeys().choose();
            int value = dictionary.get(key);
            copy.add(key, value);
            copy2.add(key,value);
            dictionary.remove(key, value);
        }
        while (!copy2.getKeys().isEmpty()) {
            int key = copy2.getKeys().choose();
            int value = copy2.get(key);
            dictionary.add(key,value);
        }
        return copy;
    }
    public static ISet copy(ISet set) {
        ISet aux = new DynamicSet();
        ISet aux2 = new DynamicSet();

        while(!set.isEmpty()) {
            int value = set.choose();
            aux.add(value);
            aux2.add(value);
            set.remove(value);
        }

        while(!aux.isEmpty()) {
            int value = aux.choose();
            set.add(value);
            aux.remove(value);
        }

        return aux2;
    }
    private static IQueue copy(IQueue queue) {
        IQueue queue2 = new DynamicQueue();
        IQueue queue3 = new DynamicQueue();
        while(!queue.isEmpty()) {
            queue2.add(queue.getFirst());
            queue3.add(queue.getFirst());
            queue.remove();
        }

        while(!queue2.isEmpty()) {
            queue.add(queue2.getFirst());
            queue2.remove();
        }
        return queue3;
    }
}
