/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.CircularDoublyLinkedList;

/**
 *
 * @author Jorge Villalta
 * @param <E>
 */
public class Node<E> {
    private Node<E> lastNode;
    private Node<E> nextNode;
    private E element;

    public Node(Node<E> lastNode, Node<E> nextNode, E element) {
        this.lastNode = lastNode;
        this.nextNode = nextNode;
        this.element = element;
    }

    public Node<E> getLastNode() {
        return lastNode;
    }

    public Node<E> getNextNode() {
        return nextNode;
    }

    public E getElement() {
        return element;
    }

    public void setLastNode(Node<E> lastNode) {
        this.lastNode = lastNode;
    }

    public void setNextNode(Node<E> nextNode) {
        this.nextNode = nextNode;
    }

    public void setElement(E element) {
        this.element = element;
    }
    
    
    
    
    
}
