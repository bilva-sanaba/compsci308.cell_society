package cellsociety;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import cell.CellConfig;
import cell.CellGenerator;
import grid.Coordinates;

/**
 * Superclass of grid that contains cells in the simulation
 * @author Mike Liu
 * 
 */
public abstract class Grid {

    private Cell[][] sim;
    
    public Grid(int row, int col, Collection<CellConfig> cellConfig, CellGenerator generator) {
        sim = new Cell[row][col];
        for(CellConfig config: cellConfig) {
            sim[config.getRow()][config.getCol()] = generator.getCell(config.getState());
        }
        fillEmpty(generator);
        buildNeighborGraph();
        for(int i = 0; i < numRows(); i++) {
            for(int j = 0; j < numCols(); j++) {
                
            }
        }
    }

    public Grid(Grid other) {
        sim = other.sim;
        buildNeighborGraph();
    }
    
    public int numRows() {
        return sim.length;
    }
    
    public int numCols() {
        return sim[0].length;
    }
    
    public Cell get(int row, int col) {
        return sim[row][col];
    }
    
    public void update() {
        for(int row = 0; row < numRows(); row++) {
            for(int col = 0; col < numCols(); col++) {
                sim[row][col].update();
            }
        }
    }
    
    /**
     * Finds the neighbors of the cell at location (row, col)
     * Implemented by subclasses only for the purpose of build neighbor graph
     * Should not be called for other classes
     * @param row - the row of grid that the cell is in
     * @param col - the column of grid that the cell is in
     * @return
     */
    protected abstract Set<Cell> findNeighbor(int row, int col);

    private void fillEmpty(CellGenerator generator) {
        for(int i = 0; i < numRows(); i++) {
            for(int j = 0; j < numCols(); j++) {
                if(sim[i][j] == null) {
                    sim[i][j] = generator.getBasicCell();
                }
            }
        }
    }
    
    private void buildNeighborGraph() {
        for(int row = 0; row < sim.length; row++) {
            for(int col = 0; col < sim[0].length; col++) {
                sim[row][col].setNeighbors(findNeighbor(row, col));
            }
        }
    }
	protected boolean isInBounds(int row, int col){
		return (col >= 0 && row >=0 && col <= numCols()-1 && row <= numRows()-1);
	} 
	protected boolean pastTopEdge(int row){
		return (row<0);
	}
	protected boolean pastLeftEdge(int col){
		return (col<0);
	}
	protected boolean pastRightEdge(int col){
		return (col>numRows()-1);
	}
	protected boolean pastBottomEdge(int row){
		return (row>numCols()-1);
	}
	protected Coordinates warpCell(int r, int c){
		if (pastTopEdge(r)){
			r=numRows()-1;
		}
		if (pastBottomEdge(r)){
			r=0;
		}
		if (pastRightEdge(c)){
			c=0;
		}
		if (pastLeftEdge(c)){
			c=numRows()-1;
		}
		return new Coordinates(r,c);
	}
    protected List<Coordinates> getToroidalNeighbors(int row, int col, Coordinates[] coordinates){
    	List<Coordinates> toroidalNeighbors = new ArrayList<Coordinates>();
        for (Coordinates coord : coordinates){
        	int r = row + coord.getX();
        	int c = col + coord.getY();
        	if (isInBounds(r,c)){
        		toroidalNeighbors.add(new Coordinates(r,c));
        		
        	}else{
        		toroidalNeighbors.add(warpCell(r,c));
        	}
        }
        return toroidalNeighbors;
    }
    
}
