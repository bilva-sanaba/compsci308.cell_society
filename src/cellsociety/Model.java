package cellsociety;

import java.util.Collection;

import cell.CellConfig;
import cell.CellGenerator;
import model.GOLModel;
import model.SegregationModel;
import model.WatorModel;
import util.CAData;

public abstract class Model {
    
    private Grid myGrid;
    CellGenerator myGenerator;
    
    public Model(Grid grid) {
        myGrid = grid;
    }
    
    public Grid getGrid() {
        return myGrid;
    }
    
    protected void setGrid(Grid grid) {
        this.myGrid = grid;
    }
    
    public abstract void update();
    
    public static Model getModel(CAData data) {
        String name = data.getName();
        if(name.equals(SegregationModel.NAME)) {
            return new SegregationModel(data);
        }
        else if(name.equals(WatorModel.NAME)) {
            return new WatorModel(data);
        }
        else if(name.equals(GOLModel.NAME)) {
            return new GOLModel(data);
        }
        throw new CAException(CAException.INVALID_MODEL, name);
    }
}
