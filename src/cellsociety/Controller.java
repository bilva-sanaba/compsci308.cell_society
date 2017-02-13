package cellsociety;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import cellsociety.handler.CellClickHandler;
import cellsociety.handler.LoadHandler;
import cellsociety.model.GOLModel;
import cellsociety.model.Model;
import cellsociety.model.SegregationModel;
import cellsociety.model.WatorModel;
import cellsociety.model.manager.GOLManager;
import cellsociety.model.manager.ModelManager;
import cellsociety.model.manager.SegregationManager;
import cellsociety.model.manager.WatorManager;
import cellsociety.view.GraphView;
import cellsociety.view.GridView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.layout.Region;
import javafx.util.Duration;
import util.CAData;
import util.XMLReader;
import util.XMLWriter;

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
    
    /**
     * Loads dataFile and attempts to initialize the simulation
     * @param dataFile
     * @param handler
     */
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
        gridView.setModel(myModel);
        graphView.setModel(myModel);
    }

    /**
     * Starts the animation
     */
    public void play() {
        validateModel();
        animation.play();
    }

    /**
     * Pauses the animation
     */
    public void pause() {
        animation.pause();
    }

    /**
     * Steps through the simulation
     */
    public void step() {
        pause();
        update();
    }
    
    public void save(File dataFile) {
        try {
            new XMLWriter().saveData(myModel, dataFile);
        } catch(CAException e) {
            throw new CAException(e);
        }
    }
    
    /**
     * Zooms in the display
     */
    public void zoomIn() {
        gridView.zoomIn();
    }

    /**
     * Zooms out the display
     */
    public void zoomOut() {
        gridView.zoomOut();
    }
    
    /**
     * Sets the speed of the animation
     * @param fps - frames per second of the animation
     */
    public void setSpeed(int fps) {
        animation.setRate(fps/(double)DEFAULT_FPS);
    }
    
    /**
     * Sets the cell shape of the simulation
     * @param index
     */
    public void setShape(int index) {
        myModel.getGrid().setShape(index);
        gridView.setShape(index);
    }
    
    /**
     * Sets the grid type of the simulation
     * @param type
     */
    public void setGrid(String type) {
        myModel.setGrid(type);
    }
    
    /**
     * Sets the neighbor pattern of the simulation
     * @param type
     */
    public void setNeighborPattern(int type) {
        myModel.getGrid().setNeighborPattern(type);
    }
    
    /**
     * Returns whether this controller has a valid model
     * @return
     */
    public boolean hasModel() {
        return myModel != null;
    }
    
    /**
     * Returns the display of the grid
     * @return
     */
    public Parent getGridView() {
        return gridView;
    }
    
    /**
     * Returns the display of the population of different states
     * @return
     */
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
