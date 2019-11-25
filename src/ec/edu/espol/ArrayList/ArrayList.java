package ec.edu.espol.ArrayList;
import ec.edu.espol.List.List;
import java.util.Iterator;

/**
 *
 * @author Jorge Villalta
 * @param <E>
 */
public class ArrayList<E> implements List<E>{
    private E[] array;
    private int capacity = 10;
    private int effectiveSize;
    
    public ArrayList(){ //Todas las TDA van a inicializar vacias
        //No se puede hacer new de E directamente, por ello hay que hacer casting 
        array = (E[]) new Object[capacity];    
        effectiveSize = 0;
    }
    
    @Override
    public boolean addFirst(E element) {
        if(element == null){
            return false;
        }else if(isEmpty()){
            array[effectiveSize ++] = element;
            return true;
        }else if (capacity == effectiveSize){
            addCapacity();
        }
        for(int i = effectiveSize -1 ; i >= 0; i-- ){
            array[i+1] = array[i];
        }
        
        
        array [0] = element;
        effectiveSize++;
        return true;   
    }
    
    @Override
    public boolean addLast(E element) {
        if(element == null){
            return false;
        }else if(effectiveSize == capacity){
            addCapacity();
        }
        array[effectiveSize++] = element;
        return true;
    }
    
    private void addCapacity(){
        E[] tmp = (E[]) new Object [capacity*2];
        for(int i = 0; i < capacity; i++){
            tmp[i] = array[i];
        }
        array = tmp;
        capacity = capacity*2;
    }
    
    @Override
    public E getFirst() {
        return array[0];
    }

    @Override
    public E getLast() {
        return array[effectiveSize -1];
    }

    @Override
    public boolean isEmpty() {
        return effectiveSize == 0;
    }


    @Override
    public E get(int index) {
        if(effectiveSize == 0 || index < 0 || index >= effectiveSize){
            return null;
        }
        return array[index];
    }
    
    @Override
    public String toString(){
        //return "Implemente este método!";
        String str =  null;
        for(int i = 0; i< effectiveSize; i++){
            if(i == 0){
                str = array[i].toString();
            }else{
                str = str + "," + array[i].toString();
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.array[index] = element;
        return element;
    }

    @Override
    public int size() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return this.effectiveSize;
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
                return array[pos++]; 
            }
        };
    }
    
}
