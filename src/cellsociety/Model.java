package cellsociety;

import java.util.Collection;
import java.util.Random;

import cell.Cell;

public abstract class Model {
    private Random rand = new Random();
    private Grid myGrid;
    
    public Model(Grid grid, boolean diagonal) {
        myGrid = grid;
        myGrid.buildNeighborGraph(diagonal);
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
    
    /**
     * Picks a random cell from the list and removes it from the list
     * Modifier
     * @param cells
     * @return
     */
    protected Cell pickRandomCell(Collection<? extends Cell> cells){
		if (cells.size()==0){
			return null;
		}
		Cell[] c = cells.toArray(new Cell[cells.size()]);
		int i = rand.nextInt(cells.size());
		Cell ret = c[i];
		return ret;
	}
}
