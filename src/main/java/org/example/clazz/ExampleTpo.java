package org.example.clazz;

import org.example.adt.DynamicQueueOfStacks;
import org.example.adt.IQueue;
import org.example.adt.IQueueOfStacks;
import org.example.adt.StaticQueueOfNElements;

import java.util.HashMap;
import java.util.Map;

public class ExampleTpo {

    public static int calcularDesplazamiento (String string) {
        HashMap<Character, Integer> alphabeticMap = ExampleTpo.generateDictionaryOfAlphabet(); // utilizamos el mapa que generamos con todos las letras
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
        int desplazamiento = ExampleTpo.calcularDesplazamiento(string);
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
        IQueueOfStacks copy = ExampleTpo.copyQueueOfStack(queue);
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
        IQueueOfStacks copy = ExampleTpo.copyQueueOfStack(queue);
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
}
