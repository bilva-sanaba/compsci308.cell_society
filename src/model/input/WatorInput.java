package model.input;

import model.WatorModel;

public class WatorInput extends ModelInput {
    
    WatorModel myModel;

    public WatorInput(WatorModel model) {
        super();
        myModel = model;
        addNumberField("Fish breed period", WatorModel.DEFAULT_FISH_BREED_PERIOD, v -> {
            //TODO
        });
        addNumberField("Shark breed period", WatorModel.DEFAULT_SHARK_BREED_PERIOD, v -> {
            
        });
        addNumberField("Shark initial energy", WatorModel.DEFAULT_SHARK_ENERGY, v -> {
            
        });
        addNumberField("Energy per fish", WatorModel.DEFAULT_FISH_ENERGY, v -> {
            
        });
    }
}
