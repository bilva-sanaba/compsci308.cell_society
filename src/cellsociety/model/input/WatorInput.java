package cellsociety.model.input;

import cellsociety.model.WatorModel;
import cellsociety.model.WatorModelParameters;

public class WatorInput extends ModelInput {
    
    WatorModel myModel;

    public WatorInput(WatorModel model) {
        super();
        myModel = model;
        addNumberField("Fish breed period", WatorModelParameters.DEFAULT_FISH_BREED_PERIOD, value -> {
            myModel.updateFishBreedPeriod(value);
        });
        addNumberField("Shark breed period", WatorModelParameters.DEFAULT_SHARK_BREED_PERIOD, value -> {
            myModel.updateSharkBreedPeriod(value);
        });
        addNumberField("Shark initial energy", WatorModelParameters.DEFAULT_SHARK_ENERGY, value -> {
            myModel.updateSharkEnergy(value);
        });
        addNumberField("Energy per fish", WatorModelParameters.DEFAULT_FISH_ENERGY, value -> {
            myModel.getParameters().setFishEnergy(value);
        });
    }
}
