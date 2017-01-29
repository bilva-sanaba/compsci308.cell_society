
import cellsociety.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    
    public static final String TITLE = "Cell Simulation";

    @Override
    public void start(Stage stage) throws Exception {
        Controller controller = new Controller();
        stage.setTitle(TITLE);
        stage.setScene(controller.getScene());
        stage.setResizable(false);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch();
    }

}