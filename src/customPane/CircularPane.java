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

public class CircularPane extends Pane {

    private double degreese;
    private double increment;
    public  void rotate(int a) {
        if (a == 1) {
            this.degreese = this.degreese + increment;

        } else if (a == -1) {
            this.degreese = this.degreese - increment;
        }
        layoutChildren();
    }
    
    public void actualizar(){
        layoutChildren();
    }

    @Override
    protected void layoutChildren() {
        int radius = 200;
        increment = 360 / getChildren().size();
        for (Node node : getChildren()) {
            double x = radius * Math.cos(Math.toRadians(this.degreese)) + getWidth() / 2;
            double y = radius * Math.sin(Math.toRadians(this.degreese)) + getHeight() / 2;
            layoutInArea(node, x - node.getBoundsInLocal().getWidth() / 2, y - node.getBoundsInLocal().getHeight() / 2, getWidth(), getHeight(), 0.0, HPos.LEFT, VPos.TOP);
            degreese += increment;
        }
    }
}
