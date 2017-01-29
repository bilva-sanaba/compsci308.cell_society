package cellsociety;

import java.awt.Dimension;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class GUI {
    
    public static final Dimension SCENE_SIZE = new Dimension(800, 600);
    public static final double INPUT_PANEL_SPACING = 80;
    
    private GameEngine engine;
    private Scene scene;
    private Button load, play, pause, step;

    public GUI() {
        BorderPane root = new BorderPane();
        root.setBottom(initInputPanel());
        scene = new Scene(root, SCENE_SIZE.width, SCENE_SIZE.height);
    }
    
    public Scene getScene() {
        return scene;
    }
    
    private Node initInputPanel() {
        initButtons();
        enableButtons();
        HBox inputPanel = new HBox(INPUT_PANEL_SPACING, load, play, pause, step);
        inputPanel.setStyle("-fx-padding: 30;" + "-fx-border-color: grey;" + "-fx-alignment: center;");
        return inputPanel;
    }

    private void initButtons() {
        load = createButton("Load", e -> {
            
        });
        play = createButton("Play", e -> {
            engine.play();
        });
        pause = createButton("Pause", e -> {
            engine.pause();
        });
        step = createButton("Step", e -> {
            engine.step();
        });
    }
    
    private Button createButton(String label, EventHandler<ActionEvent> e) {
        Button b = new Button();
        b.setText(label);
        b.setOnAction(e);
        return b;
    }

    private void enableButtons() {
        play.setDisable(engine == null);
        pause.setDisable(engine == null);
        step.setDisable(engine == null);
    }
}
