package cellsociety;

import javafx.scene.control.Button;

public class GUI {
    
    private GameEngine engine;
    private Grid grid;
    private Button load, start, pause, step;

    public GUI(GameEngine engine) {
        this.engine = engine;
    }
    
    private void createButtons() {
        load.setOnAction(e -> {
            
        });
    }
}
