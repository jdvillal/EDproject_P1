/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.main;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
        this.circleSize = 30;
        this.stage = st;
        this.mainPane = new GridPane();
    }

    public void drawControlPane() {
        Label titleLbl = new Label("Parametros de simulación");
        titleLbl.setFont(new Font("Tahoma", 30));
        Label circleSizeLbl = new Label("No de personas");
        TextField circleSizeTF = new TextField();
        EventHandler<KeyEvent> evNotCharIn = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".contains(event.getCharacter())) {
                    event.consume();
                }
            }
        };
        circleSizeTF.setOnKeyTyped(evNotCharIn);
        circleSizeTF.setMaxWidth(40);
        Button applySizeBtn = new Button("Apply | Refresh");
        EventHandler apply = (EventHandler) (Event event) -> {
            if (this.circle.isRunnig()) {
                event.consume();
            }
            if ("".equals(circleSizeTF.getText())) {
                updatePane(this.circleSize);
            } else {
                if (Integer.valueOf(circleSizeTF.getText()) < 2) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Se requiere un mínimo de dos personajes para iniciar la simulacion!");
                    alert.showAndWait();
                } else {
                    updatePane(Integer.valueOf(circleSizeTF.getText()));
                }
            }
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
        startIndexTF.setOnKeyTyped(evNotCharIn);
        startIndexTF.setMaxWidth(40);

        HBox siCtrlPane = new HBox(); //HBOX
        siCtrlPane.setSpacing(10);
        siCtrlPane.getChildren().add(startIndexLbl);
        siCtrlPane.getChildren().add(startIndexTF);
        //siCtrlPane.setAlignment(Pos.CENTER_RIGHT);

        Button pauseBtn = new Button("||");
        EventHandler pauseEV = (EventHandler) (Event ev) -> {
            if (this.circle.isRunnig()) {
                if (this.circle.isPaused()) {
                    this.circle.resum();
                    pauseBtn.setText("||");
                } else {
                    try {
                        this.circle.pause();
                        pauseBtn.setText("▶");
                    } catch (Exception e) {
                    }
                }
            }
        };
        pauseBtn.setOnMouseClicked(pauseEV);
        Button stopBtn = new Button("■");
        EventHandler stopEV = (EventHandler) (Event ev) -> {
            if (this.circle.isRunnig()) {
                this.circle.interrupt();
                updatePane(this.circleSize);
            }
        };
        stopBtn.setOnMouseClicked(stopEV);
        Button startBtn = new Button("Start");
        EventHandler startEv = (EventHandler) (Event event) -> {
            if ("".equals(startIndexTF.getText())) {
                this.circle.setStartKiller(1);
            } else {
                this.circle.setStartKiller(Integer.valueOf(startIndexTF.getText()));
            }
            if (horarioRB.isSelected()) {
                this.circle.setKillSense(1);
            } else if (antihorarioRB.isSelected()) {
                this.circle.setKillSense(-1);
            }
            System.out.println("Start pressed");
            try {
                this.circle.start();

            } catch (Exception e) {
            }
        };
        startBtn.setOnMouseClicked(startEv);
        Button increaseSpeedBtn = new Button("+");
        EventHandler increaseSpeedEV = (EventHandler) (Event event) -> {
            this.circle.decreaseRunTime();
        };
        increaseSpeedBtn.setOnMouseClicked(increaseSpeedEV);
        Button decreaseSpeedBtn = new Button("-");
        EventHandler decreaseSpeedEV = (EventHandler) (Event event) -> {
            this.circle.increasteRunTime();
        };
        decreaseSpeedBtn.setOnMouseClicked(decreaseSpeedEV);
        Label velocidadLbl = new Label("Control de velocidad:");
        HBox timeCtrlPane = new HBox(); //HBox
        timeCtrlPane.setSpacing(10);
        //timeCtrlPane.setAlignment(Pos.CENTER_RIGHT);
        timeCtrlPane.getChildren().add(pauseBtn);
        timeCtrlPane.getChildren().add(stopBtn);
        timeCtrlPane.getChildren().add(startBtn);
        timeCtrlPane.getChildren().add(velocidadLbl);
        timeCtrlPane.getChildren().add(decreaseSpeedBtn);
        timeCtrlPane.getChildren().add(increaseSpeedBtn);

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
        controlPane.setTranslateX(controlPane.getTranslateX() + 100);
        updatePane(this.circleSize);
        Scene sc = new Scene(mainPane, 1500, 900);
        this.stage.setOnCloseRequest((WindowEvent event) -> {
            if (this.circle.isRunnig()) {
                this.circle.stop();
            }
        });
        this.stage.setScene(sc);
        this.stage.show();
    }

    private void updatePane(int newSize) {
        this.circleSize = newSize;
        if (this.circle != null) {
            this.mainPane.getChildren().removeAll(circle.getCharactersPane());
            this.mainPane.getChildren().removeAll(circle.getNumerosPane());
        }
        updateCircle(this.circleSize);
        this.mainPane.add(this.circle.getCharactersPane(), 0, 0);
        this.circle.getCharactersPane().setTranslateX(this.circle.getCharactersPane().getTranslateX() + 100);
        this.mainPane.add(this.circle.getNumerosPane(), 0, 0);
        this.circle.getNumerosPane().setTranslateX(this.circle.getNumerosPane().getTranslateX() + 100);
    }

    private void updateCircle(int newSize) {
        this.circle = new Circulo(newSize);
    }

}
