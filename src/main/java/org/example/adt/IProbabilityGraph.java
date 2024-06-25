package org.example.adt;

public interface IProbabilityGraph {

    /**
     * Precondicion: El nodo no existe.
     * @param node, valor del nodo a agregar.
     */
    void addNode(int node);

    /**
     * Precondicion: El nodo existe.
     * @param node, valor que representa el nodo a borrar.
     */
    void removeNode(int node);

    /**
     * @return conjunto con todos los nodos presentes.
     */
    ISet nodes();

    /**
     * Precondicion: No existe la arista pero existen los nodos.
     * @param from, nodo del cual sale la arista.
     * @param to, nodo al cual llega la arista.
     * @param probability, probabilidad de la arista.
     */
    void addEdge(int from, int to, double probability);

    /**
     * Precondicion: Existe la arista
     * @param from, nodo del cual sale la arista.
     * @param to, nodo al cual llega la arista.
     */
    void removeEdge(int from, int to);

    /**
     * Precondicion: Existen los nodos
     * @param from, nodo del cual sale la arista.
     * @param to, nodo al cual llega la arista.
     * @return <code>true</code> si la arista existe, <code>false</code> en otro caso.
     */
    boolean edgeExists(int from, int to);

    /**
     * Precondicion: Existe la arista
     * @param from, nodo del cual sale la arista.
     * @param to, nodo al cual llega la arista.
     * @return probability de la arista.
     */
    double probability(int from, int to);

}
