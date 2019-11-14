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
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Jorge Villalta
 */
public class EDproject_P1 extends Application {

    @Override
    public void start(Stage primaryStage) {

        CircularPane cp = new CircularPane();

        for (int i = 0; i < 30; i++) {
            Button btn = new Button();
            btn.setText(Integer.toString(i));
            cp.getChildren().add(btn);
        }


        Scene scene = new Scene(cp, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);

    }

}
