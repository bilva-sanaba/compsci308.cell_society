package model.input;

import model.GOLModel;

public class GOLInput extends ModelInput {
    
    GOLModel myModel;

    public GOLInput(GOLModel model, double width) {
        super(width);
        myModel = model;
    }

}
