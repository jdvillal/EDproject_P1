/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.SimplyLinkedList;

import ec.edu.espol.List.List;
import java.util.Iterator;

/**
 *
 * @author jorgevillalta
 */

public class LinkedList<E> implements List<E>{
    private Node<E> first;//Nodo que encabeza la lista
    private Node<E> last;//Nodo al final de la lista
    private int size; //tamaño efectivo de la lista
    
    
    public LinkedList(){
       this.first = null;
       this.last = null;
       this.size = 0;
        
    }
    
    
    //Agrega elemento al inicio de la lista
    @Override
    public boolean addFirst(E e) {
        if(e == null){
            return false;
        }else{
            if(this.first == null){
                Node<E> nN = new Node(e,null);
                this.first = nN;
            }else{
                Node<E> nN = new Node(e,this.first);
                this.first = nN;     
            }
            this.size = this.size + 1;
            return true;
        }
    }

    //Agrega elemento al final de la lista
    @Override
    public boolean addLast(E e) {
        if(e == null){
            return false;
        }else{
            if(this.size == 0){
                addFirst(e);
            }else if(this.size == 1){
                Node<E> nN = new Node(e, null);
                this.first.setNext(nN);
                this.last = nN;
                this.size = this.size + 1;
            }else{
                Node<E> nN = new Node(e, null);
                this.last.setNext(nN);
                this.last = nN;
                this.size = this.size + 1;
            }
            return true;
        }
    }

    //Agrega elemento en el indice especificado
    @Override
    public boolean add(int index, E element) {
        if(element == null){
            return false;
        }else{
            if(index == 0){
                addFirst(element);
            }else if(index == this.size ){
                addLast(element);
            }else{
                Node<E> lastNode = getNode(index-1);
                Node<E> nextNode = getNode(index);
                Node<E> newNode = new Node(element,nextNode);
                lastNode.setNext(newNode);
                 this.size = this.size + 1;
            }
           
            return true;
        } 
    }

    //Remueve elemento en el indice especificado
    @Override
    public E remove(int index) {
        Node<E> toRemoveNode = getNode(index);
        Node<E> lastNode = getNode(index - 1);
        Node<E> nextNode = getNode(index + 1);
        lastNode.setNext(nextNode);
        return toRemoveNode.getContent();
    }
    
    //Metodo getNode: obtener nodo en posicion n
    private Node<E> getNode(int index){
        Node<E> currentNode = this.first;
        for(int i = 0; i < index; i ++){
            currentNode = currentNode.getNext();
        }
        return currentNode;
    }

    //Devuelve el nodo en la posicion indicada
    @Override
    public E get(int index) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return getNode(index).getContent();
    }

    //Reemplaza un elemento en la posicion especificada
    @Override
    public E set(int index, E element) {
        E toSetElement = getNode(index).getContent();
        getNode(index).setContent(element);
        return toSetElement;
    }

    //Devuelve el numero de elementos en la lista
    @Override
    public int size() {
        return this.size;
    }

    //Informa si la lista esta vacía
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    //Elimina todos los elementos en la lista
    @Override
    public void clear() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }
    
    
    @Override
    public String toString(){
        String str = null;
        for(int i = 0; i < size; i++){
            //System.out.println(i);
            if(i == 0){
                String s = getNode(i).getContent().toString();
                str = s;
            }else{
                String s = getNode(i).getContent().toString();
                str = str + "," + s;
            }
        }
        return "["+ str+"]";
    }

    
    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    
}
