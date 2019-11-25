/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.CircularDoublyLinkedList;

import java.util.Iterator;

/**
 *
 * @author danie
 * @param <E>
 */
public class CircularDoublyLinkedList<E> implements Iterable<E>{
    private Node<E> startNode;
    private Node<E> finalNode;
    private Node<E> toReturn;
    private int size = 0;

    public CircularDoublyLinkedList() {
        this.size = 0;
    }
    
    
    
    public void addFirst(E element){
        if(size == 0){
            this.startNode = new Node(element);
            this.startNode.setLast(startNode);
            this.startNode.setNext(startNode);
        }else{
            Node<E> tempLast = this.finalNode;
            Node<E> tempNext = this.startNode;
            Node<E> nNode = new Node(element);
            nNode.setLast(tempLast);
            nNode.setNext(tempNext);
            this.startNode = nNode;
        }
        this.size++;
    }
    
    public void addLast(E element){
        if(size == 0){
            this.startNode = new Node(element);
            this.startNode.setLast(startNode);
            this.startNode.setNext(startNode);
            this.finalNode = this.startNode;
        }else{
            Node<E> tempLast  = this.finalNode;
            Node<E> tempNext  = this.startNode;
            Node<E> nNode = new Node(element);
            nNode.setLast(tempLast);
            nNode.setNext(tempNext);
            this.finalNode.setNext(nNode);
            this.finalNode = nNode;
        }
        this.size++;
    }
    
    public int getIndex(E element){
        Node currentNode = this.startNode;
        for(int i = 0; i < size ; i++){
            if(currentNode.getElement().equals(element)){
                return i;
            }
        }
        return -1;
    }
    
    public E get(int index){
        Node<E> toReturn = this.startNode;
        for(int i = 0; i< index ; i++){
            toReturn = toReturn.getNext();
        }
        return toReturn.getElement();
    }
    
    public int getSize(){
        return this.size;
    }
    
    public boolean hasNext(){
        return this.size != 0;
    }
    public E getNext(){
        Node<E> temp = this.toReturn;
        this.toReturn= this.toReturn.getNext();
        return temp.getElement();
    }
    
    public boolean hasLast(){
        return this.size != 0;
    }
    public E getLast(){
        Node<E> temp = this.toReturn;
        this.toReturn = this.toReturn.getLast();
        return temp.getElement();
    }
    public void setItr(int index){
        this.toReturn = getNode(index);
    }
    public void resetItr(){
        this.toReturn = this.startNode;
    }
    
    public Node<E> getNode(int index){
        Node<E> nodeItr = this.startNode;
        for(int i = 0 ; i < index ; i++){
            nodeItr = nodeItr.getNext();
        }
        return nodeItr;
    }

    @Override
    public Iterator<E> iterator() {
            return  new Iterator<E>(){
            int pos = 0;
            @Override
            public boolean hasNext(){
                return true;
            }
            @Override
            public E next(){
                return get(pos); 
            }
        };
    }

    
    
}
