package cellsociety;

import java.util.List;
import java.util.Random;

import cell.Cell;

public abstract class Model {
    
    private Random rand;
    private Grid myGrid;
    
    public Model(Grid grid) {
        myGrid = grid;
        rand = new Random();
    }
    
    public Grid getGrid() {
        return myGrid;
    }
    
    public void setGrid(String type) {
        this.myGrid = Grid.getGrid(myGrid, type);
    }
    
    public void click(int row, int col) {
        getGrid().get(row, col).rotateState();
    }
    
    public abstract void update();
    
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
