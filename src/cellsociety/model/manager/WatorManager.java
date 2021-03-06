package cellsociety.model.manager;

import cellsociety.model.WatorModel;
import cellsociety.model.input.WatorInput;
import util.CAData;

public class WatorManager extends ModelManager {

    public WatorManager(CAData data) {
        this(new WatorModel(data));
    }
    
    private WatorManager(WatorModel model) {
        super(model, new WatorInput(model));
    }
}
