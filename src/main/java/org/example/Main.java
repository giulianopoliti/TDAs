package org.example;

import org.example.adt.*;
import org.example.clazz.Example;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        SearchBinaryTree searchBinaryTree = new DynamicSearchBinaryTree(7);
        searchBinaryTree.add(5);
        searchBinaryTree.add(12);
        searchBinaryTree.add(10);
        searchBinaryTree.add(20);
        searchBinaryTree.add(3);
        searchBinaryTree.add(6);
        searchBinaryTree.add(1);
        searchBinaryTree.add(4);
        Dictionary dictionary = Example.countAncestros(searchBinaryTree);
        ISet set = dictionary.getKeys();

        while (!set.isEmpty()) {
            int value = set.choose();
            System.out.println("Clave: " + value + " valor:" + dictionary.get(set.choose()));
            dictionary.remove(set.choose(), dictionary.get(set.choose()));
            set.remove(value);
        }

        /*
        IQueueWithPriority queueWithPriority = new DynamicQueueWithPriority();
        IQueueWithPriority queueWithPriority1 = new DynamicQueueWithPriority();
        queueWithPriority.add(1,2);
        queueWithPriority.add(2,3);
        queueWithPriority.add(2,5);
        queueWithPriority.add(1,1);
        queueWithPriority.add(2,4);
        queueWithPriority1.add(15,3);
        queueWithPriority1.add(10,1);
        Example.matchTwoQueuePriority(queueWithPriority,queueWithPriority1);
        while (!queueWithPriority.isEmpty()) {
            System.out.println("Valor:" + queueWithPriority.getFirst() + " prioridad: " + queueWithPriority.getPriority());
            queueWithPriority.remove();

        }*/

        /*
        SearchBinaryTreeWithCount binaryTree = new SearchBinaryTreeWithCount(1);
        binaryTree.add(1);
        binaryTree.add(2);
        binaryTree.add(3);
        binaryTree.add(1);
        System.out.println(binaryTree.getTotalElements());
*/


        /*
        dynamicStackWithPriority.add(1,3);
        dynamicStackWithPriority.add(2,6);
        dynamicStackWithPriority.add(3,9);
        dynamicStackWithPriority.add(4,15);
        System.out.println(Example.stackWithPriorityIsStraight(dynamicStackWithPriority));
        */

        /*
        IStack stack = new DynamicStack();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack = Example.changeValues(stack);
        while (!stack.isEmpty()) {
            System.out.println(stack.getTop());
            stack.remove();
        }*/
        /*
        BinaryTree binaryTree = new BinaryTreeWithReference(1);
        binaryTree.addLeft(2);
        binaryTree.addRight(3);
        binaryTree.getLeft().addRight(4);
        print2(binaryTree);
        */
        /*

    ISet setWithBuilder = new SetWithBuilder();
    setWithBuilder.add(1);
    while (!setWithBuilder.isEmpty()) {
        int value = setWithBuilder.choose();
        System.out.println(value);
        setWithBuilder.remove(value);
    }*/

        ISet dynamicSetWithRepeated = new DynamicSetWithRepeated();
        dynamicSetWithRepeated.add(1);
        dynamicSetWithRepeated.add(1);
        dynamicSetWithRepeated.add(1);
        dynamicSetWithRepeated.add(2);
        dynamicSetWithRepeated.add(2);
        dynamicSetWithRepeated.add(2);
        dynamicSetWithRepeated.add(2);
        dynamicSetWithRepeated.add(3);
        while (!dynamicSetWithRepeated.isEmpty()) {
            int value = dynamicSetWithRepeated.choose();
            System.out.println(value);
            dynamicSetWithRepeated.remove(value);
        }





        /*

        DynamicQueueWithBuilder dynamicQueueWithBuilder = new DynamicQueueWithBuilder(1,2,4,3,6);
        while (!dynamicQueueWithBuilder.isEmpty()) {
            System.out.println(dynamicQueueWithBuilder.getFirst());
            dynamicQueueWithBuilder.remove();
        }*/
        /*
        DynamicStackWithBuilder dynamicStackWithBuilder = new DynamicStackWithBuilder().add(1).add(2);
        while (!dynamicStackWithBuilder.isEmpty()) {
            System.out.println(dynamicStackWithBuilder.getTop());
            dynamicStackWithBuilder.remove();
        }*/

        /*
        ISet set1 = new DynamicSet();
        set1.add(5);
        DynamicSetWithBuilder set = new DynamicSetWithBuilder().add(1).add(2).add(3).addAll(set1);
        while (!set.isEmpty()) {
            int value = set.choose();
            System.out.println(value);
            set.remove(value);
        }*/

        /*
        IQueue queue = new DoubleLinkedDynamicQueue();
        queue.add(1);
        queue.add(1);
        queue.add(1);
        queue.add(1);
        while (!queue.isEmpty()){
            System.out.println(queue.getFirst());
            queue.remove();
        }*/



/*
        IStack stack = new DynamicStackWithNElements(5);
        stack.add(1);
        stack.add(1);
        stack.add(1);

        stack.add(1);
        stack.add(1);
        while (!stack.isEmpty()) {
            System.out.println(stack.getTop());
            stack.remove();
        }
*/


/*
        char c = ' '; // El carácter del cual quieres obtener el valor ASCII
        int asciiValue = (int) c; // Convertir el carácter a su valor ASCII

        System.out.println("El valor ASCII de " + c + " es " + asciiValue);
        */

        //String texto = "Muchos años después, frente al pelotón de fusilamiento, el coronel Aureliano Buendía había de recordar aquella tarde remota en que su padre lo llevó a conocer el hielo. Macondo era entonces una aldea de veinte casas de barro y cañabrava construidas a la orilla de un río de aguas diáfanas que se precipitaban por un lecho de piedras pulidas, blancas y enormes como huevos prehistóricos. El mundo era tan reciente, que muchas cosas carecían de nombre, y para mencionarlas había que señalarlas con el dedo.\n\nTodos los años, por el mes de marzo, una familia de gitanos desarrapados plantaba su carpa cerca de la aldea, y con un grande alboroto de pitos y timbales daban a conocer los nuevos inventos. Primero llevaron el imán. Un gitano corpulento, de barba montaraz y manos de gorrión, que se presentó con el nombre de Melquiades, hizo una truculenta demostración pública de lo que él mismo llamaba la octava maravilla de los sabios alquimistas de Macedonia.\n\nFue lo último que se supo de él. La carpa se plantó por última vez en marzo. Los gitanos se fueron, y con ellos se fue Melquiades, quien antes había recorrido el mundo inventando cosas. Había traído el imán a la aldea y había pensado con su pluma.";
        //String textoCodif = "Oxfkrv dqrv ghvsxhv, iuhpwh dñ shñrwrp gh ixvlñdolhpwr, hñ frurphñ Dxuhñldpr Exhpgld kdeld gh uhfrugdu dtxhññd wdugh uhorwd hp txh vx sdguh ñr ññhyr d frprfhu hñ klhñr. Odfrpgr hud hpwrpfhv xpd dñghd gh yhlpwh fdvdv gh eduur b fdqdeudyd frpvwuxlgdv d ñd rulññd gh xp ulr gh djxdv gldidpdv txh vh suhflslwdedp sru xp ñhfkr gh slhgudv sxñlgdv, eñdpfdv b hpruohv fror kxhyrv suhklvwrulfrv. Hñ oxpgr hud wdp uhflhpwh, txh oxfkdv frvdv fduhfldp gh proeuh, b sdud ohpflrpduñdv kdeld txh vhqdñduñdv frp hñ ghgr.\n\nrgrv ñrv dqrv, sru hñ ohv gh oducr, xpd idolñld gh jlwdprv ghvduudsdgrv sñdpwded vx fdusd fhufd gh ñd dñghd, b frp xp judpgh dñerurwr gh slwrv b wloedñhv gdedp d frprfhu ñrv pxhyrv lpyhpwrv. Sulohur ññhydurp hñ lodp. Xp jlwdpr frusxñhpwr, gh edued orpwdudc b odprv gh jruulrp, txh vh suhvhpwr frp hñ proeuh gh Ohñtxldghv, klcr xpd wuxfxñhpwd ghorvwudflrp sxeñlfd gh ñr txh hñ olvor ññdoded ñd rfwdyd odudylññd gh ñrv vdelrv dñtxlolvwdv gh Odfhgrpld.\n\nIxh ñr xñwlor txh vh vxsr gh hñ. Ñd fdusd vh sñdpwr sru xñwlod yhc hp oducr. Ñrv jlwdprv vh ixhurp, b frp hññrv vh ixh Ohñtxldghv, txlhp dpwhv kdeld uhfruulgr hñ oxpgr lpyhpwdpgr frvdv. Kdeld wudlgr hñ lodp d ñd dñghd b kdeld shpvdgr frp vx sñxod.";

        //int desplazamiento = Example.calcularDesplazamiento(textoCodif);
        //System.out.println(desplazamiento);
        //for (Map.Entry<Character, Integer> entry : alphabetMap.entrySet()) {
          //  System.out.println("clave: " + entry.getKey() + " ---> valor: " + entry.getValue());
        //}

        //System.out.println(Example.rotar(textoCodif));
        //System.out.println(Example.descifrarMensaje2(textoCodif));

/*
        IStack stack = new StackOfNElements(3);
        stack.add(1);
        stack.add(2);
        stack.add(3);
        while (!stack.isEmpty()){
            System.out.println(stack.getTop());
            stack.remove();
        } */

/*
        IQueueOfStacks dynamicQueueOfStacks = new DynamicQueueOfStacks(3);

        dynamicQueueOfStacks.add(1);
        dynamicQueueOfStacks.add(2);
        dynamicQueueOfStacks.add(3);
        dynamicQueueOfStacks.add(4);
        dynamicQueueOfStacks.add(5);
        dynamicQueueOfStacks.add(6);
        dynamicQueueOfStacks.add(7);
        dynamicQueueOfStacks.add(8);
        dynamicQueueOfStacks.add(9);
        /*
        dynamicQueueOfStacks.add(10);
        dynamicQueueOfStacks.add(11);
        dynamicQueueOfStacks.add(12);
        dynamicQueueOfStacks.add(13);
        dynamicQueueOfStacks.add(14);
        dynamicQueueOfStacks.add(15);
        dynamicQueueOfStacks.add(16);*/
/*
        IQueueOfStacks dynamicQueueOfStacks1 = Example.copyQueueOfStack(dynamicQueueOfStacks);

        IQueueOfStacks suma = Example.sumaOfQueueOfStacks(dynamicQueueOfStacks, dynamicQueueOfStacks1);
        while (!suma.isEmpty()) {
            System.out.println(suma.getFirst());
            suma.remove();
        }
/*
        /*while (!dynamicQueueOfStacks.isEmpty()){
            int value = dynamicQueueOfStacks.getFirst();
            System.out.println(value);
            dynamicQueueOfStacks.remove();
        }*/
/*
        IQueueOfStacks traspuesta = Example.traspuestaOfQueueOfStacks(dynamicQueueOfStacks);
        while (!traspuesta.isEmpty()){
            System.out.println(traspuesta.getFirst());
            traspuesta.remove();
        }*/
        /*
        System.out.println("El valor de la traza es: " + Example.calcularTraza(dynamicQueueOfStacks1));
*/
        /*
        Dictionary dictionary = new DynamicDictionary();
        dictionary.add(1,2);
        dictionary.add(1,3);
        dictionary.add(2,2);
        ISet set = Example.setOfKeysAndValues(dictionary);
        while (!set.isEmpty()){
            int value = set.choose();
            System.out.println(value);
            set.remove(value);
        }*/



        /*

        IStack stack = new DynamicStack();
        stack.add(8);
        stack.add(5);
        stack.add(5);
        stack.add(5);
        stack.add(7);
        stack.add(5);
        stack.add(4);
        stack.add(2);
        boolean esSpecific = Example.specificStack(stack);
        System.out.println(esSpecific);


*/
        /*
        ISet bag = new Bag(40);
        bag.add(5);
        bag.add(5);
        bag.add(5);
        bag.add(-10);
        while (!bag.isEmpty()) {
            int value = bag.choose();
            System.out.println(value);
            bag.remove(value);
        }*/



        /*
        ISet set = new DynamicSet();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);
        set.add(6);

        boolean esInductive = Example.isInductive(set,1,7);
        System.out.println(esInductive);
*/
/*
        BinaryTree binaryTree = Example.createFibonacciTree(5);
        printByLevel(binaryTree, 5);


*/

        /*
        MultipleDictionaryWithStack multipleDictionary = new DynamicMultipleDictionaryWithStack();
        multipleDictionary.add(1,2);
        multipleDictionary.add(1,3);
        multipleDictionary.add(1,3);
        multipleDictionary.add(1,4);
        multipleDictionary.add(2,6);
        multipleDictionary.add(3,8);
        Example.printDynamicDictionaryWithStack(multipleDictionary);
        multipleDictionary.remove(2);
        Example.printDynamicDictionaryWithStack(multipleDictionary);
*/




        /*
        IStack stack = new DynamicStack();
        IQueue queue = new DynamicQueue();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(1);
        stack.add(1);
        stack.add(2);
        stack.add(3);
        IStackWithPriority stackWithPriority = Example.ordenarStackWithPriorityEnBaseAQueue(stack,queue);
        while (!stackWithPriority.isEmpty()){
            System.out.println("Value:" + stackWithPriority.getTop() + "--> Priority:" + stackWithPriority.getPriority());
            System.out.println();
            stackWithPriority.remove();
        }*/

    /*
        IQueue queue = new DynamicQueue();
        queue.add(2);
        queue.add(2);
        queue.add(3);
        queue.add(1);
        queue.add(2);
        queue.add(1);
        queue.add(2);
        IQueue candidate = new DynamicQueue();
        candidate = Example.encontrarSQMasLarga(queue);
        while (!candidate.isEmpty()){
            System.out.println(candidate.getFirst());
            candidate.remove();
=======
        int size = Example.encontrarLargoQueueSobre2(queue);
        //System.out.println(size);

        IQueue subQueueMasLarga = Example.encontrarSubQueueMasLarga(queue, size);
        while (!subQueueMasLarga.isEmpty()){
            System.out.println(subQueueMasLarga.getFirst());
            subQueueMasLarga.remove();
>>>>>>> taskQueue
        }

*/

        /*
        ISet set = Example.generarIdeal(4,8,1,2);
        while (!set.isEmpty()){
            int value = set.choose();
            System.out.println(value);
            set.remove(value);
        }*/



        /*DynamicStack stack = new DynamicStack();
        stack.addSinPotencia(1);
        stack.addSinPotencia(4);
        stack.addSinPotencia(2);
        stack.addSinPotencia(3);
        stack.addSinPotencia(6);
        stack.addSinPotencia(8);
        stack.addSinPotencia(4);

        while (!stack.isEmpty()){
            System.out.println(stack.getTop());
            stack.remove();
        }
*/

        /* arbol completo altura 3*/
        /*
        BinaryTree binaryTree = new DynamicBinaryTree(1);
        binaryTree.addLeft(2);
        binaryTree.addRight(3);
        binaryTree.getLeft().addLeft(4);
        binaryTree.getLeft().addRight(5);
        binaryTree.getRight().addLeft(6);
        binaryTree.getRight().addRight(7);

        print(binaryTree);

*/
        /*SearchBinaryTree binaryTree = new DynamicSearchBinaryTree(1);
        binaryTree.add(2);
        binaryTree.add(3);
        binaryTree.add(4);
        binaryTree.add(5);
        binaryTree.add(6);
        binaryTree.add(7);*/
        //boolean treeCompl = treeComplete(binaryTree);
        //System.out.println(treeCompl);
/*
        BinaryTree binaryTree1 = invertTree(binaryTree);
        printByLevel(binaryTree, 3);
        printByLevel(binaryTree1, 3);*/
    }

    public static boolean treeComplete (BinaryTree binaryTree){
        if (binaryTree == null) {
            return true;
        }
        if (binaryTree.getLeft() == null && binaryTree.getRight() == null) {
            return true;
        }
        if ((binaryTree.getRight() != null && binaryTree.getLeft() == null) || (binaryTree.getRight() == null && binaryTree.getLeft() != null)){
            return false;
        }
        return treeComplete(binaryTree.getRight()) && treeComplete(binaryTree.getLeft());
    }
        /* else if (binaryTree.getLeft() != null && binaryTree.getRight() == null){
            return false;
        } else {
            treeComplete(binaryTree.getLeft());
            treeComplete(binaryTree.getRight());
        }*/
    public static boolean esSesgado (BinaryTree binaryTree) {
        return esSesgadoDerecha(binaryTree) || esSesgadoIzquierda(binaryTree);
    }
    public static boolean esSesgadoIzquierda (BinaryTree binaryTree){
        if (binaryTree == null) {
            return true;
        }
        return false; /* TBD */
    }
    public static boolean esSesgadoDerecha (BinaryTree binaryTree){
        return false; // TBD
    }
    public static BinaryTree invertTree (BinaryTree binaryTree){
        if (binaryTree == null) {
            return binaryTree;
        } if (binaryTree.getRight() == null && binaryTree.getLeft() == null){
            return binaryTree;
        } else {
            binaryTree.addLeft(binaryTree.getRight().getRootValue());
            binaryTree.addRight(binaryTree.getLeft().getRootValue());
            return invertTree(binaryTree);
        }
    }

    public static void print (BinaryTree binaryTree){
        if (binaryTree == null){
            return;
        }
        System.out.println(binaryTree.getRootValue());
        print(binaryTree.getLeft());
        print(binaryTree.getRight());
    }
    public static void print2 (BinaryTree binaryTree){
        if (binaryTree.getLeft() != null){
            print2(binaryTree.getLeft());
        }
        System.out.println(binaryTree.getRootValue());
        if (binaryTree.getRight() != null){
            print2(binaryTree.getRight());
        }
    }
    public static void print3 (BinaryTree binaryTree){
        if (binaryTree.getRight() != null){
            print2(binaryTree.getRight());
        }
        System.out.println(binaryTree.getRootValue());
        if (binaryTree.getLeft() != null){
            print2(binaryTree.getLeft());
        }
    }

    public static int totalLevels (BinaryTree binaryTree){
        if (binaryTree == null){
            return 0;
        }
        return 1 + Math.max(totalLevels(binaryTree.getLeft()) , totalLevels(binaryTree.getRight()));
    }

    public static void printByLevel (BinaryTree binaryTree, int level){
        int current = totalLevels(binaryTree);
        if (current == level) {
            System.out.println(binaryTree.getRootValue());
            return;
        }
        if (binaryTree.getLeft() != null){
            printByLevel(binaryTree.getLeft(), level);
        }
        if (binaryTree.getRight() != null){
            printByLevel(binaryTree.getRight(), level);
        }
    }
    public static void printByLevel2 (BinaryTree binaryTree, int level, int init){
        if (binaryTree == null){
            return;
        }
        if (level == init){
            System.out.println(binaryTree.getRootValue());
            return;
        }
        if (binaryTree.getLeft() != null){
            printByLevel2(binaryTree.getLeft(), level, init +1);
        }
        if (binaryTree.getRight() != null){
            printByLevel2(binaryTree.getRight(), level, init +1);
        }
    }
    public static void print4 (BinaryTree binaryTree){
        if (binaryTree == null){
            return;
        }
        if (binaryTree.getLeft() != null){

        }
    }


}