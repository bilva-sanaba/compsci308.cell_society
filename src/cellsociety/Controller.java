package cellsociety;

import java.io.File;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.util.Duration;
import shapegenerator.SquareGenerator;
import util.CAData;
import util.XMLReader;

/**
 * Regulates the simulation and coordinates model and view
 * @author Mike Liu
 *
 */
public class Controller {
    
    public static final int MIN_SPEED = 1;
    public static final int MAX_SPEED = 20;
    public static final int DEFAULT_SPEED = 10;
    public static final double MILLIS_PER_SECOND = 1000;

    private Timeline animation;
    private Model model;
    private GridView gridView;
    
    public Controller(double gridWidth, double gridHeight) {
        gridView = new GridView(gridWidth, gridHeight);
        animation = getTimeline();
    }
    
    public void load(File dataFile) {
        try {
            CAData data = new XMLReader().readData(dataFile);
            model = Model.getModel(data.getName(), data.numRows(), data.numCols(), data.getCell());
            gridView.setModel(model);
            gridView.setShape(new SquareGenerator());
        } catch(CAException e) {
            model = null;
            throw new CAException(e);
        }
        gridView.update();
    }
    
    public void play() {
        validateModel();
        animation.play();
    }
    
    public void pause() {
        animation.pause();
    }
    
    public void step() {
        validateModel();
        model.update();
        gridView.update();
    }
    
    public void setSpeed(int fps) {
        animation.getKeyFrames().clear();
        animation.getKeyFrames().add(new KeyFrame(Duration.millis(MILLIS_PER_SECOND/fps), e -> step()));
    }
    
    public boolean hasModel() {
        return model != null;
    }
    
    public Parent getGridView() {
        return gridView;
    }
    
    private Timeline getTimeline() {
        Timeline tl = new Timeline();
        tl.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(Duration.millis(MILLIS_PER_SECOND/DEFAULT_SPEED), e -> step());
        tl.getKeyFrames().add(frame);
        return tl;
    }

    private void validateModel() {
        if(!hasModel()) {
            throw new CAException(CAException.NO_MODEL);
        }
    }
}
