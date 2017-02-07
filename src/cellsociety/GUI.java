package cellsociety;

import java.io.File;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

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
    public static final String DATA_FILE_EXTENSION = "*.xml";

    public static final double SCENE_WIDTH = 600;
    public static final double SCENE_HEIGHT = 680;
    public static final double INPUT_PANEL_HEIGHT = 80;

    private Stage myStage;
    private Scene myScene;
    private BorderPane myRoot;
    private Button load, play, pause, step;
    private Slider speedSlider;
    private Label speedLabel;
    private Controller myController;
    private ResourceBundle myResources;
    private FileChooser myChooser;

    public GUI() {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + PROPERTIES);
        myController = new Controller(SCENE_WIDTH);
        myRoot = new BorderPane();
        myRoot.setBottom(initInputPanel(INPUT_PANEL_HEIGHT));
        enableInput(!myController.hasModel());
        myScene = new Scene(myRoot, SCENE_WIDTH, SCENE_HEIGHT);
        myScene.getStylesheets().add(DEFAULT_RESOURCE_PACKAGE + STYLESHEET);
        myChooser = makeChooser(DATA_FILE_EXTENSION);
    }
    
    public void show(Stage stage) {
        myRoot.setCenter(myController.getGridView());
        stage.setTitle(TITLE);
        stage.setScene(myScene);
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
        load = createButton(myResources.getString("LoadButton"), e -> {
            try {
                File dataFile = myChooser.showOpenDialog(myStage);
                if(dataFile != null) {
                    myController.load(dataFile);
                }
            } catch(CAException ce) {
                showError(ce.getMessage());
            }
            enableInput(!myController.hasModel());
        });
        play = createButton(myResources.getString("PlayButton"), e -> myController.play());
        pause = createButton(myResources.getString("PauseButton"), e -> myController.pause());
        step = createButton(myResources.getString("StepButton"), e -> myController.step());
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
            speedLabel.setText(String.format(myResources.getString("SpeedLabel"), (int)speedSlider.getValue()));
            myController.setSpeed((int)speedSlider.getValue());
        });
    }
    
    private Label createSpeedLabel() {
        Label label = new Label(String.format(myResources.getString("SpeedLabel"), Controller.DEFAULT_SPEED));
        label.setTextFill(Color.WHITE);
        return label;
    }
    
    private Slider createSpeedSlider() {
        Slider slider = new Slider(Controller.MIN_SPEED, Controller.MAX_SPEED, Controller.DEFAULT_SPEED);
        slider.setMajorTickUnit(Controller.MAX_SPEED - Controller.MIN_SPEED);
        slider.setMinorTickCount(Controller.MAX_SPEED - Controller.MIN_SPEED - 1);
        slider.setShowTickMarks(true);
        slider.setSnapToTicks(true);
        return slider;
    }

    private FileChooser makeChooser(String extensionAccepted) {
        FileChooser result = new FileChooser();
        result.setTitle("Open Data File");
        result.setInitialDirectory(new File(System.getProperty("user.dir")));
        result.getExtensionFilters().setAll(new ExtensionFilter("Text Files", extensionAccepted));
        return result;
    }
    
    private void showError(String message) {
        Alert a = new Alert(AlertType.ERROR);
        a.setContentText(message);
        a.showAndWait();
    }
}
