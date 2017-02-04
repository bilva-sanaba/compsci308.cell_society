package cellsociety;

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
        //Add exception handling
    }
    
    public void play() {
        if(!hasModel()) {
            throw new CAException(CAException.NO_MODEL);
        }
        animation.play();
    }
    
    public void pause() {
        animation.pause();
    }
    
    public void step() {
        if(!hasModel()) {
            throw new CAException(CAException.NO_MODEL);
        }
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
