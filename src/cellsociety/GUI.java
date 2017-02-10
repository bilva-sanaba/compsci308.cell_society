package cellsociety;

import java.io.File;
import java.util.ResourceBundle;

import cellsociety.handler.LoadHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
    public static final String DATA_FILE_EXTENSION = "*.xml";

    public static final double SCENE_WIDTH = 600;
    public static final double SCENE_HEIGHT = 680;
    public static final double GRID_WIDTH = SCENE_WIDTH;

    private Stage myStage, inputStage;
    private BorderPane myRoot;
    private Button load, play, pause, step, options;
    private Slider speedSlider;
    private Label speedLabel;
    private Controller myController;
    private ResourceBundle myResources;
    private FileChooser myChooser;

    public GUI(Stage stage) {
        myStage = stage;
        inputStage = new Stage();
        inputStage.initOwner(myStage);
        myController = new Controller(GRID_WIDTH);
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + PROPERTIES);
        myRoot = createRoot();
        enableInput(!myController.hasModel());
        myStage.setTitle(TITLE);
        myStage.setScene(createScene());
        myStage.setResizable(false);
        myChooser = makeChooser(DATA_FILE_EXTENSION);
    }
    
    public void show() {
        myStage.show();
    }
    
    private BorderPane createRoot() {
        BorderPane bp = new BorderPane();
        bp.setCenter(myController.getGridView());
        bp.setBottom(initInputPanel());
        return bp;
    }
    
    private Scene createScene() {
        Scene scene = new Scene(myRoot, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets().add(DEFAULT_RESOURCE_PACKAGE + STYLESHEET);
        return scene;
    }

    private Node initInputPanel() {
        initButtons();
        initSpeedChooser();
        HBox inputPanel = new HBox(load, play, pause, step, options, speedLabel, speedSlider);
        inputPanel.setId("input-panel");
        return inputPanel;
    }

    private void initButtons() {
        load = createButton(myResources.getString("LoadButton"), e -> {
            try {
                File dataFile = myChooser.showOpenDialog(myStage);
                if(dataFile != null) {
                    myController.load(dataFile, new LoadHandler() {

                        @Override
                        public void setModelInput(Region root) {
                            root.setId("model-input");
                            Scene scene = new Scene(root);
                            scene.getStylesheets().add(DEFAULT_RESOURCE_PACKAGE + STYLESHEET);
                            inputStage.setScene(scene);
                        }
                    });
                }
            } catch(CAException ce) {
                showError(ce.getMessage());
            }
            enableInput(!myController.hasModel());
        });
        play = createButton(myResources.getString("PlayButton"), e -> myController.play());
        pause = createButton(myResources.getString("PauseButton"), e -> myController.pause());
        step = createButton(myResources.getString("StepButton"), e -> myController.step());
        options = createButton(myResources.getString("OptionButton"), e -> {
            inputStage.show();
            inputStage.setX(myStage.getX() + myStage.getWidth());
        });
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
        options.setDisable(disable);
        speedSlider.setDisable(disable);
    }

    private void initSpeedChooser() {
        speedSlider = createSpeedSlider();
        speedLabel = createSpeedLabel();
        speedSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            speedLabel.setText(String.format(myResources.getString("SpeedLabel"), newValue.intValue()));
            myController.setSpeed(newValue.intValue());
        });
    }
    
    private Label createSpeedLabel() {
        Label label = new Label(String.format(myResources.getString("SpeedLabel"), Controller.DEFAULT_FPS));
        label.setTextFill(Color.WHITE);
        return label;
    }
    
    private Slider createSpeedSlider() {
        Slider slider = new Slider(Controller.MIN_SPEED, Controller.MAX_SPEED, Controller.DEFAULT_FPS);
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
