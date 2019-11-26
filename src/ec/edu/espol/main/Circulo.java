/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.main;

import customPane.CircularPane;

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
    private CircularDoublyLinkedList<Persona> listaPersonas = new CircularDoublyLinkedList();
    private boolean horario;
    private int startKiller;

    public Circulo(int size) {
        startKiller = 0;
        milliseconds = 500;
        horario = true;
        numerosPane = new CircularPane(360);
        charactersPane = new CircularPane(320);
        //listaPersonas = new CircularDoublyLinkedList();
        this.size = size;
        for (int i = 1; i <= size; i++) {
            Persona nPersona = new Persona(i);
            listaPersonas.addLast(nPersona);
            numerosPane.getChildren().add(nPersona.getPosLabel());
            charactersPane.getChildren().add(nPersona.getCharacterImage());
        }
    }
    
    public void setStartKiller(int posicion){
        this.startKiller = posicion;
    }

    @Override
    public void run() {
        System.out.println(this.horario);
        System.out.println("La simulacion ha empezado");
        int deadsNumber = 0;
        Persona personItr = this.listaPersonas.get(startKiller-1);
        if (this.horario) {
            try {
                while (deadsNumber != this.size) {
                    //System.out.println(personItr.getPosicion());
                    Persona toKill = personItr.checkNextAlife(listaPersonas);
                    toKill.kill();
                    deadsNumber++;
                    personItr = toKill.checkNextAlife(listaPersonas);
                    this.charactersPane.update();
                    sleep(milliseconds);
                }
            } catch (Exception e) {
            }
        } else {
            try {
                while (deadsNumber != this.size) {
                    //System.out.println(personItr.getPosicion());
                    Persona toKill = personItr.checkLastAlife(listaPersonas);
                    toKill.kill();
                    deadsNumber++;
                    personItr = toKill.checkLastAlife(listaPersonas);
                    this.charactersPane.update();
                    sleep(milliseconds);
                }
            } catch (Exception e) {
            }
        }
    }

    public void setKillSense(int i) {
        if (i == 1) {
            this.horario = true;
        } else if (i == -1) {
            this.horario = false;
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
        int c = p.getPosicion();
        listaPersonas.setItr(c);
        Persona personItr = null;
        while (c != p.getPosicion() - 1) {
            personItr = listaPersonas.getNextE();
            if (personItr.isAlife() && !personItr.equals(p)) {
                listaPersonas.resetItr();
                break;
            }
            c = personItr.getPosicion();
        }
        System.out.println("Next found " + personItr.getPosicion());
        return personItr;
    }

    public Persona getLastAlive(Persona p) {
        listaPersonas.setItr(p.getPosicion());
        while (listaPersonas.hasPrevE()) {
            Persona temp = listaPersonas.getPrevE();
            System.out.print(temp.getPosicion() + " " + temp.isAlife() );
            if (temp.equals(p)) {
                break;
            }
            if (temp.isAlife()) {
                return temp;
            }
        }
        return null;
    }

}
