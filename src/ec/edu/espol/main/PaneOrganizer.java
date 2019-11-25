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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    //private CircularPane currentCircle;
    private Circulo circle;

    public PaneOrganizer(Stage st) {
        this.circleSize = 31;
        this.stage = st;
        this.mainPane = new GridPane();
    }

    public void drawControlPane() {
        Label titleLbl = new Label("Parametros de simulación");
        titleLbl.setFont(new Font("Tahoma", 30));
        Label circleSizeLbl = new Label("No de soldados");
        TextField circleSizeTF = new TextField();
        circleSizeTF.setMaxWidth(40);
        Button applySizeBtn = new Button("Apply");
        EventHandler apply = (EventHandler) (Event event) -> {
            updatePane(Integer.valueOf(circleSizeTF.getText()));
        };
        applySizeBtn.setOnMouseClicked(apply);
        HBox sizeCtrlPane = new HBox();//HBOX
        sizeCtrlPane.setSpacing(10);
        sizeCtrlPane.getChildren().add(circleSizeLbl);
        sizeCtrlPane.getChildren().add(circleSizeTF);
        sizeCtrlPane.getChildren().add(applySizeBtn);
        //sizeCtrlPane.setAlignment(Pos.CENTER_RIGHT);

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
        //senseCtrlPane.setAlignment(Pos.CENTER_RIGHT);

        Label startIndexLbl = new Label("Iniciar ejecucion en: ");
        TextField startIndexTF = new TextField();
        startIndexTF.setMaxWidth(40);
        Button csRotBtn = new Button("->");
        Button usRotBtn = new Button("<-");

        HBox siCtrlPane = new HBox(); //HBOX
        siCtrlPane.setSpacing(10);
        siCtrlPane.getChildren().add(startIndexLbl);
        siCtrlPane.getChildren().add(startIndexTF);
        siCtrlPane.getChildren().add(usRotBtn);
        siCtrlPane.getChildren().add(csRotBtn);
        //siCtrlPane.setAlignment(Pos.CENTER_RIGHT);

        Button pauseBtn = new Button("▶ ||");
        Button stopBtn = new Button("■");
        Button startBtn = new Button("Start");
        EventHandler startEv = (EventHandler) (Event event) -> {
            System.out.println("Start pressed");
            try {
                this.circle.start();
                
            } catch (Exception e) {
            }
        };
        startBtn.setOnMouseClicked(startEv);
        HBox timeCtrlPane = new HBox(); //HBox
        timeCtrlPane.setSpacing(10);
        //timeCtrlPane.setAlignment(Pos.CENTER_RIGHT);
        timeCtrlPane.getChildren().add(pauseBtn);
        timeCtrlPane.getChildren().add(stopBtn);
        timeCtrlPane.getChildren().add(startBtn);

        VBox controlPane = new VBox();
        controlPane.setAlignment(Pos.CENTER);
        controlPane.getChildren().add(titleLbl);
        controlPane.getChildren().add(sizeCtrlPane);
        controlPane.getChildren().add(senseCtrlPane);
        controlPane.getChildren().add(siCtrlPane);
        controlPane.getChildren().add(timeCtrlPane);
        controlPane.setSpacing(20);

        mainPane.setAlignment(Pos.CENTER);
        mainPane.setHgap(100);
        mainPane.setVgap(40);
        mainPane.add(controlPane, 4, 0);
        updatePane(30);

        Scene sc = new Scene(mainPane, 1500, 900);

        this.stage.setScene(sc);
        this.stage.show();
    }

    private void updatePane(int newSize) {
        if (this.circle != null) {
            this.mainPane.getChildren().removeAll(circle.getCharactersPane());
            this.mainPane.getChildren().removeAll(circle.getNumerosPane());
        }
        updateCircle(newSize);
        this.mainPane.add(this.circle.getCharactersPane(), 0, 0);
        this.mainPane.add(this.circle.getNumerosPane(), 0, 0);
    }

    private void updateCircle(int newSize) {
        this.circle = new Circulo(newSize);
    }

}
