package model;

import cell.WatorCell;
import cellsociety.Model;

public class WatorModel extends Model {

    public WatorModel() {
        super(WatorCell.getGenerator());
    }

    private int sharkEnergy = 10;
    
    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

}
