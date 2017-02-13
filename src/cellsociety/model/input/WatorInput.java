package cellsociety.model.input;

import cellsociety.model.WatorModel;

public class WatorInput extends ModelInput {
    
    WatorModel myModel;

    public WatorInput(WatorModel model) {
        super();
        myModel = model;
        addNumberField("Fish breed period", WatorModel.DEFAULT_FISH_BREED_PERIOD, value -> {
            myModel.updateFishBreedPeriod(value);
        });
        addNumberField("Shark breed period", WatorModel.DEFAULT_SHARK_BREED_PERIOD, value -> {
            myModel.updateSharkBreedPeriod(value);
        });
        addNumberField("Shark initial energy", WatorModel.DEFAULT_SHARK_ENERGY, value -> {
            myModel.updateSharkEnergy(value);
        });
        addNumberField("Energy per fish", WatorModel.DEFAULT_FISH_ENERGY, value -> {
            myModel.setFishEnergy(value);
        });
    }
}
