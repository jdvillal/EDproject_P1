/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.main;

import customPane.CircularPane;
import ec.edu.espol.CircularDoublyLinkedList.CircularDoublyLinkedList;
import ec.edu.espol.CircularDoublyLinkedList.CircularDoublyLinkedList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author danie
 * @param <E>
 */
public class Circulo<E> extends Thread {

    private int size;
    private int milliseconds;
    private CircularPane numerosPane;
    private CircularPane charactersPane;
    private CircularDoublyLinkedList<Persona> listaPersonas;

    public Circulo(int size) {
        milliseconds = 1500;
        numerosPane = new CircularPane(360);
        charactersPane = new CircularPane(320);
        listaPersonas = new CircularDoublyLinkedList();
        this.size = size;
        for (int i = 1; i <= size; i++) {
            Persona nPersona = new Persona(i);
            listaPersonas.addLast(nPersona);
            numerosPane.getChildren().add(nPersona.getPosLabel());
            charactersPane.getChildren().add(nPersona.getCharacterImage());
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("La simulacion ha empezado");
            int deadsNumber = 0;
            Persona personItr = this.listaPersonas.get(0);
            while (deadsNumber != this.size) {
                System.out.println(personItr.getPosicion());
                Persona toKill = getNextAlive(personItr);
                toKill.kill();
                deadsNumber++;
                personItr = getNextAlive(toKill);
                this.charactersPane.update();
                sleep(milliseconds);
            }
        } catch (Exception e) {
        }
    }

    public int getSize() {
        return size;
    }

    public CircularPane getNumerosPane() {
        return numerosPane;
    }

    public CircularPane getCharactersPane() {
        return charactersPane;
    }

    public Persona getNextAlive(Persona p) {
        int c = p.getPosicion()+1;
        listaPersonas.setItr(c);
        Persona personItr = null;
        while(c != p.getPosicion()){
            personItr = listaPersonas.getNext();
            if(personItr.isAlife() && !personItr.equals(p)){
                listaPersonas.resetItr();
                break;
            }
            c = personItr.getPosicion();
        }
        return personItr;
    }

    public Persona gteLastAlive(Persona p) {
        int c = p.getPosicion()-1;
        listaPersonas.setItr(c);
        Persona personItr = null;
        while(c != p.getPosicion()){
            personItr = listaPersonas.getLast();
            if(personItr.isAlife() && !personItr.equals(p)){
                listaPersonas.resetItr();
                break;
            }
            c = personItr.getPosicion();
        }
        return personItr;
    }

}
