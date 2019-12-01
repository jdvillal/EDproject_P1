/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.main;

import customPane.CircularPane;

/**
 *
 * @author daniel
 * @param <E>
 */

public class Circulo<E> extends Thread {

    private final int size;
    private int milliseconds;
    private final CircularPane numerosPane;
    private final CircularPane charactersPane;
    private final CircularDoublyLinkedList<Persona> listaPersonas;
    private boolean horario;
    private int startKiller;
    private boolean threadRunning;
    private boolean threadPaused;

    public Circulo(int size) {
        listaPersonas = new CircularDoublyLinkedList();
        startKiller = 0;
        milliseconds = 1000;
        horario = true;
        numerosPane = new CircularPane(365);
        charactersPane = new CircularPane(330);
        this.size = size;
        this.threadRunning = false;
        this.threadPaused = false;
        for (int i = 1; i <= size; i++) {
            Persona nPersona = new Persona(i);
            listaPersonas.addLast(nPersona);
            numerosPane.getChildren().add(nPersona.getPosLabel());
            charactersPane.getChildren().add(nPersona.getCharacterImage());
        }
    }

    public void setStartKiller(int posicion) {
        this.startKiller = posicion;
    }

    public boolean isPaused() {
        return this.threadPaused;
    }

    public void pause() {
        this.threadPaused = true;
        try {
            this.suspend();
        } catch (Exception e) {
        }
    }

    public void resum() {
        this.threadPaused = false;
        try {
            this.resume();
        } catch (Exception e) {
        }
    }

    @Override
    public void run() {
        this.threadRunning = true;
        System.out.println(this.horario);
        System.out.println("La simulacion ha empezado");
        int deadsNumber = 0;
        Persona personItr = this.listaPersonas.get(startKiller - 1);
        if (this.horario) {
            try {
                while (deadsNumber != this.size) {
                    Persona toKill = personItr.checkNextAlife(listaPersonas);
                    personItr.turnAsassin();
                    toKill.turnVictim();
                    deadsNumber++;
                    this.charactersPane.update();
                    sleep(milliseconds);
                    toKill.kill();
                    personItr.turnNormal();
                    this.charactersPane.update();
                    personItr = toKill.checkNextAlife(listaPersonas);
                }
            } catch (Exception e) {
            }
        } else {
            try {
                while (deadsNumber != this.size) {
                    Persona toKill = personItr.checkLastAlife(listaPersonas);
                    personItr.turnAsassin();
                    toKill.turnVictim();
                    deadsNumber++;
                    this.charactersPane.update();
                    sleep(milliseconds);
                    toKill.kill();
                    personItr.turnNormal();
                    this.charactersPane.update();
                    personItr = toKill.checkLastAlife(listaPersonas);
                }
            } catch (Exception e) {
            }
        }
        this.threadRunning = false;
    }
    
    public void increasteRunTime(){
        if(this.milliseconds < 2000){
            this.milliseconds = this.milliseconds*2;
        }
    }
    
    public void decreaseRunTime(){
        if(this.milliseconds > 125){
            this.milliseconds = this.milliseconds/2;
        }
    }

    public void setKillSense(int i) {
        if (i == 1) {
            this.horario = true;
        } else if (i == -1) {
            this.horario = false;
        }
    }

    public boolean isRunnig() {
        return this.threadRunning;
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
}
