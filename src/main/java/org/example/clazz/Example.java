package org.example.clazz;

import org.example.adt.*;
import org.example.adt.Dictionary;
import org.example.adt.Stack;

import java.util.*;

public class Example {

    public static Dictionary countAncestros (SearchBinaryTree searchBinaryTree) {
        if (searchBinaryTree == null) {
            return null;
        } if (searchBinaryTree.isEmpty()) {
            return new DynamicDictionary();
        }
        Dictionary dictionary = new DynamicDictionary();
        dictionary.add(searchBinaryTree.getRootValue(), 0);
        asignarAncestros(searchBinaryTree.getLeft(), dictionary, searchBinaryTree.getRootValue());
        asignarAncestros(searchBinaryTree.getRight(), dictionary, searchBinaryTree.getRootValue());
        return dictionary;
    }
    public static void asignarAncestros (SearchBinaryTree searchBinaryTree, Dictionary dictionary, int count) {
        if (searchBinaryTree == null || (searchBinaryTree.getLeft() == null && searchBinaryTree.getRight() == null)) {
            return;
        }
        dictionary.add(searchBinaryTree.getRootValue(), count);
        asignarAncestros(searchBinaryTree.getLeft(), dictionary, count + searchBinaryTree.getRootValue());
        asignarAncestros(searchBinaryTree.getRight(), dictionary, count + searchBinaryTree.getRootValue());
    }

    private static int getLastPriority (IQueueWithPriority d1) {
        IQueueWithPriority copy = copyIQueueWithPriority(d1);
        while (!copy.isEmpty()) {
            int priority = copy.getPriority();
            copy.remove();
            if (copy.isEmpty()) {
                return priority;
            }
        } return 0;
    }
    public static IQueueWithPriority matchTwoQueuePriority (IQueueWithPriority d1, IQueueWithPriority d2) {
        int priority = getLastPriority(d1);
        while (!d2.isEmpty()) {
            d1.add(d2.getFirst(), 2*d2.getPriority() + priority);
            d2.remove();
        }
        return d1;
    }

    public static DynamicStackWithPriority matchTwoStackWPriority (DynamicStackWithPriority d1, DynamicStackWithPriority d2) {
        int priorityOfLast = d1.getPriority();
        while (!d1.isEmpty()) {
            d1.add(d2.getTop(), 2*d2.getPriority() + priorityOfLast);
            d2.remove();
        }
        return d1;
    }




    public static boolean stackWithPriorityIsStraight (DynamicStackWithPriority dynamicStackWithPriority) {
        DynamicStackWithPriority copy = copyStackWithPriority(dynamicStackWithPriority);
        double pendiente = copy.getPriority() / copy.getTop();
        copy.remove();
        while (!copy.isEmpty()) {
            if (copy.getPriority() / copy.getTop() != pendiente) {
                return false;
            }
            copy.remove();
        }
        return true;
    }
    public static DynamicStackWithPriority copyStackWithPriority (DynamicStackWithPriority dynamicStackWithPriority) {
        DynamicStackWithPriority copy = new DynamicStackWithPriority();
        DynamicStackWithPriority copy2 = new DynamicStackWithPriority();
        while (!dynamicStackWithPriority.isEmpty()) {
            copy.add(dynamicStackWithPriority.getTop(), dynamicStackWithPriority.getPriority());
            copy2.add(dynamicStackWithPriority.getTop(), dynamicStackWithPriority.getPriority());
            dynamicStackWithPriority.remove();
        }
        while (!copy2.isEmpty()) {
            dynamicStackWithPriority.add(copy2.getTop(), copy2.getPriority());
            copy2.remove();
        }
        return copy;
    }
    public static IStack changeValues (IStack stack) {
        IStack copy = copy(stack);
        IStack stack2 = new DynamicStack();
        while (!copy.isEmpty()) {
            stack2.add(copy.getTop());
            copy.remove();
        }
        int a = stack2.getTop();
        stack2.remove();
        while (!stack2.isEmpty()) {
            copy.add(stack2.getTop());
            stack2.remove();
        }
        copy.add(a);
        return copy;
    }
    public static int calcularDesplazamiento (String string) {
        HashMap<Character, Integer> alphabeticMap = Example.generateDictionaryOfAlphabet(); // utilizamos el mapa que generamos con todos las letras
        for (char c: string.toCharArray()) {
            c = Character.toLowerCase(c);
            if (!alphabeticMap.containsKey(c)) { // corregimos los tildes aca
                switch (c) {
                    case 'á': c = 'a'; break;
                    case 'é': c = 'e'; break;
                    case 'í': c = 'i'; break;
                    case 'ó': c = 'o'; break;
                    case 'ú':
                    case 'ü': c = 'u'; break;
                    case 'ñ': c = 'n'; break;
                }
            }
            if (Character.isLetter(c) || c == 'ñ') {
                alphabeticMap.put(c, alphabeticMap.get(c) +1);
            }
        }

        char letraConMasFrecuencia = ' ';
        int valueMax = 0;
        for (Map.Entry<Character, Integer> entry : alphabeticMap.entrySet()) {
            if (entry.getValue() > valueMax) {
                letraConMasFrecuencia = entry.getKey();
                valueMax = entry.getValue();
            }
        }
        System.out.println(letraConMasFrecuencia);
        int desplazamiento = letraConMasFrecuencia - 'a';
        return desplazamiento;
    }

    public static String descifrarMensaje2(String string) {
        String mensaje = "";
        int desplazamiento = Example.calcularDesplazamiento(string);
        final int LONGITUD_ALFABETO = 26, INICIO_MINUSCULAS = 97, INICIO_MAYUSCULAS = 65;
        for (char c : string.toCharArray()) {
            char charDescifrado = c;

            if (Character.isLowerCase(c)) {
                charDescifrado = (char) ((c - INICIO_MINUSCULAS - desplazamiento + LONGITUD_ALFABETO) % LONGITUD_ALFABETO + INICIO_MINUSCULAS);
                if (charDescifrado > 76 && charDescifrado < 79) {
                    charDescifrado = (char)(charDescifrado +1);
                }
            } else if (Character.isUpperCase(c)) {
                charDescifrado = (char) ((c - INICIO_MAYUSCULAS - desplazamiento + LONGITUD_ALFABETO) % LONGITUD_ALFABETO + INICIO_MAYUSCULAS);
                if (charDescifrado > 108 && charDescifrado < 111) {
                    charDescifrado = (char)(charDescifrado +1);
                }
            }

            mensaje += charDescifrado;
        }

        return mensaje;
    } // funciona para todo, pero toma la m como l, la n como m, y no esta incluida la ñ, fue la unica forma que pude



    public static String descifrarMensaje (String string) {
        String mensajeFinal = "";
        final int LONGITUD_ALFABETO = 26, INICIO_MINUSCULAS = 97, INICIO_MAYUSCULAS = 65;
        int desplazamiento = -Example.calcularDesplazamiento(string);
        for (int i = 0; i < string.length(); i++) {
            char caracterActual = string.charAt(i);
            if (!Character.isLetter(caracterActual)) {
                mensajeFinal += caracterActual;
            }

            int ascii = (int) caracterActual;
            boolean esMayuscula = Character.isUpperCase(caracterActual);
            int nuevoAscii = ((ascii - (esMayuscula ? INICIO_MAYUSCULAS : INICIO_MINUSCULAS)) + desplazamiento) % LONGITUD_ALFABETO;
            if (nuevoAscii < 0) {
                nuevoAscii += LONGITUD_ALFABETO;
            }
            if (nuevoAscii == 'n' || nuevoAscii == 'm' || nuevoAscii == 'l' || nuevoAscii == 'ñ') {
                nuevoAscii++;
            }
            int charFinal = (esMayuscula ? INICIO_MAYUSCULAS : INICIO_MINUSCULAS) + nuevoAscii;
            mensajeFinal += Character.toString((char) charFinal);
        }
        return mensajeFinal;
    }


    public static String rotar(String cadenaOriginal) {
        // En ASCII, la a es 97, b 98, A 65, B 66, etcétera
        final int LONGITUD_ALFABETO = 26, INICIO_MINUSCULAS = 97, INICIO_MAYUSCULAS = 65;
        int desplazamiento = - Example.calcularDesplazamiento(cadenaOriginal);
        String cadenaRotada = ""; // La cadena nueva, la que estará rotada
        for (int x = 0; x < cadenaOriginal.length(); x++) {
            char caracterActual = cadenaOriginal.charAt(x);
            // Si no es una letra del alfabeto entonces ponemos el char tal y como está
            // y pasamos a la siguiente iteración
            if (!Character.isLetter(caracterActual)) {
                cadenaRotada += caracterActual;
                continue;
            }

            int codigoAsciiDeCaracterActual = (int) caracterActual;
            boolean esMayuscula = Character.isUpperCase(caracterActual);

            // La posición (1 a 26) que ocupará la letra después de ser rotada
            // El % LONGITUD_ALFABETO se utiliza por si se pasa de 26. Por ejemplo,
            // la "z", al ser rotada una vez da el valor de 27, pero en realidad debería
            // regresar a la letra "a", y con mod hacemos eso ya que 27 % 26 == 1,
            // 28 % 26 == 2, etcétera ;)
            int nuevaPosicionEnAlfabeto = ((codigoAsciiDeCaracterActual
                    - (esMayuscula ? INICIO_MAYUSCULAS : INICIO_MINUSCULAS)) + desplazamiento) % LONGITUD_ALFABETO;
            // Arreglar rotaciones negativas
            if (nuevaPosicionEnAlfabeto == 'n' || nuevaPosicionEnAlfabeto == 'm' || nuevaPosicionEnAlfabeto == 'l' || nuevaPosicionEnAlfabeto == 'ñ') {
                nuevaPosicionEnAlfabeto++;
            }
            if (nuevaPosicionEnAlfabeto < 0)
                nuevaPosicionEnAlfabeto += LONGITUD_ALFABETO;
            int nuevaPosicionAscii = (esMayuscula ? INICIO_MAYUSCULAS : INICIO_MINUSCULAS) + nuevaPosicionEnAlfabeto;
            // Convertir el código ASCII numérico a su representación como símbolo o letra y
            // concatenar
            cadenaRotada += Character.toString((char) nuevaPosicionAscii);
        }
        return cadenaRotada;
    }

    public static String descifradoCesar(String texto) {
        StringBuilder cifrado = new StringBuilder();
        int codigo = Example.calcularDesplazamiento(texto) % 26;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) >= 'a' && texto.charAt(i) <= 'z') {
                if ((texto.charAt(i) - codigo) < 'a') {
                    cifrado.append((char) (texto.charAt(i) - codigo + 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) - codigo));
                }
            } else if (texto.charAt(i) >= 'A' && texto.charAt(i) <= 'Z') {
                if ((texto.charAt(i) - codigo) < 'A') {
                    cifrado.append((char) (texto.charAt(i) - codigo + 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) - codigo));
                }
            }
        }
        return cifrado.toString();
    }

    public static HashMap<Character, Integer> generateDictionaryOfAlphabet (){
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (char c = 'a'; c <= 'n'; c++) { // Lleno el diccionario con las letras del alfabeto y valores asociados por ASCII
            hashMap.put(c, 0);  // Ejemplo: Valor a, Valor b, ...
        }
        hashMap.put('ñ', 0); // metemos la ñ despues de la n
        for (char c = 'o'; c <= 'z'; c++) {
            hashMap.put(c, 0);  // de la o hasta la z
        }
        return hashMap;
    }

    public static IQueueOfStacks sumaOfQueueOfStacks (IQueueOfStacks q1, IQueueOfStacks q2) {
        if (q1.getNumElementsOfStack() != q2.getNumElementsOfStack()) {
            throw new RuntimeException("Las matrices tienen que ser del mismo tamaño.");
        }
        IQueueOfStacks copyQ1 = Example.copyQueueOfStack(q1);
        IQueueOfStacks copyQ2 = Example.copyQueueOfStack(q2);
        IQueueOfStacks sumaAlReves = new DynamicQueueOfStacks(q1.getNumElementsOfStack());
        IQueueOfStacks suma = new DynamicQueueOfStacks(q1.getNumElementsOfStack());
        while (!copyQ1.isEmpty()) {
            sumaAlReves.add(copyQ1.getFirst() + copyQ2.getFirst());
            copyQ1.remove();
            copyQ2.remove();
        }
        while (!sumaAlReves.isEmpty()) {
            suma.add(sumaAlReves.getFirst());
            sumaAlReves.remove();
        }
        return suma;
    }
    public static IQueueOfStacks traspuestaOfQueueOfStacks(IQueueOfStacks queue) {
        IQueueOfStacks copy = Example.copyQueueOfStack(queue);
        IQueueOfStacks traspuesta = new DynamicQueueOfStacks(queue.getNumElementsOfStack());
        int numElements = queue.getNumElementsOfStack();
        IQueue[] tempsQueue = new StaticQueueOfNElements[numElements];

        // Inicializar cada pila en el array de pilas temporales
        for (int i = 0; i < numElements; i++) {
            tempsQueue[i] = new StaticQueueOfNElements(numElements);
        }

        // Llenar las pilas temporales con los elementos de la copia de la cola
        for (int i = 0; i < numElements; i++) {
            for (int j = 0; j < numElements; j++) {
                if (!copy.isEmpty()) {
                    tempsQueue[j].add(copy.getFirst());
                    copy.remove();
                }
            }
        }

        // Transferir los elementos de las pilas temporales a la cola de traspuesta
        for (int j = numElements -1; j >= 0; j--) {
            while (!tempsQueue[j].isEmpty()) {
                traspuesta.add(tempsQueue[j].getFirst());
                tempsQueue[j].remove();
            }
        }

        return traspuesta;
    }


    public static boolean IQueueOfStackIsFull (IQueueOfStacks queue) {
        try {
            IQueueOfStacks copy = copyQueueOfStack(queue);
            copy.add(1);
        } catch (RuntimeException runtimeException) {
            return false;
        }
        return true;
    }
    public static IQueueOfStacks copyQueueOfStack (IQueueOfStacks iQueueOfStacks){
        int numElements = iQueueOfStacks.getNumElementsOfStack();
        IQueueOfStacks copy = new DynamicQueueOfStacks(numElements);
        IQueueOfStacks copy2 = new DynamicQueueOfStacks(numElements);
        while (!iQueueOfStacks.isEmpty()){
            copy.add(iQueueOfStacks.getFirst());
            iQueueOfStacks.remove();
        }
        while (!copy.isEmpty()){
            copy2.add(copy.getFirst());
            iQueueOfStacks.add(copy.getFirst());
            copy.remove();
        }
        return copy2;
    }

    public static int calcularTraza (IQueueOfStacks queue) {
        IQueueOfStacks copy = copyQueueOfStack(queue);
        int traza = 0;
        int index = copy.getNumElementsOfStack();
        while (!copy.isEmpty()) {
            index--;
            for (int i = 0; i < copy.getNumElementsOfStack(); i++) {
                if (i == index) {
                    traza += copy.getFirst();
                }
                copy.remove();
            }
        }
        return traza;
    }
    public static void print(Stack stack) {
        Stack aux = copy(stack);
        while(!aux.isEmpty()) {
            System.out.println(aux.getTop());
            aux.remove();
        }
    }

    public static Stack copy(IStack stack) {
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


           /* if (count > 0 && count < 15){
        count++;
    }*/
    public static ISet setOfKeysAndValues (Dictionary dictionary) {
        ISet keys = dictionary.getKeys();
        ISet copyKeys = copy(keys);
        ISet result = new DynamicSet();
        while (!copyKeys.isEmpty()){
            ISet copyKeys2 = copy(keys);
            int key = copyKeys.choose();
            while (!copyKeys2.isEmpty()) {
                int key2 = copyKeys2.choose();
                if (dictionary.get(key2) == key) {
                    result.add(key);
                    copyKeys2.remove(key2);
                    break;
                }
                copyKeys2.remove(key2);

            }
            copyKeys.remove(key);
        }
        if (result.isEmpty()){
            throw new RuntimeException("No hubo llaves que se repitan con algun valor.");
        } else return result;
    }

    public static boolean specificStack (IStack stack) {
        IStack copy = copy(stack);
        int countPares = 0;
        int countImpares = 0;
        Integer previous = null;
        while (!copy.isEmpty() && copy.getTop() % 2 == 0) {
            if (countPares == 0) {
                countPares++;
                previous = copy.getTop();
                copy.remove();
            } else {
                if (copy.getTop() + 2 == previous || copy.getTop() - 2 == previous) {
                    countPares++;
                    previous = copy.getTop();
                    copy.remove();
                } else return false;
            }
        }

        if (countPares > 15) {
            return false;
        }
        previous = null; // no me gusta poner null pero ya estoy quemado
        while (!copy.isEmpty() && countImpares <= 3 && copy.getTop() % 2 == 1) {
            if (countImpares == 0) {
                countImpares++;
                previous = copy.getTop();
                copy.remove();
            } else {
                if (copy.getTop() + 2 == previous || copy.getTop() - 2 == previous) {
                    countImpares++;
                    previous = copy.getTop();
                    copy.remove();
                } else break;
            }
        }
        if (countImpares < 2) {
            return false;
        }
        while (!copy.isEmpty()) {
            int value = copy.getTop();
            copy.remove();
            if (copy.isEmpty()){
                if (value % 2 != 0) {
                    return false;
                }
            }
        }
        return true;
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
    // TE DICE SI UN INT ESTRA DENTRO DEL CONJUNTO
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
        int count = 0;  //O(C)
        while (!copy.isEmpty()){ // O (N+C+C+C+C+C)
            if (copy.getFirst() == number){ // O(C)
                if (++count == 3){ //O (C)
                    return true; // O(C)
                }
                copy.remove(); // O(C)
            }
        }
        return false; // O(C)
    }


    public static IQueueWithPriority copyIQueueWithPriority (IQueueWithPriority queueWithPriority) {
        IQueueWithPriority copy = new DynamicQueueWithPriority();
        IQueueWithPriority copy2 = new DynamicQueueWithPriority();

        while (!queueWithPriority.isEmpty()) {
            copy.add(queueWithPriority.getFirst(), queueWithPriority.getPriority());
            copy2.add(queueWithPriority.getFirst(), queueWithPriority.getPriority());
            queueWithPriority.remove();
        }
        while (!copy2.isEmpty()) {
            queueWithPriority.add(copy2.getFirst(),copy2.getPriority());
            copy2.remove();
        }
        return copy;
    }

    public static boolean esCreciente(IQueueWithPriority queueWithPriority) {
        if (queueWithPriority.isEmpty()) {
            return true;
        }
        int value = queueWithPriority.getFirst();
        int priority = queueWithPriority.getPriority();
        queueWithPriority.remove();
        if (priority >= queueWithPriority.getPriority() || value >= queueWithPriority.getFirst()) {
            return false;
        }
        return esCreciente(queueWithPriority);
    }

    public static boolean esDecreciente (IQueueWithPriority queueWithPriority) {
        if (queueWithPriority.isEmpty()) {
            return true;
        }
        int value = queueWithPriority.getFirst();
        int priority = queueWithPriority.getPriority();
        queueWithPriority.remove();
        if (priority <= queueWithPriority.getPriority() || value <= queueWithPriority.getFirst()) {
            return false;
        }
        return esDecreciente(queueWithPriority);
    }

    public static String definirQueue(IQueueWithPriority queueWithPriority) {
        if (queueWithPriority.isEmpty()){
            throw new RuntimeException("La cola esta vacia.");
        }
        IQueueWithPriority copy = copyIQueueWithPriority(queueWithPriority);
        int v1 = copy.getFirst();
        int p1  = copy.getPriority();
        copy.remove();
        if (!queueWithPriority.isEmpty()) {
            if (v1 > copy.getFirst() && p1 > copy.getPriority()) {
                if(esDecreciente(queueWithPriority)){
                    return "Es creciente.";
                }
            }else if (v1 < copy.getFirst() && p1 < copy.getPriority()) {
                if(esCreciente(queueWithPriority)){
                    return "Es decreciente";
                }
            }
        }
        return "No es nada.";
    }


    public static boolean isInductive (ISet set, int a, int b) {
        if (set.isEmpty()){
            throw new RuntimeException("Es un conjunto vacio.");
        } if (a >= b) {
            throw new RuntimeException("Intervalo erroneo, B debe ser mayor a A");
        }
        ISet copy = copy(set);
        boolean tieneEl1 = false;
        int count = b-a;
        while (!copy.isEmpty()){
            int value = copy.choose();
            if (value == 1) {
                tieneEl1 = true;
            }
            copy.remove(value);
        }
        if (!tieneEl1){
            return false;
        }
        boolean [] array = new boolean[count];
        ISet copy2 = copy(set);
        while (!copy2.isEmpty()){
            int value = copy2.choose();
            if (value >= a && value < b) {
                array [value-a] = true;
            }
            copy2.remove(value);
        }
        for (int i = 0; i < array.length; i++) {
            if (!array[i]) {
                return false;
            }
        }
        return true;
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
    public BinaryTree copyBinaryTree (BinaryTree binaryTree) {
        BinaryTree copy = new DynamicBinaryTree(binaryTree.getRootValue());
        BinaryTree copy2 = new DynamicBinaryTree(binaryTree.getRootValue());
        if (binaryTree.getLeft() != null) {

        }
        return null;
    }

    public static boolean isSBT(BinaryTree binaryTree) {
        return isSBT(binaryTree, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isSBT(BinaryTree binaryTree, int min, int max) {
        if (binaryTree == null) {
            return true;
        }

        int rootValue = binaryTree.getRootValue();
        if (rootValue <= min || rootValue >= max) {
            return false;
        }

        return isSBT(binaryTree.getLeft(), min, rootValue) && isSBT(binaryTree.getRight(), rootValue, max);
    }
    public static int sumaNodosInternos (BinaryTree binaryTree) {
        if (binaryTree == null) {
            return 0;
        }
        int minimo = Integer.MAX_VALUE;
        int count = 0;
        int [] array = sumaNodosInternos(binaryTree, minimo, count);
        return array[0] * array[1];
    }
    public static int[] sumaNodosInternos (BinaryTree binaryTree, int min, int count) {
        if (binaryTree.getLeft() == null && binaryTree.getRight() == null) {
            if (binaryTree.getRight().getRootValue() < min) {
                min = binaryTree.getRight().getRootValue();
            } else if (binaryTree.getLeft().getRootValue() < min) {
                min = binaryTree.getLeft().getRootValue();
            }
        } else {
            if (binaryTree.getLeft() != null) {
                count += binaryTree.getLeft().getRootValue();
                sumaNodosInternos(binaryTree.getLeft(), min, count);
            }
            if (binaryTree.getRight() != null) {
                count += binaryTree.getRight().getRootValue();
                sumaNodosInternos(binaryTree.getRight(), min, count);
            }
        }
        int [] array = new int[2];
        array[0] = min;
        array[1] = count;
        return array;
    }
    public static BinaryTree SBBMoreLargeController (BinaryTree binaryTree) {
        if (binaryTree == null) {
            throw new RuntimeException("El arbol es nulo.");
        }else {
            BinaryTree maxLarge = new DynamicBinaryTree(binaryTree.getRootValue());
            BinaryTree copy = new DynamicBinaryTree(binaryTree.getRootValue());
            int count = 1;
            ResultSBBMoreLarge resultSBBMoreLarge = calculateSBBMoreLarge(binaryTree, count);
            if (resultSBBMoreLarge.getCount() > count) {
                return resultSBBMoreLarge.getBinaryTree();
            }
        }
        return binaryTree;
    }
    private static ResultSBBMoreLarge calculateSBBMoreLarge (BinaryTree binaryTree, int count) {
        if (binaryTree.getRootValue() < binaryTree.getLeft().getRootValue() &&
        binaryTree.getRootValue() > binaryTree.getRight().getRootValue()) {
            count += 2;
            calculateSBBMoreLarge(binaryTree.getLeft(), count);
            calculateSBBMoreLarge(binaryTree.getRight(), count);
        } else if (binaryTree.getRootValue() < binaryTree.getLeft().getRootValue()) {
            count++;
            calculateSBBMoreLarge(binaryTree.getLeft(), count);
        } else if (binaryTree.getRootValue() > binaryTree.getRight().getRootValue()) {
            count++;
            Example.calculateSBBMoreLarge(binaryTree.getRight(), count);
        }
        return new ResultSBBMoreLarge(binaryTree, count);
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
