package model.manager;

import model.GOLModel;
import model.input.GOLInput;
import util.CAData;

public class GOLManager extends ModelManager {

    public GOLManager(CAData data) {
        this(new GOLModel(data));
    }
    
    private GOLManager(GOLModel model) {
        super(model, new GOLInput(model));
    }

}
