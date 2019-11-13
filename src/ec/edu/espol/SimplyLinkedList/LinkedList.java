package ec.edu.espol.SimplyLinkedList;

import ec.edu.espol.List.List;
import java.util.Iterator;

/**
 *
 * @author Jorge Villalta
 * @param <E>
 */
public class LinkedList<E> implements List<E> {

    private Node<E> first, last;
    private int effectiveSize;

    public LinkedList() {
        first = null;
        last = null;
        effectiveSize = 0;
    }

    @Override
    public boolean addFirst(E element) {
        Node<E> newNode = new Node<>(element);
        if (element == null) {
            return false;
        } else if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            Node<E> temp = this.first;
            this.first = newNode;
            this.first.setNext(temp);
        }
        effectiveSize++;
        return true;
    }

    @Override
    public boolean addLast(E element) {
        Node<E> newNode = new Node(element);
        if (element == null) {
            return false;
        } else if (isEmpty()) {
            this.first = newNode;
            this.last = newNode;
        } else {
            this.last.setNext(newNode);
            this.last = newNode; 
        }
        effectiveSize++;
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
        return (this.effectiveSize == 0);
    }

    @Override
    public int size() {
        return effectiveSize;
    }

    private Node<E> getNode(int index) {
        Node<E> currentNode = this.first;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode;
    }

    @Override
    public String toString() {
        //return "Implemente este método!";
        String str = null;
        for (int i = 0; i < effectiveSize; i++) {
            if (i == 0) {
                str = getNode(i).getData().toString();
            } else {
                str = str + "," + getNode(i).getData().toString();
            }
        }
        return str;
    }

    @Override
    public boolean removeFirst() {
        if (this.effectiveSize != 0) {
            Node<E> nod = this.first.getNext();
            this.first = nod;
            this.effectiveSize--;
            return true;
        } else {
            return false;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeLast() {
        if (this.effectiveSize != 0) {
            Node<E> nod = getNode(this.effectiveSize - 1);
            this.last = nod;
            this.last.setNext(null);
            this.effectiveSize--;
            return true;
        } else {
            return false;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insert(int index, E element) {
        if(element == null){
            return false;
        }else{
            if(index == 0){
                addFirst(element);
            }else if(index == this.effectiveSize){
                addLast(element);
            }else{
                Node<E> lastNode = getNode(index-1);
                Node<E> nextNode = getNode(index);
                Node<E> newNode = new Node(element);
                newNode.setNext(nextNode);
                lastNode.setNext(newNode);
                this.effectiveSize++;
            }
            return true;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(E element) {
        Node<E> currentNode = this.first;
        for (int i = 0; i < this.effectiveSize; i++) {
            if (currentNode.getData().equals(element)) {
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E get(int index) {
        return getNode(index).getData();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public int indexOf(E element) {
        for(int i = 0; i < effectiveSize;i++){
            if(getNode(i).getData().equals(element)){
                return i;
            }
        }
        return -1;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E remove(int index) {
        Node<E> toRemoveNode = getNode(index);
        Node<E> lastNode = getNode(index-1);
        Node<E> nextNode = getNode(index+1);
        lastNode.setNext(nextNode);
        return toRemoveNode.getData();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

        return new Iterator<E>() {
            int pos = 0;

            @Override
            public boolean hasNext() {
                return pos < effectiveSize;
            }

            @Override
            public E next() {
                return getNode(pos++).getData();
            }
        };
    }
}
