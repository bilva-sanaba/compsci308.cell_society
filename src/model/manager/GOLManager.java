package model.manager;

import model.GOLModel;
import model.input.GOLInput;
import util.CAData;

public class GOLManager extends ModelManager {

    public GOLManager(CAData data, double width) {
        this(new GOLModel(data), width);
    }
    
    private GOLManager(GOLModel model, double width) {
        super(model, new GOLInput(model, width));
    }

}
