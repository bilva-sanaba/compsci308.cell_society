package cellsociety.view;

import java.util.HashMap;
import java.util.Map;

import cellsociety.CAException;
import cellsociety.Model;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * Displays the population graph of the simulation
 * @author Mike Liu
 *
 */
public class GraphView {
    
    public static final int DEFAULT_DOMAIN = 50;
    public static final int DEFAULT_RANGE = 2;
    public static final int TICK_UNIT = 5;
    public static final String X_LABEL = "Steps";
    public static final String Y_LABEL = "Number";
    
    private Model myModel;
    private int seq;
    private NumberAxis myXAxis, myYAxis;
    private LineChart<Number, Number> myChart;
    private Map<String, XYChart.Series<Number, Number>> mySeries;
    //private XYChart.Series<Number, Number> dataSeries;
    
    public GraphView() {
        createAxis();
        myChart = new LineChart<Number, Number>(myXAxis, myYAxis);
        mySeries = new HashMap<String, XYChart.Series<Number, Number>>();
    }

    /**
     * Sets the model that is displayed
     * @param model - model to be displayed
     */
    public void setModel(Model model) {
        myModel = model;
        reset();
        update();
    }
    
    /**
     * Returns the line chart representing the population graph
     * @return
     */
    public LineChart<Number, Number> getChart() {
        return myChart;
    }

    /**
     * Updates the display
     */
    public void update() {
        if(myModel == null) {
            throw new CAException(CAException.NO_MODEL);
        }
        addData(myModel.getPopulation());
        moveAxis();
    }
    
    private void createAxis() {
        myXAxis = new NumberAxis(X_LABEL, 0, DEFAULT_DOMAIN, TICK_UNIT);
        myYAxis = new NumberAxis(0, DEFAULT_RANGE, TICK_UNIT);
    }
    
    private void reset() {
        seq = 0;
        mySeries.clear();
        myChart.getData().clear();
    }
    
    private void addData(Map<String, Integer> population) {
        for(String key: population.keySet()) {
            if(!mySeries.containsKey(key)) {
                XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
                series.setName(key);
                mySeries.put(key, series);
                myChart.getData().add(series);
            }
            int value = population.get(key);
            if(value >= myYAxis.getUpperBound()) {
                myYAxis.setUpperBound(value + 1);
            }
            mySeries.get(key).getData().add(new XYChart.Data<Number, Number>(seq, value));
        }
        seq++;
    }
    
    private void moveAxis() {
        if (seq > DEFAULT_DOMAIN) {
            for(XYChart.Series<Number, Number> series: mySeries.values()) {
                series.getData().remove(0);
            }
            myXAxis.setLowerBound(seq - DEFAULT_DOMAIN);
            myXAxis.setUpperBound(seq);
        }
    }
}
