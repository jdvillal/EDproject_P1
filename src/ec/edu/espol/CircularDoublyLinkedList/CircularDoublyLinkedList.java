/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.CircularDoublyLinkedList;

import java.util.ListIterator;
import java.util.function.Consumer;

/**
 *
 * @author Jorge Villalta
 * @param <E>
 */
public class CircularDoublyLinkedList<E> implements ListIterator<E> {
    private Node<E> startNode = null;
    private Node<E> lastReturned = null;
    private int index;
    private int size;

    public CircularDoublyLinkedList() {
        this.size = 0;
    }
 

    @Override
    public boolean hasNext() {
        return index < size;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E next() {
        if(lastReturned == null){
            lastReturned = startNode;
            this.index = this.index +1 ;
            return lastReturned.getElement();
        }else{
            lastReturned = lastReturned.getNextNode();
            this.index = this.index +1 ;
            return lastReturned.getElement();
        }
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasPrevious() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E previous() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int nextIndex() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int previousIndex() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void set(E e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(E e) {
        if(this.size == 0){
            this.startNode = new Node(null,null,e);
            
        }else{
            Node<E> last = startNode.getLastNode();
            Node<E> next = startNode.getNextNode();
            
        }
        this.size = this.size + 1;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void forEachRemaining(Consumer<? super E> action) {
        ListIterator.super.forEachRemaining(action); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString(){
        //return "Implemente este método!";
        String str = null;
        Node<E> cn = startNode;
        for(int i = 0; i < size; i++){
            if(i==0){
                System.out.println(cn.getElement().toString());
                str = cn.getElement().toString();
            }else{
                System.out.println(cn.getElement().toString());
                str = str + "," + cn.getElement().toString();
            }
            cn = cn.getNextNode();
        }
        return str;
    }

    


}
