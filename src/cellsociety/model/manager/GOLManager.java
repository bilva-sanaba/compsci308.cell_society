package cellsociety.model.manager;

import cellsociety.model.GOLModel;
import cellsociety.model.input.GOLInput;
import util.CAData;

public class GOLManager extends ModelManager {

    public GOLManager(CAData data) {
        this(new GOLModel(data));
    }
    
    private GOLManager(GOLModel model) {
        super(model, new GOLInput(model));
    }

}
