/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.main;

import customPane.CircularPane;
import javafx.application.Application;

import ec.edu.espol.List.List;
import ec.edu.espol.SimplyLinkedList.LinkedList;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Jorge Villalta
 */
public class EDproject_P1 extends Application {

    @Override
    public void start(Stage primaryStage) {
        

        PaneOrganizer pn = new PaneOrganizer(primaryStage);
        pn.drawControlPane();
        primaryStage.setTitle("Simulador");

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);

    }


}
