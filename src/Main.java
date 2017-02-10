
import cellsociety.GUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    
    

    @Override
    public void start(Stage stage) throws Exception {
        GUI gui = new GUI(stage);
        gui.show();
    }
    
    public static void main(String[] args) {
        launch();
    }

}