package cellsociety;

import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Regulates the simulation and coordinates model and view
 * @author Mike Liu
 *
 */
public class Controller {
    
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
    public static final String EXCEPTION_PROPERTIES = "exception";
    public static final ResourceBundle EXCEPTION_RESOURCES =
            ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + EXCEPTION_PROPERTIES);

    public static final int FRAMES_PER_SECOND = 5;
    public static final double MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;

    private Timeline animation;
    private Model model;
    private GridView gridView;
    
    public Controller(double gridWidth) {
        gridView = new GridView(gridWidth);
        animation = getTimeline();
    }
    
    public void load() {
        //TODO
    }
    
    public void play() {
        animation.play();
    }
    
    public void pause() {
        animation.pause();
    }
    
    public void step() {
        model.update();
        gridView.update();
    }
    
    public void setSpeed(double speed) {
        animation.getKeyFrames().clear();
        animation.getKeyFrames().add(new KeyFrame(Duration.millis(MILLISECOND_DELAY/speed), e -> step()));
    }
    
    public boolean hasModel() {
        return model != null;
    }
    
    public Pane getGridView() {
        return gridView;
    }
    
    private Timeline getTimeline() {
        Timeline tl = new Timeline();
        tl.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
        tl.getKeyFrames().add(frame);
        return tl;
    }

}
