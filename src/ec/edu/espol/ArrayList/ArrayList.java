/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.ArrayList;

import ec.edu.espol.List.List;
import java.util.Iterator;

/**
 *
 * @author jorgevillalta
 */
public class ArrayList<E> implements List<E> {
    private int capacity = 100;
    private int size = 0;
    private E[] elements = (E[]) new Object[capacity];;

    public ArrayList() {
    }
    
    //Se crea un arrego estatico nuevo en caso de que el arreglo elements actual llegue a su limite.
    private void addCapacity(){
        this.capacity = this.capacity * 2;
        E tempArray[] = (E[]) new Object[this.capacity];
        for(int i = 0; i < this.size; i++){
            tempArray[i] = this.elements[i];
        }
        this.elements = tempArray;
    }
    
    //Metodo para agregar elementos en el inicio de la lista
    @Override
    public boolean addFirst(E e) {
        if(e == null){
            return false;
        }else{
            if(this.size == this.capacity){
                addCapacity();
            }
            for(int i = size; i > 0; i--){
                this.elements[i] = this.elements[i-1];
            }
            this.elements[0] = e;
            size = size + 1;
            return true;
        }
    }

    //Metodo para agregar elementos al final de la lista
    @Override
    public boolean addLast(E e) {
        if(e == null){
            return false;
        }else{
            if(this.size == this.capacity){
                addCapacity();
            }
            this.elements[size] = e;
            size = size + 1;
            return true;
        }
    }

    //Metodo par agregar elementos en el indice especificado
    @Override
    public boolean add(int index, E element) {
        if(element == null){
            return false;
        }else{
            if(this.size == this.capacity){
                addCapacity();
            }
            int i = size;
            while(i > index){
                this.elements[i] = this.elements[i-1];
                i = i-1;
            }
            this.elements[index] = element;
            size = size + 1;
            return true;
        } 
    }

    //Metodo para remover elementos en el indice especificado
    @Override
    public E remove(int index) {
        E borrado = this.elements[index];
        for(int i = index; i < size; i++){
            this.elements[i] = this.elements[i+1];
        }
        this.size = this.size - 1;
        return borrado;
    }

    //Metodo para obtener el elemento en el indice especificado
    @Override
    public E get(int index) {
        return this.elements[index];
    }

    //Metodo para reemplazar un elemento en el indice especificado
    @Override
    public E set(int index, E element) {
        E seteado = this.elements[index];
        this.elements[index] = element;
        return seteado;
    }

    //Metodo que devuelve el numero de elementos en la lista
    @Override
    public int size() {
        return this.size;
    }

    //Metodo para saber si la lista está vacía
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    //Metodo para remover todos los elementos de la lista
    @Override
    public void clear() {
        this.capacity = 100;
        this.size = 0;
        this.elements = (E[]) new Object[capacity];
    }
    
    
    @Override
    public String toString(){
        String str = "";
        for(int i = 0; i < size; i++){
            String cs = this.elements[i].toString();
            if(i == 0){
                str = cs;
            }else{
                str = str + "," +cs;
            }
 
        }
        return "["+str+"]";
    }
    
    //Iterador de la lista para los bucles for each
    @Override
    public Iterator<E> iterator() {
            return  new Iterator<E>(){
            int pos = 0;
            @Override
            public boolean hasNext(){
                return pos < size;
            }
            @Override
            public E next(){
                return elements[pos++]; 
            }
        };
    }
    
    
}
