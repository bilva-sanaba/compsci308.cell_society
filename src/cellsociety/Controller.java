package cellsociety;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import cellsociety.handler.CellClickHandler;
import cellsociety.handler.LoadHandler;
import cellsociety.view.GraphView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.layout.Region;
import javafx.util.Duration;
import model.GOLModel;
import model.SegregationModel;
import model.WatorModel;
import model.manager.GOLManager;
import model.manager.ModelManager;
import model.manager.SegregationManager;
import model.manager.WatorManager;
import util.CAData;
import util.XMLReader;

/**
 * Regulates the simulation and coordinates model and view
 * @author Mike Liu
 *
 */
public class Controller {
    
    public static final List<String> SHAPE_CHOICES = Arrays.asList(
            "Square",
            "Triangle",
            "Hexagon");
    
    public static final int MIN_SPEED = 1;
    public static final int MAX_SPEED = 20;
    public static final int DEFAULT_FPS = 10;
    public static final double MILLIS_PER_SECOND = 1000;

    private Timeline animation;
    private Model myModel;
    private GridView gridView;
    private GraphView graphView;
    
    public Controller(double gridWidth) {
        gridView = new GridView(gridWidth, new CellClickHandler() {

            @Override
            public void onClicked(int row, int col) {
                myModel.click(row, col);
                gridView.update();
            }
        });
        graphView = new GraphView();
        animation = getTimeline();
    }
    
    public void load(File dataFile, LoadHandler handler) {
        animation.stop();
        try {
            CAData data = new XMLReader().readData(dataFile);
            ModelManager manager = chooseModel(data);
            myModel = manager.getModel();
            handler.setModelInput(manager.getInput().getRoot());
        } catch(CAException e) {
            myModel = null;
            throw new CAException(e);
        }
        handler.resetChoices();
        gridView.setModel(myModel);
        graphView.setModel(myModel);
    }

    public void play() {
        validateModel();
        animation.play();
    }
    
    public void pause() {
        animation.pause();
    }
    
    public void step() {
        pause();
        update();
    }
    
    public void setSpeed(int fps) {
        animation.setRate(fps/(double)DEFAULT_FPS);
    }
    
    public void setShape(int index) {
        myModel.getGrid().setShape(index);
        gridView.setShape(index);
    }
    
    public void setGrid(String type) {
        myModel.setGrid(type);
    }
    
    public void setNeighborPattern(String type) {
        myModel.getGrid().setNeighborPattern(type);
    }
    
    public boolean hasModel() {
        return myModel != null;
    }
    
    public Parent getGridView() {
        return gridView;
    }
    
    public Region getGraphView() {
        return graphView.getChart();
    }
    
    private void update() {
        validateModel();
        myModel.update();
        gridView.update();
        graphView.update();
    }
    
    private Timeline getTimeline() {
        Timeline tl = new Timeline();
        tl.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(Duration.millis(MILLIS_PER_SECOND/DEFAULT_FPS), e -> update());
        tl.getKeyFrames().add(frame);
        return tl;
    }
    
    private ModelManager chooseModel(CAData data) {
        String name = data.getName();
        if(name.equals(SegregationModel.NAME)) {
            return new SegregationManager(data);
        }
        else if(name.equals(WatorModel.NAME)) {
            return new WatorManager(data);
        }
        else if(name.equals(GOLModel.NAME)) {
            return new GOLManager(data);
        }
        throw new CAException(CAException.INVALID_MODEL, name);
    }

    private void validateModel() {
        if(!hasModel()) {
            throw new CAException(CAException.NO_MODEL);
        }
    }
}
