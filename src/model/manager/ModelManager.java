package model.manager;

import cellsociety.Model;
import model.input.ModelInput;

public abstract class ModelManager {

    Model myModel;
    ModelInput myInput;
    
    public ModelManager(Model model, ModelInput input) {
        myModel = model;
        myInput = input;
    }
    
}
