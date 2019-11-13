package ec.edu.espol.SimplyLinkedList;

import ec.edu.espol.List.List;
import java.util.Iterator;


/**
 *
 * @author Jorge Villalta
 * @param <E>
 */
public class LinkedList <E> implements List<E> {
    private Node<E> first, last;
    private int effectiveSize;
    
    public LinkedList(){
        first = last = null;
        effectiveSize = 0;
    }
    
    @Override
    public boolean addFirst(E element) {
        Node<E> nodo = new Node<>(element);
        if(element == null)
            return false;
        else if(isEmpty())
            first = last = nodo;
        else{
            nodo.setNext(first);
            first = nodo;
        }
        effectiveSize++;
        return true;
    }

    @Override
    public boolean addLast(E element) {
        Node<E> nodo = new Node<>(element);
        if(element == null)
            return false;
        else if(isEmpty())
            first = last = nodo;
        else{
            last.setNext(nodo);
            last = nodo;
        }
        
        effectiveSize ++;
        return true;
    }

    @Override
    public E getFirst() {
        return first.getData();
    }

    @Override
    public E getLast() {
        return last.getData();
    }

   
    @Override
    public boolean isEmpty() {
        return (first == null && last == null);
    }

    @Override
    public int size() {
        return effectiveSize;
    }
    
    public Node getFirstNode () {
        return first;
    }
    
    public Node getLastNode () {
        return last;
    }
    
    private Node<E> getNode(int index){
        Node<E> currentNode = this.first;
        for(int i = 0; i < index; i++){
            currentNode = currentNode.getNext();
        }
        return currentNode;
    }
    
    @Override
    public String toString(){
        //return "Implemente este método!";
        String str = null;
        for(int i = 0; i < effectiveSize; i++){
            if(i==0){
                str = getNode(i).getData().toString();
            }else{
                str = str + "," + getNode(i).getData().toString();
            }
        }
        return str;
    }

    @Override
    public boolean removeFirst() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeLast() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insert(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(E element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E get(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }

    @Override
    public int indexOf(E element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(E element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<E> iterator() {
    
            return  new Iterator<E>(){
            int pos = 0;
            @Override
            public boolean hasNext(){
                return pos < effectiveSize;
            }
            @Override
            public E next(){
                return getNode(pos++).getData(); 
            }
        };
    }
}