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
 * @author daniel
 */

public class Persona {
    
    private HashMap<String,Image> imagenes;
    private String personaje;
    private String asesino;
    private String muerto;
    
    private  ImageView currentImv;
    private final Label posicionLbl;
    private boolean vivo;
    private Integer posicion;

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
        
        this.personaje = "happy";
        this.asesino = "muerte";
        this.muerto = "lapida";
        
        currentImv = new ImageView(this.imagenes.get(this.personaje));
        currentImv.setScaleX(0.5);
        currentImv.setScaleY(0.5);
        this.vivo = true;
        this.posicion = posicion;
        posicionLbl = new Label(String.valueOf(this.posicion));
    }
    
    public void turnAsassin(){
        this.currentImv.setImage(this.imagenes.get(this.asesino));
    }
    public void turnVictim(){
        this.currentImv.setImage(this.imagenes.get("espantado"));
    }
    public void turnNormal(){
        System.out.println(this.personaje);
        this.currentImv.setImage(this.imagenes.get(this.personaje));
    }
    
    public void setPersonajeImage(String imName){
        this.personaje = imName;
    }
    
    public void setAssasingImage(String imName){
        this.asesino = imName;
    }
    
    public void setMuertoImage(String imName){
        this.muerto = imName;
    }
        
        

    public ImageView getCharacterImage() {
        return this.currentImv;
    }

    public boolean isAlife() {
        return this.vivo;
    }

    public int getPosicion() {
        return this.posicion;
    }

    public Label getPosLabel() {
        return this.posicionLbl;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public void kill() {
        this.currentImv.setImage(this.imagenes.get(this.muerto));
        this.vivo = false;
    }

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
