package cellsociety;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cell.Cell;

public abstract class Model {
    
    private Grid myGrid;
    Map<String, Integer> myPopulation;
    private Random myRand;
    
    public Model(Grid grid) {
        myGrid = grid;
        myRand = new Random();
        myPopulation = new HashMap<String, Integer>();
    }
    
    public Grid getGrid() {
        return myGrid;
    }
    
    public void setGrid(String type) {
        this.myGrid = myGrid.switchGrid(type);
    }
    
    public void click(int row, int col) {
        getGrid().get(row, col).rotateState();
    }
    
    public Map<String, Integer> getPopulation() {
        myPopulation.put("TEST", 1);
        return myPopulation;
        //TODO
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
		int i = myRand.nextInt(cells.size());
		Cell ret = cells.get(i);
		cells.remove(i);
		return ret;
	}
}
