package ec.edu.espol.main;

public class Node<E> {
	private Node<E> next;
	private Node<E> previous;
	private E data;
        
        public Node(){
        }
	
	public Node(E data){
		  this.data = data;
	      next = null;
	      previous = null;
	}

	public Node<E> getNext(){
		return next;
	}

	public void setNext(Node<E> next){
		this.next = next;
	}

	public E getData(){
		return data;
	}

	public void setData(E data){
		this.data = data;
	}
	
	public Node<E> getPrevious(){
		return previous;
	}
	
	public void setPrevious(Node<E> previous){
		this.previous = previous;	
	}

}
