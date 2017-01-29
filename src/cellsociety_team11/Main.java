package cellsociety_team11;

import cellsociety.GUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    
    public static final String TITLE = "Cell Simulation";

    @Override
    public void start(Stage stage) throws Exception {
        GUI display = new GUI();
        stage.setTitle(TITLE);
        stage.setScene(display.getScene());
        stage.show();
    }
    
    public static void main(String[] args) {
        launch();
    }

}
