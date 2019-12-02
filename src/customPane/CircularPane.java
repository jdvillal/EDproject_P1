/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customPane;
/**
 *
 * @author danie
 */
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

//CircularPane: extiende la clase Pane para ser compatible con javafx
public class CircularPane extends Pane {
    private int radius;
    
    public CircularPane(int radius){
        this.radius = radius;
    }
    
    //Se redibuja el Pane cada vez que hacemos algun ajuste
    public void update(){
        layoutChildren();
    }

    //Calculamos en qué punto del circulo debe ir cada nodo agregado al pane,
    @Override
    protected void layoutChildren() {
        final double increment = 360d / getChildren().size();
        double degreese = 0;
        for (Node node : getChildren()) {
            double x = radius * Math.cos(Math.toRadians(degreese)) + getWidth() / 2;
            double y = radius * Math.sin(Math.toRadians(degreese)) + getHeight() / 2;
            layoutInArea(node, x - node.getBoundsInLocal().getWidth() / 2, y - node.getBoundsInLocal().getHeight() / 2, getWidth(), getHeight(), 0.0, HPos.LEFT, VPos.TOP);
            degreese += increment;
        }
    } 
    
}
