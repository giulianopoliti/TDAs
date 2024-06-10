package org.example;

import org.example.adt.*;
import org.example.clazz.Example;

import java.util.Queue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        BinaryTree binaryTree = new DynamicBinaryTree(5);
        binaryTree = Example.findFibonacciTree(binaryTree);
        print(binaryTree);






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