package cellsociety;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cell.Cell;
import cell.state.CellState;

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
        return myPopulation;
    }
    
    public abstract void update();
    
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
		int i = myRand.nextInt(cells.size());
		Cell ret = c[i];
		return ret;
	}
    
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
