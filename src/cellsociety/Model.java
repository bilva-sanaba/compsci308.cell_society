package cellsociety;

import java.util.Collection;
import java.util.Random;

import cell.CellGenerator;

public abstract class Model {
    private Random rand = new Random();
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
    
    public void toRectangle() {
        //TODO
    }
    
    public void toTriangle() {
        //TODO
    }
    
    public void toHexagon() {
        //TODO
    }
    
    protected Cell pickRandomCell(Collection<Cell> cells){
		int i = 0;
		if (cells.size()==0){
			return null;
		}
		int random = rand.nextInt(cells.size());
		for(Cell cell : cells){
			if (i == random){
				return cell;
			}
			i++;
		}
		return null;
	}
}
