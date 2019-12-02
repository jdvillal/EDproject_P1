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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *Clase en la que se implementa la interfaz grafica y se gestionan los eventos
 * @author daniel
 */
class PaneOrganizer {

    private Stage stage = null;
    private int circleSize;//registra el tamaño(cantidad de personas) del circulo actual
    private final GridPane mainPane;//Pane principal de la escena
    private Circulo circle;//Circulo a mostrarse en pantalla

    public PaneOrganizer(Stage st) {
        this.circleSize = 30;
        this.stage = st;
        this.mainPane = new GridPane();
    }

    //Dibuja toda la interfaz principal del programa
    public void drawControlPane() {
        Label titleLbl = new Label("Parametros de simulación");
        titleLbl.setFont(new Font("Tahoma", 30));
        //Nodos para configurar la contidad de personas en la simulacion
        Label circleSizeLbl = new Label("No de personas");
        TextField circleSizeTF = new TextField();
        EventHandler<KeyEvent> evNotCharIn = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                //Valida la entrada de caracteres numericos en un TextField
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
                updateCirclePane(this.circleSize);
            } else {
                if (Integer.valueOf(circleSizeTF.getText()) < 2) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Se requiere un mínimo de dos personajes para iniciar la simulacion!");
                    alert.showAndWait();
                } else {
                    updateCirclePane(Integer.valueOf(circleSizeTF.getText()));
                }
            }
        };
        applySizeBtn.setOnMouseClicked(apply);
        HBox sizeCtrlPane = new HBox();//HBOX
        sizeCtrlPane.setSpacing(10);
        sizeCtrlPane.getChildren().add(circleSizeLbl);
        sizeCtrlPane.getChildren().add(circleSizeTF);
        sizeCtrlPane.getChildren().add(applySizeBtn);

        //Nodos para configurar el sentido de la simulación
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

        //Nodos para definir en qué posicion se inicia la simulación
        Label startIndexLbl = new Label("Iniciar ejecucion en: ");
        TextField startIndexTF = new TextField();
        startIndexTF.setOnKeyTyped(evNotCharIn);
        startIndexTF.setMaxWidth(40);
        HBox siCtrlPane = new HBox(); //HBOX
        siCtrlPane.setSpacing(10);
        siCtrlPane.getChildren().add(startIndexLbl);
        siCtrlPane.getChildren().add(startIndexTF);

        //Nodos para controlar el tiempo en la simulacion
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
            }
            updateCirclePane(this.circleSize);
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
        
        //Nodos para controlar la velocidad de la simulacion
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
        timeCtrlPane.getChildren().add(pauseBtn);
        timeCtrlPane.getChildren().add(stopBtn);
        timeCtrlPane.getChildren().add(startBtn);
        timeCtrlPane.getChildren().add(velocidadLbl);
        timeCtrlPane.getChildren().add(decreaseSpeedBtn);
        timeCtrlPane.getChildren().add(increaseSpeedBtn);

        //Boton para personalizar las imagenes que representan a las personas en la simulacion
        Button personalizarBtn = new Button("Personalizar");
        personalizarBtn.setOnMouseClicked((Event event) -> {
            if(this.circle.isRunnig()){
                event.consume();
            }else{
                personalizarStage();
            }
            
            
        });

        //Se agregan los pane anteriores al pane general de los controles de la simulacion
        VBox controlPane = new VBox();
        controlPane.setAlignment(Pos.CENTER);
        controlPane.getChildren().add(titleLbl);
        controlPane.getChildren().add(sizeCtrlPane);
        controlPane.getChildren().add(senseCtrlPane);
        controlPane.getChildren().add(siCtrlPane);
        controlPane.getChildren().add(timeCtrlPane);
        controlPane.getChildren().add(personalizarBtn);
        controlPane.setSpacing(20);

        
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setHgap(100);
        mainPane.setVgap(40);
        mainPane.add(controlPane, 4, 0);
        controlPane.setTranslateX(controlPane.getTranslateX() + 100);
        updateCirclePane(this.circleSize);
        Scene sc = new Scene(mainPane, 1500, 900);
        this.stage.setOnCloseRequest((WindowEvent event) -> {
            if (this.circle.isRunnig()) {
                this.circle.stop();
            }
        });
        this.stage.setScene(sc);
        this.stage.show();
    }

    //Actualiza la interfaz circular con el tamaño especificado
    private void updateCirclePane(int newSize) {
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

    //Actualiza el circulo creando una nueva instancia con el tamaño especificado
    private void updateCircle(int newSize) {
        this.circle = new Circulo(newSize);
    }

    //Crea una ventana llamada cuando el usuario desee configurar las imagenes de la simulacion
    private void personalizarStage() {
        GridPane gp = new GridPane();

        Label personLbl = new Label("Personaje");
        ImageView imHappy = new ImageView(new Image(getClass().getResourceAsStream("happy.png")));
        imHappy.setScaleX(0.5);
        imHappy.setScaleY(0.5);
        RadioButton happyRB = new RadioButton("Happy Guy");
        happyRB.setGraphic(imHappy);
        ImageView imSmile = new ImageView(new Image(getClass().getResourceAsStream("smile.png")));
        imSmile.setScaleX(0.5);
        imSmile.setScaleY(0.5);
        RadioButton smileRB = new RadioButton("Smile Guy");
        smileRB.setGraphic(imSmile);
        ToggleGroup tgPerson = new ToggleGroup();
        happyRB.setToggleGroup(tgPerson);
        smileRB.setToggleGroup(tgPerson);

        Label asesinoLbl = new Label("Asesino");
        ImageView imJason = new ImageView(new Image(getClass().getResourceAsStream("jason.png")));
        imJason.setScaleX(0.5);
        imJason.setScaleY(0.5);
        RadioButton jasonRB = new RadioButton("Jason");
        jasonRB.setGraphic(imJason);
        ImageView imMuerte = new ImageView(new Image(getClass().getResourceAsStream("muerte.png")));
        imMuerte.setScaleX(0.5);
        imMuerte.setScaleY(0.5);
        RadioButton muerteRB = new RadioButton("La muerte");
        muerteRB.setGraphic(imMuerte);
        ImageView imNinja = new ImageView(new Image(getClass().getResourceAsStream("ninja.png")));
        imNinja.setScaleX(0.5);
        imNinja.setScaleY(0.5);
        RadioButton ninjaRB = new RadioButton("Ninja");
        ninjaRB.setGraphic(imNinja);
        ToggleGroup tgAssasing = new ToggleGroup();
        jasonRB.setToggleGroup(tgAssasing);
        muerteRB.setToggleGroup(tgAssasing);
        ninjaRB.setToggleGroup(tgAssasing);

        Label muerteLbl = new Label("Indicador de muerte");
        ImageView imLapida = new ImageView(new Image(getClass().getResourceAsStream("lapida.png")));
        imLapida.setScaleX(0.5);
        imLapida.setScaleY(0.5);
        RadioButton lapidaRB = new RadioButton("Lápida");
        lapidaRB.setGraphic(imLapida);
        ImageView imTiza = new ImageView(new Image(getClass().getResourceAsStream("tiza.png")));
        imTiza.setScaleX(0.5);
        imTiza.setScaleY(0.5);
        RadioButton tizaRB = new RadioButton("Marca Tiza");
        tizaRB.setGraphic(imTiza);
        ImageView imSkull = new ImageView(new Image(getClass().getResourceAsStream("skull.png")));
        imSkull.setScaleX(0.5);
        imSkull.setScaleY(0.5);
        RadioButton skullRB = new RadioButton("Calabera");
        skullRB.setGraphic(imSkull);
        ImageView imFantasma = new ImageView(new Image(getClass().getResourceAsStream("fantasma.png")));
        imFantasma.setScaleX(0.5);
        imFantasma.setScaleY(0.5);
        RadioButton fantasmaRB = new RadioButton("Fantasma");
        fantasmaRB.setGraphic(imFantasma);
        ToggleGroup tgMuerto = new ToggleGroup();
        lapidaRB.setToggleGroup(tgMuerto);
        tizaRB.setToggleGroup(tgMuerto);
        skullRB.setToggleGroup(tgMuerto);
        fantasmaRB.setToggleGroup(tgMuerto);

        Button applyBtn = new Button("Apply");
        

        gp.add(personLbl, 0, 0);
        gp.add(happyRB, 0, 1);
        gp.add(smileRB, 1, 1);

        gp.add(asesinoLbl, 0, 2);
        gp.add(jasonRB, 0, 3);
        gp.add(muerteRB, 1, 3);
        gp.add(ninjaRB, 2, 3);

        gp.add(muerteLbl, 0, 4);
        gp.add(lapidaRB, 0, 5);
        gp.add(tizaRB, 1, 5);
        gp.add(skullRB, 2, 5);
        gp.add(fantasmaRB, 3, 5);

        gp.add(applyBtn, 2, 7);

        gp.setAlignment(Pos.CENTER);
        gp.setHgap(30);
        gp.setVgap(30);
        Scene sc = new Scene(gp, 900, 600);
        Stage stg = new Stage();
        stg.setScene(sc);
        stg.show();
        
        applyBtn.setOnMouseClicked((Event event) -> {
            String person = null;
            String asesino = null;
            String muerto = null;
            if (happyRB.isSelected()) {
                person = "happy";
                System.out.println("happy");
            } else if (smileRB.isSelected()) {
                person = "smile";
                System.out.println("smile");
            }

            if (jasonRB.isSelected()) {
                asesino = "jason";
                System.out.println("jason");
            } else if (muerteRB.isSelected()) {
                asesino = "muerte";
                System.out.println("muerte");
            } else if (ninjaRB.isSelected()) {
                asesino = "ninja";
                System.out.println("ninja");
            }

            if (lapidaRB.isSelected()) {
                muerto = "lapida";
                System.out.println("lapida");
            } else if (tizaRB.isSelected()) {
                muerto = "tiza";
                System.out.println("tiza");
            } else if (skullRB.isSelected()) {
                muerto = "skull";
                System.out.println("skull");
            } else if (fantasmaRB.isSelected()) {
                muerto = "fantasma";
                System.out.println("fantasma");
            }
            this.circle.updateGraphics(person, asesino, muerto);
            stg.close();
        });
    }

}
