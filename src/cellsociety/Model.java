package cellsociety;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import cell.CellGenerator;
import cell.CellState;

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
    protected Collection<Cell> getCertainCells(CellState state){
		Collection<Cell> cells = new ArrayList<Cell>();
		for (int row = 0; row < getGrid().numRows(); row++) {
			for (int col = 0; col < getGrid().numCols(); col++) {
				if (getGrid().get(row, col).inState(state)){
					cells.add(getGrid().get(row, col));
				}
			}
		}
		return cells;
	}
}
