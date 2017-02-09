package cellsociety;

import java.util.List;
import java.util.Random;

import cell.Cell;

public abstract class Model {
    private Random rand = new Random();
    private Grid myGrid;
    
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
    
    /**
     * Picks a random cell from the list and removes it from the list
     * Modifier
     * @param cells
     * @return
     */
    protected Cell pickRandomCell(List<? extends Cell> cells){
		if (cells.size()==0){
			return null;
		}
		int i = rand.nextInt(cells.size());
		Cell ret = cells.get(i);
		cells.remove(i);
		return ret;
	}
}
