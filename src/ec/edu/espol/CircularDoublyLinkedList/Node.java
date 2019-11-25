/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.CircularDoublyLinkedList;

/**
 *
 * @author danie
 */
public class Node<E> {
    private E element;
    private Node last=null;
    private Node next=null;

    public Node() {
    }
    public Node(E element){
        this.element = element;
    }

    public E getElement() {
        return element;
    }

    public Node getLast() {
        return last;
    }

    public Node getNext() {
        return next;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public void setLast(Node last) {
        this.last = last;
    }

    public void setNext(Node next) {
        this.next = next;
    }
    
}
