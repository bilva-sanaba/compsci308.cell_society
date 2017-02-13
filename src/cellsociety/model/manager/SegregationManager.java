package cellsociety.model.manager;

import cellsociety.model.SegregationModel;
import cellsociety.model.input.SegregationInput;
import util.CAData;

public class SegregationManager extends ModelManager {

    public SegregationManager(CAData data) {
        this(new SegregationModel(data));
    }
    
    private SegregationManager(SegregationModel model) {
        super(model, new SegregationInput(model));
    }
}
