package cellsociety.model.input;

import cellsociety.model.SegregationModel;
import javafx.scene.Node;
import javafx.scene.control.Slider;

public class SegregationInput extends ModelInput {
    
    SegregationModel myModel;

    public SegregationInput(SegregationModel model) {
        super();
        myModel = model;
        addElement("Happiness threshold", makeHappinessSlider());
    }

    private Node makeHappinessSlider() {
        Slider slider = new Slider(0, 1, SegregationModel.DEFAULT_THRESHOLD);
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            myModel.setThreshold(newValue.doubleValue());
        });
        slider.setMajorTickUnit(.5);
        slider.setMinorTickCount(4);
        slider.setShowTickMarks(true);
        return slider;
    }
}
