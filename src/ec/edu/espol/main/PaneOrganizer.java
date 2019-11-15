/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.main;

import customPane.CircularPane;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author daniel
 */
class PaneOrganizer {

    private Stage stage = null;
    private int circleSize;
    private GridPane mainPane;

    public PaneOrganizer(Stage st) {
        this.circleSize = 30;
        this.stage = st;
        this.mainPane = new GridPane();
    }

    public void drawControlPane() {
        Label titleLbl = new Label("Parametros de simulación");
        titleLbl.setFont(new Font("Tahoma",30));
        Label circleSizeLbl = new Label("No de soldados");
        TextField circleSizeTF = new TextField();
        Button applySizeBtn = new Button("Apply");
        HBox sizeCtrlPane = new HBox();//HBOX
        sizeCtrlPane.setSpacing(10);
        sizeCtrlPane.getChildren().add(circleSizeLbl);
        sizeCtrlPane.getChildren().add(circleSizeTF);
        sizeCtrlPane.getChildren().add(applySizeBtn);

        Label senseLbl = new Label("Sentido de la ejecucion: ");
        ToggleGroup sentidoTG = new ToggleGroup();
        RadioButton horarioRB = new RadioButton("Horario");
        horarioRB.setToggleGroup(sentidoTG);
        RadioButton antihorarioRB = new RadioButton("Antihorario");
        antihorarioRB.setToggleGroup(sentidoTG);
        HBox senseCtrlPane = new HBox();//HBOX
        senseCtrlPane.setSpacing(10);
        senseCtrlPane.getChildren().add(senseLbl);
        senseCtrlPane.getChildren().add(horarioRB);
        senseCtrlPane.getChildren().add(antihorarioRB);

        Label startIndexLbl = new Label("Iniciar ejecucion en: ");
        TextField startIndexTF = new TextField();
        Button csRotBtn = new Button("-->");
        Button usRotBtn = new Button("<--");

        HBox siCtrlPane = new HBox(); //HBOX
        siCtrlPane.setSpacing(10);
        siCtrlPane.getChildren().add(startIndexLbl);
        siCtrlPane.getChildren().add(startIndexTF);
        siCtrlPane.getChildren().add(usRotBtn);
        siCtrlPane.getChildren().add(csRotBtn);

        Button pauseBtn = new Button("▶ ||");
        Button stopBtn = new Button("■");
        Button startBtn = new Button("Start simulation");
        HBox timeCtrlPane = new HBox(); //HBox
        timeCtrlPane.setSpacing(circleSize);
        timeCtrlPane.getChildren().add(pauseBtn);
        timeCtrlPane.getChildren().add(stopBtn);
        timeCtrlPane.getChildren().add(startBtn);

        VBox controlPane = new VBox();
        //controlPane.setAlignment(Pos.CENTER);
        controlPane.getChildren().add(titleLbl);
        controlPane.getChildren().add(sizeCtrlPane);
        controlPane.getChildren().add(senseCtrlPane);
        controlPane.getChildren().add(siCtrlPane);
        controlPane.getChildren().add(timeCtrlPane);
        controlPane.setSpacing(20);
        

        CircularPane cp = fixCircle(30);

        mainPane.setAlignment(Pos.CENTER);
        mainPane.setHgap(100);
        mainPane.setVgap(40);
        mainPane.add(controlPane, 4, 0);
        mainPane.add(cp, 0, 0);

        Scene sc = new Scene(mainPane, 1200, 700);

        this.stage.setScene(sc);
        this.stage.show();
    }

    private CircularPane fixCircle(int size) {
        CircularPane cp = new CircularPane();
        for (int i = 0; i < size; i++) {
            Button btn = new Button(String.valueOf(i));
            cp.getChildren().add(btn);
        }
        return cp;
    }

}
