package cellsociety;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cell.Cell;
import cell.state.CellState;

/**
 * Superclass for all simulation models
 * @author Mike Liu
 *
 */
public abstract class Model {
    
    private Grid myGrid;
    Map<String, Integer> myPopulation;
    private Random myRand;
    
    public Model(Grid grid) {
        myGrid = grid;
        myRand = new Random();
        myPopulation = new HashMap<String, Integer>();
        for(String key: getDocumentedStates()) {
            myPopulation.put(key, 0);
        }
        for(Cell cell: getGrid()) {
            addCount(cell.getState(), 1);
        }
    }
    
    /**
     * Returns the name of the model
     * @return
     */
    public abstract String getName();

    /**
     * Returns the grid of the model
     * @return
     */
    public Grid getGrid() {
        return myGrid;
    }
    
    /**
     * Sets the grid of the model to the specified type
     * @param type - refer to Grid.GRID_TYPE for available types
     */
    public void setGrid(String type) {
        this.myGrid = myGrid.switchGrid(type);
    }
    
    /**
     * Notifies the model that the cell at position (row, col) is clicked
     * @param row
     * @param col
     */
    public void click(int row, int col) {
        getGrid().get(row, col).rotateState();
    }
    
    /**
     * Returns the population map of the model
     * @return
     */
    public Map<String, Integer> getPopulation() {
        return myPopulation;
    }
    
    /**
     * Updates the simulation
     */
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
    
    /**
     * Returns the states that needs to be kept track of
     * Helper method implemented by subclasses
     * @return
     */
    protected abstract Collection<String> getDocumentedStates();
    
    protected void addCount(String state, int num) {
        if(myPopulation.containsKey(state)) {
            myPopulation.put(state, myPopulation.get(state) + num);
        }
    }
    
    protected void addCount(CellState state, int num) {
        addCount(state.toString(), num);
    }
    
    protected void resetCount() {
        for(String key: myPopulation.keySet()) {
            myPopulation.put(key, 0);
        }
    }
}
