/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.SimplyLinkedList;

/**
 *
 * @author jorgevillalta
 * @param <E>
 */
public class Node<E> {
    private E content;//Contenido del nodo
    private Node<E> next;//Referencia al siguiente nodo

    public Node(E element, Node<E> e) {
        this.content = element;
        this.next = e;
    }

    public E getContent() {
        return content;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }
    

    
    
    
    
}
