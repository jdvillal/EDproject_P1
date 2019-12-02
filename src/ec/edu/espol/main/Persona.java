/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.main;

import java.util.HashMap;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Abstracción de las personas que conforman el circulo que se ve en pantalla
 * @author daniel
 */

public class Persona {
    
    private HashMap<String,Image> imagenes; //HashMap que contiene todas las representaciones posibles de la persona en patalla (ImagesView)
    private String personaje; //Clave para obtener la imagen(del HashMap imagenes) para representar a la persona en un estado normal
    private String asesino;//Clave para obtener la imagen(del HashMap imagenes) para reprersentar a la persona cuando esté en turno de matar a otra
    private String muerto;//Clave para obtener la imagen(del HashMap imagenes) para representar a la persona cuando ha muerto.
    
    private  ImageView currentImv;//Imagen que representa a la persona en la UI
    private final Label posicionLbl;//Label que indica  la posicion de la persona dentro del circulo en la UI.
    private boolean vivo;//Indica si la persona está viva o muerta
    private Integer posicion;//Registra la posicion de la persona en el circulo

    public Persona(int posicion) {
        this.imagenes = new HashMap<>();
        this.imagenes.put("happy", new Image(getClass().getResourceAsStream("happy.png")));
        this.imagenes.put("smile", new Image(getClass().getResourceAsStream("smile.png")));
        this.imagenes.put("jason", new Image(getClass().getResourceAsStream("jason.png")));
        this.imagenes.put("muerte", new Image(getClass().getResourceAsStream("muerte.png")));
        this.imagenes.put("ninja", new Image(getClass().getResourceAsStream("ninja.png")));
        this.imagenes.put("fantasma", new Image(getClass().getResourceAsStream("fantasma.png")));
        this.imagenes.put("tiza", new Image(getClass().getResourceAsStream("tiza.png")));
        this.imagenes.put("lapida", new Image(getClass().getResourceAsStream("lapida.png")));
        this.imagenes.put("skull", new Image(getClass().getResourceAsStream("skull.png")));
        this.imagenes.put("espantado", new Image(getClass().getResourceAsStream("espantado.png")));
        
        this.personaje = "happy";//por defecto
        this.asesino = "muerte";//por defecto
        this.muerto = "tiza";//por defecto
        
        currentImv = new ImageView(this.imagenes.get(this.personaje));
        currentImv.setScaleX(0.5);
        currentImv.setScaleY(0.5);
        this.vivo = true;
        this.posicion = posicion;
        posicionLbl = new Label(String.valueOf(this.posicion));
    }
    
    //Setea la imagen mostrada por pantalla cuando la persona sea el asesino en un turno
    public void turnAsassin(){
        this.currentImv.setImage(this.imagenes.get(this.asesino));
    }
    //Setea la imagen mostrada por pantalla cuando la persona va a morir en un turno
    public void turnVictim(){
        this.currentImv.setImage(this.imagenes.get("espantado"));
    }
    //Devuelve la imagen a la de la persona en su estado normal
    public void turnNormal(){
        System.out.println(this.personaje);
        this.currentImv.setImage(this.imagenes.get(this.personaje));
    }
    
    //Setea la clave para cambiar la imagen normal por defecto.
    public void setPersonajeImage(String imName){
        this.personaje = imName;
    }
    //Setea la clave para cambiar la imagen asesino por defecto.
    public void setAssasingImage(String imName){
        this.asesino = imName;
    }
    //Setea la clave para cambiar la imagen muerto por defecto.
    public void setMuertoImage(String imName){
        this.muerto = imName;
    }
        
        
    //Devuelve la imagen que representa a la persona en la UI.
    public ImageView getCharacterImage() {
        return this.currentImv;
    }

    //Informa si la persona está muerta o no.
    public boolean isAlife() {
        return this.vivo;
    }

    //Devuelve la posicion de la persona en el circulo
    public int getPosicion() {
        return this.posicion;
    }

    //Devuelve el label que representa la posicion de la persona en la UI.
    public Label getPosLabel() {
        return this.posicionLbl;
    }

    //Cambia el estado de la persona a muerto y cambia la imagen (a muerto) que representa a la persona en la UI.
    public void kill() {
        this.currentImv.setImage(this.imagenes.get(this.muerto));
        this.vivo = false;
    }

    //Recive una lista circular en la que la persona ha sido agregada y retorna a la perdona anterior que aun está viva.
    public Persona checkLastAlife(CircularDoublyLinkedList<Persona> cl) {
        cl.setItr(cl.getIndex(this) - 1);
        for (int i = 0; i < cl.size(); i++) {
            Persona p = cl.getPrevE();
            if (p.isAlife() && !p.equals(this)) {
                return p;
            }
        }
        return null;
    }

    //Recive una lista circular en la que la persona ha sido agregada y retorna a la persona siguiente que aun está viva.
    public Persona checkNextAlife(CircularDoublyLinkedList<Persona> cl) {
        cl.setItr(cl.getIndex(this) + 1);
        for (int i = 0; i < cl.size(); i++) {
            Persona p = cl.getNextE();
            if (p.isAlife() && !p.equals(this)) {
                return p;
            }
        }
        return null;
    }

}
