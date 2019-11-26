/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.main;

/**
 *
 * @author danie
 * @param <E>
 */
public class CircularDoublyLinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private Node<E> location;
    private Node<E> iteratorNode;
    private int numElements;
    private boolean found;

    public CircularDoublyLinkedList(){
        head = null;
        tail = null;
        numElements = 0;
        location = null;
    }

    public boolean isEmpty(){
        return (head == null);
    }

    public int size(){
        return numElements;
    }
    public int getSize(){
        return this.numElements;
    }

    protected void find(E target){
        location = head;
        found = false;
        if (!isEmpty()) {
            do {
                if (location.getData().equals(target)) // if they match
                {
                    found = true;
                    return;
                } else {
                    location = location.getNext();
                }
            } while (location != tail.getNext());
        }

    }

    public boolean contains(E element){
        find(element);
        return found;
    }

    protected void getIndexOf(int position){
        int start = 0;
        location = head;
        found = false;

        if ((!isEmpty()) && (position >= 0) && (position <= size())) {
            do {
                // move search to the next node
                location = location.getNext();
                start++;

            } while ((location != head) && start < position);
            found = true;
        }

    }

    public E get(E data) // Returns an element e from this list such that e.equals(element);
    // if no such element exists, returns null.
    {
        find(data);
        if (found) {
            return location.getData();
        } else {
            return null;
        }
    }

    public void reset() // Initializes current position for an iteration through this list,
    // to the first element on this list.
    {
        location = head;
    }

    public void add(E data) // Adds element to this list.
    {
        Node<E> newNode = new Node<E>(data);   // Reference to the new node being added

        if (isEmpty()) // Adding into an empty list
        {
            head = newNode;
            tail = newNode;
            head.setPrevious(tail);
            tail.setNext(head);
        } else // Adding into a non-empty list
        {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
            tail.setNext(head);
        }
        numElements++;
    }

    public void addFirst(E data){
        Node<E> newNode = new Node<E>(data);   // Reference to the new node being added
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            head.setPrevious(tail);
            tail.setNext(head);
        } else {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
            head.setPrevious(tail);
            tail.setNext(head);
        }
        numElements++;

    }

    public void addLast(E data){
        Node<E> newNode = new Node<E>(data);
        if (isEmpty()){
            head = newNode;
            tail = newNode;
            head.setPrevious(tail);
            tail.setNext(head);
        } else{
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
            head.setPrevious(tail);
            tail.setNext(head);
        }
        numElements++;

    }

    public void add(E data, int position) //adds new element to the specified position
    {
        Node<E> newNode = new Node<E>(data);

        if (isEmpty()) {
            // add element to an empty list
            head = newNode;
            tail = newNode;
            head.setPrevious(tail);
            tail.setNext(head);

        } else if (position <= 0) {
            // insert at front of the list
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
            head.setPrevious(tail);
            tail.setNext(head);

        } else if (position >= size()) {
            // if position does not exist, perform add at the most efficient
            // position for circular doubly linked list, the most efficient position is
            // at the end.		
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
            tail.setNext(head);

        } else {
            /* General Case */
            // determine location where to perform insert
            getIndexOf(position);

            //inserts the elements to the specified position
            newNode.setPrevious(location.getPrevious());
            newNode.setNext(location);
            location.getPrevious().setNext(newNode);
            location.setPrevious(newNode);

        }
        numElements++;
    }

    public boolean remove(E element) // Removes an element e from this list such that e.equals(element)
    // and returns true; if no such element exists, returns false.
    {
        find(element);
        if (found) {
            if (location == head && size() == 1) //removes the only existing element
            //empties the list
            {
                head = null;
                tail = null;

            } else if (location == head) //removes first node
            {
                head = head.getNext();
                head.setPrevious(tail);
                tail.setNext(head);

            } else if (location == tail) //removes last node
            {

                tail = tail.getPrevious();
                tail.setNext(head);
                head.setPrevious(tail);
            } else {						 // removes node at location
                location.getPrevious().setNext(location.getNext());
                location.getNext().setPrevious(location.getPrevious());
            }
            numElements--;
        }
        return found;
    }

    public void removeFront(){
        if (!isEmpty()) {
            if (head.getNext() == head) { 
                head = null;
                tail = null;
            } else {			//removes the first element
                head = head.getNext();
                head.setPrevious(tail);
                tail.setNext(head);
            }
        }
        numElements--;
    }

    public void removeBack(){
        if (!isEmpty()) {
            if (head.getNext() == head) { //if the last element is the only element in the list,
                //it empties the list      	
                head = null;
                tail = null;
            } else {				//removes the last element
                tail = tail.getPrevious();
                tail.setNext(head);
                head.setPrevious(tail);
            }
        }
        numElements--;
    }

    public void removeAt(int position){
        if (position <= 0) {		//removes front element
            head = head.getNext();
            head.setPrevious(tail);
            tail.setNext(head);
        } else if (position >= size() - 1) { //remove last element
            tail = tail.getPrevious();
            tail.setNext(head);
            head.setPrevious(tail);
        } else {
            //general case
            //determines the position
            getIndexOf(position);
            //removes the element in the specified position
            location.getPrevious().setNext(location.getNext());
            location.getNext().setPrevious(location.getPrevious());
        }
        numElements--;
    }
    
    public int getIndex(E element){
        for(int i = 0 ; i < this.size() ; i ++){
            if(element.equals(getNode(i).getData())){
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString(){
        String item = "CDL List: [ ";
        Node<E> current = head;
        if (!isEmpty()) {

            do {
                item += current.hashCode() + " data: "+ current.getData().hashCode() + "Previous: "+current.getPrevious().hashCode() + "next: "+ current.getNext().hashCode() + "\n";
                current = current.getNext();
            } while (current != head);
        }
        item += "]";
        return item;
    }

    public String printReverse() // prints the elements of the list in a nicely formated manner in reverse order
    {
        String item = "List: [ ";

        Node<E> current = head;

        if (!isEmpty()) {
            do {
                item += current.getData() + " ";
                current = current.getPrevious();
            } while (current != head);
        }
        item += "]";
        return item;
    }
    public Node<E> getNode(int index){
        Node<E> temp = this.head;
        for(int i = 0; i < index ; i++){
            temp = temp.getNext();
        }
        return temp;
    }
    
    public E get(int index){
        return getNode(index).getData();
    }
    
    public void resetItr(){
        this.iteratorNode = this.head;
    }
    public void setItr(int posicion){
        this.iteratorNode = getNode(posicion);
    }
    public boolean hasPrevE(){
        return this.size() > 1;
    }
    public E getPrevE(){
        Node<E> temp = this.iteratorNode;
        this.iteratorNode = this.iteratorNode.getPrevious();
        return temp.getData();
    }
    public boolean hasNextE(){
        return this.size() > 1;
    }
    public E getNextE(){
        Node<E> temp = this.iteratorNode;
        this.iteratorNode = this.iteratorNode.getNext();
        return temp.getData();
    }

}
