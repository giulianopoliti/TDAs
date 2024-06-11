package org.example.clazz;

import org.example.adt.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Example {

    public static void print(Stack stack) {
        Stack aux = copy(stack);
        while(!aux.isEmpty()) {
            System.out.println(aux.getTop());
            aux.remove();
        }
    }

    public static Stack copy(Stack stack) {
        Stack copy = new Stack();
        Stack copy2 = new Stack();

        while(!stack.isEmpty()) {
            int aux = stack.getTop();
            copy.add(aux);
            copy2.add(aux);
            stack.remove();
        }

        while(!copy.isEmpty()) {
            stack.add(copy.getTop());
            copy.remove();
        }

        while(!copy2.isEmpty()) {
            copy.add(copy2.getTop());
            copy2.remove();
        }

        return copy;
    }

    public static boolean isEven(Stack stack) {
        Stack aux = copy(stack);
        Stack aux2 = new Stack();
        Stack aux3 = new Stack();
        int count = 0;
        while(!aux.isEmpty()) {
            if(count % 2 == 0) {
                aux2.add(aux.getTop());
            } else {
                aux3.add(aux.getTop());
            }
            aux.remove();
        }

        while(!aux2.isEmpty() && !aux3.isEmpty()) {
            aux2.remove();
            aux3.remove();
        }

        return aux2.isEmpty() && aux3.isEmpty();
    }

    public static boolean isEven2(Stack stack, int count) {
        if(stack.isEmpty()) {
            return true;
        }

        Stack aux = copy(stack);
        Stack aux2 = new Stack();
        Stack aux3 = new Stack();

        if(count % 2 == 0) {
            aux2.add(aux.getTop());
            aux.remove();
        } else {
            aux3.add(aux.getTop());
            aux.remove();
        }
        isEven2(aux, count + 1);

        while(!aux2.isEmpty() && !aux3.isEmpty()) {
            aux2.remove();
            aux3.remove();
        }

        return aux2.isEmpty() && aux3.isEmpty();
    }

    public static IQueueWithPriority addAll(IQueueWithPriority pq1, IQueueWithPriority pq2) {
        if(pq1.isEmpty()) {
            IQueueWithPriority aux1 = new DynamicQueueWithPriority();
            IQueueWithPriority aux2 = new DynamicQueueWithPriority();
            while(!pq2.isEmpty()) {
                aux1.add(pq2.getFirst(), pq2.getPriority());
                aux2.add(pq2.getFirst(), pq2.getPriority());
                pq2.remove();
            }

            while(!aux2.isEmpty()) {
                pq2.add(aux2.getFirst(), aux2.getPriority());
                aux2.remove();
            }

            return aux1;
        }

        if(pq2.isEmpty()) {
            IQueueWithPriority aux1 = new DynamicQueueWithPriority();
            IQueueWithPriority aux2 = new DynamicQueueWithPriority();
            while(!pq1.isEmpty()) {
                aux1.add(pq1.getFirst(), pq1.getPriority());
                aux2.add(pq1.getFirst(), pq1.getPriority());
                pq1.remove();
            }

            while(!aux2.isEmpty()) {
                pq1.add(aux2.getFirst(), aux2.getPriority());
                aux2.remove();
            }

            return aux1;
        }

        int max = pq1.getPriority();
        IQueueWithPriority aux = new DynamicQueueWithPriority();
        while(!pq1.isEmpty()) {
            aux.add(pq1.getFirst(), pq1.getPriority());
            //if(pq1.getPriority() > max) {
            max = pq1.getPriority();
            //}
            pq1.remove();
        }

        int min = pq2.getPriority();
        int candidate = min > 0 ? 0 : -min;

        IQueueWithPriority aux2 = new DynamicQueueWithPriority();
        while(!pq2.isEmpty()) {
            aux.add(pq2.getFirst(), pq2.getPriority() + max + candidate);
            aux2.add(pq2.getFirst(), pq2.getPriority());
            pq2.remove();
        }

        while(!aux2.isEmpty()) {
            pq2.add(aux2.getFirst(), aux2.getPriority());
            aux2.remove();
        }

        return aux;
    }

    public static void merge(IQueue queue) {
        int size = size(queue);
        if(queue.isEmpty() || size == 1) {
            return;
        }
        if (size == 2) {
            int first = queue.getFirst();
            queue.remove();
            int second = queue.getFirst();
            queue.remove();
            if(first > second) {
                queue.add(second);
                queue.add(first);
            } else {
                queue.add(first);
                queue.add(second);
            }
            return;
        }

        int to = size / 2;
        IQueue aux = new DynamicQueue();
        for(int i = 0; i <= to; i++) {
            aux.add(queue.getFirst());
            queue.remove();
        }

        merge(queue);
        merge(aux);

        IQueue result = new DynamicQueue();

        while(!queue.isEmpty() && !aux.isEmpty()) {
            if(queue.getFirst() > aux.getFirst()) {
                result.add(aux.getFirst());
                aux.remove();
            } else {
                result.add(queue.getFirst());
                queue.remove();
            }
        }

        while(!queue.isEmpty()) {
            result.add(queue.getFirst());
            queue.remove();
        }

        while(!aux.isEmpty()) {
            result.add(aux.getFirst());
            aux.remove();
        }

        while(!result.isEmpty()) {
            queue.add(result.getFirst());
            result.remove();
        }

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

    private static int size(IQueue queue) {
        IQueue queue2 = new DynamicQueue();
        int count = 0;
        while(!queue.isEmpty()) {
            queue2.add(queue.getFirst());
            count++;
            queue.remove();
        }

        while(!queue2.isEmpty()) {
            queue.add(queue2.getFirst());
            queue2.remove();
        }
        return count;
    }

    public static boolean subseteq(ISet set, ISet set2) {
        ISet copy = copy(set);
        while(!copy.isEmpty()) {
            int value = copy.choose();
            if(!in(value, set2)) {
                return false;
            }
            copy.remove(value);
        }
        return true;
    }

    public static ISet symmetricDifference(ISet set, ISet set2) {
        return union(minus(set, set2), minus(set2, set));
    }

    public static ISet minus(ISet set, ISet set2) {
        ISet copy = copy(set);

        ISet aux = new DynamicSet();
        while(!copy.isEmpty()) {
            int value = copy.choose();
            if(!in(value, set2)) {
                aux.add(value);
            }
            copy.remove(value);
        }

        return aux;
    }

    public static ISet union(ISet set, ISet set2) {
        ISet copy = copy(set);
        ISet copy2 = copy(set2);

        while(!copy2.isEmpty()) {
            int value = copy2.choose();
            copy.add(value);
            copy2.remove(value);
        }

        return copy;
    }

    public static ISet intersection(ISet set, ISet set2) {
        ISet copy = copy(set);
        ISet copy2 = copy(set2);

        ISet intersection = new DynamicSet();
        while(!copy.isEmpty()) {
            int value = copy.choose();
            if(in(value, copy2)) {
                intersection.add(value);
            }
            copy.remove(value);
        }

        return intersection;
    }

    public static boolean in(int a, ISet set) {
        ISet copy = copy(set);

        while(!copy.isEmpty()) {
            int value = copy.choose();
            if(value == a) {
                return true;
            }
            copy.remove(value);
        }

        return false;
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


    public static int total(ISet set) {
        if(set.isEmpty()) {
            return 0;
        }
        ISet copy = copy(set);
        int count = 0;
        while(!copy.isEmpty()){
            count++;
            copy.remove(copy.choose());
        }
        return count;
    }

    public static boolean equals(ISet set, ISet set2) {
        return subseteq(set, set2) && subseteq(set2, set);
    }

    public static SetOfSet partsSet(ISet set) {
        ISet copy = copy(set);
        int total = total(set);

        int[] values = new int[total];
        for(int i = 0; i < total; i++) {
            int value = copy.choose();
            values[i] = value;
            copy.remove(value);
        }

        int max = (int) Math.pow(2, total);

        SetOfSet setOfSet = new SetOfSet();
        for(int i = 0; i < max; i++) {
            ISet aux = new DynamicSet();
            boolean[] array = toBinary(i, total);
            for(int j = 0; j < array.length; j++) {
               if(array[j]) {
                   aux.add(values[j]);
               }
            }
            setOfSet.add(set);
        }
        return setOfSet;
    }

    public static boolean[] toBinary(int n, int size) {
        boolean[] array = new boolean[size];
        int i = 0;
        while(n != 0 && n != 1) {
            if(n % 2 != 0) {
                array[i] = true;
            }
            i++;
        }
        array[i] = n == 1;
        return array;
    }


    /*
    -----------CONSIGNAS DEL SIMULACRO DE PARCIAL 2 --------------
    1) Modificar la implementacion dinamica de Stack para que no se puedan agregar valores sean potencia
    de un elemento que ya existe dentro de la estructura.
    RTA:
    Esto lo podemos ver en la clase DynamicStackSinPotencias.

    2) Un ideal I en Z tiene un conjunto que llamaremos tambien I, que cumple dos condiciones:
        1-   Si a, b ∈ I, entonces a + b ∈ I.
        2-   Si a ∈ Z, b ∈ I, entonces a b ∈ I

        Claramente este conjunto podr´ıa ser infinito, y entonces cualquier conjunto
        que tengamos programado no podr´a representar el conjunto de un Ideal.
        Pero, podemos tomar un intervalo (a, b) tal que exista un I que lo contenga.

        Generear un ideal a partir de dos n´umeros n y m y cubrir todo el intervalo
        (a, b), donde n es el digito m´as a la izquierda de su legajo (distinto de cero)
        y an´alogamente m es el m´as a la derecha.



        Hacer un metodo estatico que reciba una cola de numeros enteros y devuelva true si la secuencia puede representar operaciones de add y remove
        de una stack si consideramos que no puede tener elementos repetidos.

     */


    // 2 A) GENERAR CONJUNTO IDEAL.

    public static ISet generarIdeal (int a, int b, int n, int m) {
        ISet ideal = new DynamicSet();
        if (n > b) {
            if (m > b) {
                return ideal;
            }
            for (int i = 1; i * m < b; i++) {
                if (i * n > a) {
                    ideal.add(i * m);
                }
            }
            return ideal;
        }
        if (m > b) {
            for (int i = 1; i * n < b; i++) {
                if (i * n > a) {
                    ideal.add(i * n);
                }
                return ideal;
            }
        }
        for (int i = 1; i * n < b; i++) {
            if (i * n > a) {
                ideal.add(i * n);
            }
        }
        for (int i = 1; i * m < b; i++) {
            if (i * n > a) {
                ideal.add(i * m);
            }
        }

        ISet aux1 = copy(ideal);
        while (!aux1.isEmpty()) {
            int value = aux1.choose();
            aux1.remove(value);
            ISet aux2 = copy(aux1);
            while (!aux2.isEmpty()) {
                int value2 = aux2.choose();
                if (value + value2 > a && value + value2 < b) {
                    ideal.add(value + value2);
                }
                aux2.remove(value2);
            }
        }
        return ideal;
    }

    // 2 B) REPRESENTACION DE STACK SI TIENE OPERACIONES DE ADD Y REMOVE.
    public static boolean verificarRepresentacionDeStack (IQueue queue){
        IQueue copy = copy(queue);
        ISet set = new DynamicSet();
        while (!copy.isEmpty()){
            set.add(copy.getFirst());
            copy.remove();
        }
        while (!set.isEmpty()){
            int value = set.choose();
            if (apareceMasDeTresVeces(queue,value)){
                return false;
            }
            set.remove(value);
        }
        return true;
    }
    public static boolean apareceMasDeTresVeces (IQueue queue, int number){
        IQueue copy = copy(queue);
        int count = 0;
        while (!copy.isEmpty()){
            if (copy.getFirst() == number){
                if (++count == 3){
                    return true;
                }
                copy.remove();
            }
        }
        return false;
    }

    public static BinaryTree createFibonacciTree (int num) {
        List <Integer> fibonaccis = generateFibonacciList(num);
        BinaryTree binaryTree = new DynamicBinaryTree(num);
        binaryTree = generateFibonacciTree(fibonaccis,binaryTree);
        return binaryTree;
    }

    public static BinaryTree generateFibonacciTree(List<Integer> fibonaccis, BinaryTree binaryTree) {
        if (fibonaccis.isEmpty()) {
            return binaryTree;
        }

        // Obtener el último elemento de la lista de Fibonacci
        int lastFibonacci = fibonaccis.get(fibonaccis.size() - 1);

        // Agregar el último elemento como el nodo derecho del árbol actual
        binaryTree.addRight(lastFibonacci);

        // Crear una copia de la lista para procesar los subárboles
        List<Integer> copy = new ArrayList<>(fibonaccis);
        copy.remove(copy.size() - 1); // Remover el último elemento de la copia

        // Llamar recursivamente para construir el subárbol derecho
        generateFibonacciTree(copy, binaryTree.getRight());

        // Si aún hay elementos en la lista, agregar el último elemento como el nodo izquierdo del árbol actual
        if (!copy.isEmpty()) {
            binaryTree.addLeft(copy.get(copy.size() - 1));
            copy.remove(copy.size() - 1); // Remover el último elemento de la copia

            // Llamar recursivamente para construir el subárbol izquierdo
            generateFibonacciTree(copy, binaryTree.getLeft());
        }

        return binaryTree;
    }
    public static BinaryTree generateFibonacciTr (List<Integer> fibonaccis, BinaryTree binaryTree){
        if (fibonaccis.isEmpty()){
            return binaryTree;
        }
        List<Integer> copy = new ArrayList<>(fibonaccis);
        if (!copy.isEmpty()) {
            binaryTree.addRight(fibonaccis.getLast());
            copy.remove(copy.size()-1);
            binaryTree.addLeft(fibonaccis.getLast());
            generateFibonacciTr(copy,binaryTree.getRight());
        }
        List<Integer> copy2 = new ArrayList<>(copy);
        // Agregar el nodo izquierdo.
        if (!copy2.isEmpty()) {
            binaryTree.addRight(fibonaccis.getLast());
            copy2.remove(copy.size()-1);
            binaryTree.addLeft(fibonaccis.getLast());
            generateFibonacciTr(copy2,binaryTree.getLeft());
        }
        return binaryTree;
    }



        /*
        if (fibonaccis.isEmpty()){
            return binaryTree;
        } else {
            binaryTree.addRight(fibonaccis.get(fibonaccis.size()-1));
            if (fibonaccis.isEmpty()){
                return binaryTree;
            }
            binaryTree.addLeft(fibonaccis.get(fibonaccis.size()-2));
            fibonaccis.remove(fibonaccis.size()-1);
            generateFibonacciTree(fibonaccis, binaryTree.getRight());
            generateFibonacciTree(fibonaccis, binaryTree.getLeft());
        }
        return binaryTree;*/
    public static List <Integer> generateFibonacciList (int num) {
        List<Integer> fibonaccis = new ArrayList<>();
        fibonaccis.add(0);
        fibonaccis.add(1);
        while (num > fibonaccis.get(fibonaccis.size()-1)){
            fibonaccis.add(fibonaccis.get(fibonaccis.size()-1) + fibonaccis.get(fibonaccis.size()-2));
        }
        if (fibonaccis.get(fibonaccis.size()-1) == num) {
            return fibonaccis;
        }
        else {
            throw new RuntimeException("El numero no pertenece a la sucesion de Fibonacci");
        }
    }

    /*
    A PARTIR DE UN NUMERO DADO, ENCUENTRA UN ARBOL DIVIDIENDO ESE NUMERO POR 2, Y AGREGANDOLOS A HIJOS IZQUIERDOS Y DERECHOS.
    EN CASO DE QUE EL NUMERO SEA IMPAR. AGREGA A LA DERECHA EL NUMERO MAS GRANDE.
     */
    public static BinaryTree findTreeByDivision (BinaryTree binaryTree){
            if (binaryTree.getRootValue() == 1){
                return binaryTree;
            } else {
                int hijo = binaryTree.getRootValue() /2;
                binaryTree.addLeft(hijo);
                binaryTree.addRight(binaryTree.getRootValue() - hijo);
                findTreeByDivision(binaryTree.getLeft());
                findTreeByDivision(binaryTree.getRight());
            }
        return binaryTree;
    }

    /*

    --------------CONSIGNA---------------
    Dada una Queue de numeros enteros, calcular la Queue que representa la subcola de elementos
    mas larga tal que esa subcola aparece al menos dos veces en la Queue.

    Para esto sacamos la longitud /2 y luego se le pasa como parametro a encontrarSubQueueMasLarga.
     */
    public static int encontrarLargoQueue (IQueue queue){
        IQueue copy = copy(queue);
        int count = 0;
        while (!copy.isEmpty()){
            copy.remove();
            count++;
        }
        return count;
    }
    public static IQueue encontrarSQMasLarga (IQueue queue){
        IQueue longestCandidate = new DynamicQueue();
        IQueue copy = copy(queue);
        for (int i = size(queue); i > 0; i--){
            IQueue candidate = encontrarSubQueue(copy,i);
            if (size(candidate) > size(longestCandidate)){
                longestCandidate = candidate;
            }
            copy.remove();
        }
        return longestCandidate;
    }

    public static IQueue encontrarSubQueue (IQueue queue, int size){
        if (queue.isEmpty()){
            throw new RuntimeException("Cola vacia.");
        }
        IQueue subQueue = new DynamicQueue();
        IQueue copy = copy(queue);
        for (int i = 0; i < size && !copy.isEmpty(); i++){
            subQueue.add(copy.getFirst());
            copy.remove();
        }

        IQueue candidate = new DynamicQueue();
        while (!copy.isEmpty()){
            IQueue tempQueue = copy(copy);
            IQueue tempSQ = copy(subQueue);
            boolean match = true;
            while (!tempSQ.isEmpty() && !tempQueue.isEmpty()){
                if (tempQueue.getFirst() == tempSQ.getFirst()){
                    match = false;
                    break;
                }
                tempQueue.remove();
                tempQueue.remove();
                }
            if (match && tempSQ.isEmpty()){
                candidate = subQueue;
                break;
            }
            copy.remove();
        }
        return candidate;
    }
    public static IQueue encontrarSubQueueMasLarga (IQueue queue, int size){
        if (queue.isEmpty()){
            throw new RuntimeException("Cola vacia.");
        }
        if (size < 0){
            throw new RuntimeException("No hay subcola que se repíta.");
        }
        IQueue copy = copy(queue);
        IQueue subQueue = new DynamicQueue();
        //int size = encontrarLargoQueue(queue) / 2;
        for (int i = size; i >= 0; i--){
            if (!copy.isEmpty()){
                subQueue.add(copy.getFirst());
                copy.remove();
            }
        }
        IQueue copySubQueue = copy(subQueue);
        while (!copySubQueue.isEmpty()){
            if (!copy.isEmpty()){
                if (copySubQueue.getFirst() != copy.getFirst()){
                    copy.remove();
                }
                else {
                    copy.remove();
                    copySubQueue.remove();
                }
            }
            else {
                return encontrarSubQueueMasLarga(queue, size-1);
            }
        }
        return subQueue;
    }

    /* -----CONSIGNA----

    Dada una Queue de índices y los elementos de una Stack (el primero de la Queue es el indice
    del tope de la Stack), ordene la Stack en base a sus indices.

    Para esto creamos la clase StackWithPriority.
    En el main se le pasa como parametro una stack y una queue al metodo.
    En caso de ser de distinta longitud, toma la longitud mas corta para evitar nullpointers.
     */
    public static IStackWithPriority ordenarStackWithPriorityEnBaseAQueue (IStack stack, IQueue queue){
        IStackWithPriority stackWithPriority = new DynamicStackWithPriority();
        while (!stack.isEmpty() && !queue.isEmpty()){
            stackWithPriority.add(stack.getTop(),queue.getFirst());
            stack.remove();
            queue.remove();
        }
        return stackWithPriority;
    }


    public static void printDynamicDictionary (MultipleDictionary multipleDictionary) {
        ISet keys = multipleDictionary.getKeys();
        ISet copykeys = copy(keys);
        while (!copykeys.isEmpty()){
            int key = copykeys.choose();
            System.out.print("key -->" + key);
            System.out.print("VALORES: ");
            List <Integer> values = multipleDictionary.get(key);
            for (Integer value : values) {
                System.out.print(value);
                System.out.println(",");
            }
            System.out.println();
            copykeys.remove(key);
        }
    }

    public static void printDynamicDictionaryWithStack (MultipleDictionaryWithStack multipleDictionary) {
        ISet keys = multipleDictionary.getKeys();
        ISet copykeys = copy(keys);
        while (!copykeys.isEmpty()){
            int key = copykeys.choose();
            System.out.print("key -->" + key);
            System.out.print("VALORES: ");
            IStack stack = multipleDictionary.get(key);
            while (!stack.isEmpty()) {
                System.out.print(stack.getTop());
                System.out.println(",");
                stack.remove();
            }
            System.out.println();
            copykeys.remove(key);
        }
    }
}
