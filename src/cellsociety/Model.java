package cellsociety;

import java.util.Collection;

import cell.CellConfig;
import cell.CellGenerator;
import model.GOLModel;
import model.SegregationModel;
import model.WatorModel;

public abstract class Model {
    
    private Grid myGrid;
    CellGenerator myGenerator;
    
    public Model(Grid grid) {
        myGrid = grid;
        //TODO create grid based on xml data
    }
    
    public Grid getGrid() {
        return myGrid;
    }
    
    protected void setGrid(Grid grid) {
        this.myGrid = grid;
    }
    
    public abstract void update();
    
    public static Model getModel(String name, int row, int col, Collection<CellConfig> cellConfig) {
        if(name.equals(SegregationModel.NAME)) {
            return new SegregationModel(row, col, cellConfig);
        }
        else if(name.equals(WatorModel.NAME)) {
            return new WatorModel(row, col, cellConfig);
        }
        else if(name.equals(GOLModel.NAME)) {
            return new GOLModel(row, col, cellConfig);
        }
        throw new CAException(CAException.INVALID_MODEL, name);
    }
}
