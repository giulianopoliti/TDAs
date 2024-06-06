package org.example.clazz;

import org.example.adt.*;

import java.util.List;

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
    /*public static MultipleDictionary intersection (MultipleDictionary dictionary, MultipleDictionary dictionary2){
        ISet set = dictionary.getKeys();
        ISet set2 = dictionary2.getKeys();
        ISet intersectionSet = intersection(set,set2);
        while (!intersectionSet.isEmpty()){
            int key = intersectionSet.choose();
            List<Integer> list = dictionary.get(key);
            List<Integer> list2 = dictionary2.get(key);
            ISet setAux = new DynamicSet();
            for (int i = 0; i< list.size(); i++){
                setAux.add(list.get(i));
            }
            ISet setAux2 = new DynamicSet();
            for (int i = 0; i < list2.size(); i++){
                setAux2.add(list2.get(i));
            }
            ISet intersectionValues = intersection(setAux, setAux2);
            MultipleDictionary result = new DynamicMultipleDictionary();
            if (!intersectionValues.isEmpty()){
                while (!intersectionValues.isEmpty()){
                    int element = intersectionValues.choose();
                    result.add(key, element);
                    intersectionValues.remove(element);
                }
            } intersectionSet.remove(key);
        }return result;
}
*/
}
