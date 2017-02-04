package cellsociety;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * User interface that sets up the display and lets user interact with the simulation
 * @author Mike Liu
 *
 */
public class GUI {
    
    public static final String TITLE = "Cell Automata";
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
    public static final String STYLESHEET = "default.css";
    public static final String PROPERTIES = "default";

    public static final double SCENE_WIDTH = 800;
    public static final double SCENE_HEIGHT = 600;
    public static final double INPUT_PANEL_HEIGHT = 80;

    private Scene scene;
    private BorderPane root;
    private Button load, play, pause, step;
    private Slider speedSlider;
    private Label speedLabel;
    private Controller controller;
    private ResourceBundle resources;

    public GUI() {
        resources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + PROPERTIES);
        controller = new Controller(SCENE_WIDTH);
        root = new BorderPane();
        root.setBottom(initInputPanel(INPUT_PANEL_HEIGHT));
        enableInput(!controller.hasModel());
        scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets().add(DEFAULT_RESOURCE_PACKAGE + STYLESHEET);
    }
    
    public void show(Stage stage) {
        root.setCenter(controller.getGridView());
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private Node initInputPanel(double height) {
        initButtons();
        initSpeedChooser();
        HBox inputPanel = new HBox(load, play, pause, step, speedLabel, speedSlider);
        inputPanel.setId("input-panel");
        inputPanel.setPrefHeight(height);
        return inputPanel;
    }

    private void initButtons() {
        load = createButton(resources.getString("LoadButton"), e -> controller.load());
        play = createButton(resources.getString("PlayButton"), e -> controller.play());
        pause = createButton(resources.getString("PauseButton"), e -> controller.pause());
        step = createButton(resources.getString("StepButton"), e -> controller.step());
    }

    private Button createButton(String label, EventHandler<ActionEvent> e) {
        Button b = new Button();
        b.setText(label);
        b.setOnAction(e);
        return b;
    }

    private void enableInput(boolean disable) {
        play.setDisable(disable);
        pause.setDisable(disable);
        step.setDisable(disable);
        speedSlider.setDisable(disable);
    }

    private void initSpeedChooser() {
        speedSlider = createSpeedSlider();
        speedLabel = createSpeedLabel();
        speedSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            speedLabel.setText(getSpeedText());
            controller.setSpeed(getSpeed(speedSlider));
        });
    }
    
    private Label createSpeedLabel() {
        Label label = new Label(getSpeedText());
        label.setTextFill(Color.WHITE);
        return label;
    }
    
    private String getSpeedText() {
        return String.format(resources.getString("SpeedLabel"), getSpeed(speedSlider));
    }
    
    private Slider createSpeedSlider() {
        Slider slider = new Slider(-2, 2, 0);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(3);
        return slider;
    }

    private double getSpeed(Slider slider) {
        return Math.pow(2, slider.getValue());
    }
}
