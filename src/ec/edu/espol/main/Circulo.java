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

//Abstracción de lo que se ve en pantalla
public class Circulo<E> extends Thread {

    private final int size;//numero de elementos que contiene el circulo
    private int milliseconds;//para controlar la velocidad de la simulación
    private final CircularPane numerosPane;//Pane de javafx que muestra los label de las posiciones
    private final CircularPane charactersPane;//Pane de java fx que muestra los imageview de los personajes
    private final CircularDoublyLinkedList<Persona> listaPersonas;//Lista circular que contiene las personas de la simulacion
    private boolean horario;//define si la simulacion se hace en sentido horario o no
    private int startKiller;//define el indice en el que se inicia la simulacion
    private boolean threadRunning;//define si la simulacion está en curso o no
    private boolean threadPaused;//define si el hilo de la simulación esta en curso y ha sido pausado

    //El constructor recibe el numero de personas que tendrá el circulo
    public Circulo(int size) {
        listaPersonas = new CircularDoublyLinkedList();
        startKiller = 0;
        milliseconds = 1000;//por defecto
        horario = true;//sentido horario por defecto
        numerosPane = new CircularPane(365);//se crea el pane circular 
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

    //En caso de querer definir quién inicia la simulacion.
    public void setStartKiller(int posicion) {
        this.startKiller = posicion;
    }

    //Informa si la simulacion está pausada
    public boolean isPaused() {
        return this.threadPaused;
    }

    //Pausa la simulación
    public void pause() {
        this.threadPaused = true;
        try {
            this.suspend();
        } catch (Exception e) {
        }
    }

    //Resumir la simulacion
    public void resum() {
        this.threadPaused = false;
        try {
            this.resume();
        } catch (Exception e) {
        }
    }

    //ALGORITMO QUE EJECUTA LA SIMULACIÓN: se ejecuta en paralelo al hilo principal(el cual controla la interfaz)
    @Override
    public void run() {
        this.threadRunning = true;//Setea el atributo indicando que la simulacion esta en curso
        System.out.println(this.horario);
        System.out.println("La simulacion ha empezado");
        int deadsNumber = 0;
        Persona personItr = this.listaPersonas.get(startKiller - 1);
        //Por defecto o si se ha definido sentido horario
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
            //Si se ha definido sentido antihorario
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
        this.threadRunning = false;//Setea el atributo indicando que la simulacion terminó
    }

    //Incrementa al doble la velocidad de la simulacion
    public void increasteRunTime() {
        if (this.milliseconds < 2000) {
            this.milliseconds = this.milliseconds * 2;
        }
    }

    //Reduce a la mitad la velocidad de la simulación
    public void decreaseRunTime() {
        if (this.milliseconds > 125) {
            this.milliseconds = this.milliseconds / 2;
        }
    }

    //Para definir el sentido de la simulación
    public void setKillSense(int i) {
        if (i == 1) {
            this.horario = true;
        } else if (i == -1) {
            this.horario = false;
        }
    }

    //Informa si la simulacion está corriendo
    public boolean isRunnig() {
        return this.threadRunning;
    }

    //Retorna la cantidad de personas de la simulación
    public int getSize() {
        return size;
    }
    
    public CircularPane getCharactersPane(){
        return this.charactersPane;
    }
    
    public CircularPane getNumerosPane(){
        return this.numerosPane;
    }

    //Actualiza los graficos de la interfaz en funcion de los parametros recibidos
    public void updateGraphics(String personaje, String asesino, String muerte) {
        for (Persona p : this.listaPersonas) {
            if (personaje != null) {
                p.setPersonajeImage(personaje);
                p.turnNormal();
            }
            if (asesino != null) {
                p.setAssasingImage(asesino);
            }
            if (muerte != null) {
                p.setMuertoImage(muerte);
            }
        }
        this.charactersPane.update();
    }

}
