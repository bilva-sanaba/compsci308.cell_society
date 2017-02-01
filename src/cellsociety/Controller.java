package cellsociety;

import java.awt.Dimension;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

/**
 * Master class that sets up the scene, interacts with user and coordinates the simulation
 * @author Mike Liu
 *
 */
public class Controller {
    
    public static final Dimension SCENE_SIZE = new Dimension(800, 600);
    public static final double INPUT_PANEL_SPACING = 50;
    public static final int FRAMES_PER_SECOND = 1;
    public static final double MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;

    private Scene scene;
    private Timeline animation;
    private Button load, play, pause, step;
    private Slider speedSlider;
    private Label speedLabel;
    private Model model;
    private View view;

    public Controller() {
        BorderPane root = new BorderPane();
        view = new View();
        root.setCenter(view);
        root.setBottom(initInputPanel());
        scene = new Scene(root, SCENE_SIZE.width, SCENE_SIZE.height);
        animation = getTimeline();
    }
    
    public Scene getScene() {
        return scene;
    }
    
    private Timeline getTimeline() {
        Timeline tl = new Timeline();
        tl.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
        tl.getKeyFrames().add(frame);
        return tl;
    }
    
    private void step() {
        model.step();
        view.update();
    }

    private Node initInputPanel() {
        initButtons();
        initSpeedChooser();
        enableInput(model == null);
        HBox inputPanel = new HBox(INPUT_PANEL_SPACING, load, play, pause, step, speedLabel, speedSlider);
        inputPanel.setStyle("-fx-padding: 30;" + "-fx-background-color: black;" + "-fx-alignment: center;");
        return inputPanel;
    }

    private void initButtons() {
        load = createButton("Load", e -> handleLoad());
        play = createButton("Play", e -> {
            animation.play();
        });
        pause = createButton("Pause", e -> {
            animation.pause();
        });
        step = createButton("Step", e -> {
            step();
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
        speedSlider.setDisable(disable);
    }

    private void initSpeedChooser() {
        speedLabel = createSpeedLabel();
        speedSlider = createSpeedSlider();
        speedSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double speed = getSpeed(speedSlider);
            speedLabel.setText(String.format("Speed: %.2fx", speed));
            animation.getKeyFrames().clear();
            animation.getKeyFrames().add(new KeyFrame(Duration.millis(MILLISECOND_DELAY/speed), e -> step()));
        });
    }
    
    private Label createSpeedLabel() {
        Label label = new Label("Speed: 1.00x");
        label.setTextFill(Color.WHITE);
        return label;
    }
    
    private Slider createSpeedSlider() {
        Slider slider = new Slider(-2, 2, 0);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(3);
        return slider;
    }
    
    private void handleLoad() {
        // TODO Auto-generated method stub
    }

    private double getSpeed(Slider slider) {
        return Math.pow(2, slider.getValue());
    }
}
