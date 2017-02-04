package model;

import cellsociety.Model;
import cellsociety.cellgenerator.WatorCellGenerator;

public class WatorModel extends Model {

    public WatorModel() {
        super(new WatorCellGenerator());
    }

    private int sharkEnergy = 10;
    
    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

}
