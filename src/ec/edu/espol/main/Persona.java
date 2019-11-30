/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.main;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author danie
 */
public class Persona {

    private final Image imAlife = new Image(getClass().getResourceAsStream("alife.png"));
    private final Image imDead = new Image(getClass().getResourceAsStream("dead.png"));
    private ImageView currentImv = new ImageView(imAlife);
    private Label posicionLbl;
    private boolean vivo;
    private Integer posicion;

    public Persona(int posicion) {
        this.currentImv.setImage(imAlife);
        currentImv.setScaleX(0.5);
        currentImv.setScaleY(0.5);
        this.vivo = true;
        this.posicion = posicion;
        posicionLbl = new Label(String.valueOf(this.posicion));
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
        this.currentImv.setImage(imDead);
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
    
    public void setXY(int radio, int dregrees){
    
    }

    public void mover(int milliseconds, Persona p) {
        double thisPosX = this.currentImv.xProperty().doubleValue();
        System.out.println(thisPosX);
        double thisPosY = this.currentImv.yProperty().doubleValue();
        System.out.println(thisPosY);
        double tokillPosX = p.getCharacterImage().xProperty().doubleValue();
        System.out.println(tokillPosX);
        double tokillPosY = p.getCharacterImage().yProperty().doubleValue();
        System.out.println(tokillPosX);
        Duration inicio = Duration.ZERO;
        Duration fin = Duration.millis(milliseconds);

        KeyValue kvInicioX = new KeyValue(this.currentImv.translateXProperty(), thisPosX);
        KeyValue kvFinalX = new KeyValue(this.currentImv.translateXProperty(), tokillPosX);
        KeyValue kvInicioY = new KeyValue(this.currentImv.translateYProperty(), thisPosY);
        KeyValue kvFinalY = new KeyValue(this.currentImv.translateYProperty(), tokillPosY);

        KeyFrame kfInicioX = new KeyFrame(inicio, kvInicioX);
        KeyFrame kfFinalX = new KeyFrame(fin, kvFinalX);
        KeyFrame kfInicioY = new KeyFrame(inicio, kvInicioY);
        KeyFrame kfFinalY = new KeyFrame(fin, kvFinalY);

        Timeline lineaTiX = new Timeline(kfInicioX, kfFinalX);
        Timeline lineaTiY = new Timeline(kfInicioY, kfFinalY);

        lineaTiX.play();
        lineaTiY.play();
    }

}
