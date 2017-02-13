package cellsociety.model.manager;

import cellsociety.model.Model;
import cellsociety.model.input.ModelInput;

/**
 * Constructs a simulation model and its corresponding input
 * @author Mike Liu
 *
 */
public abstract class ModelManager {

    Model myModel;
    ModelInput myInput;
    
    public ModelManager(Model model, ModelInput input) {
        myModel = model;
        myInput = input;
    }
    
    public Model getModel() {
        return myModel;
    }
    
    public ModelInput getInput() {
        return myInput;
    }
}
