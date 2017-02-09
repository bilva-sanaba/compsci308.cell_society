package grid;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import cell.CellConfig;
import cell.CellGenerator;
import cellsociety.Cell;
import cellsociety.Grid;

public class HexagonalGrid extends Grid {
	private int [] possibleXNeighbors; 
	private int [] possibleYNeighbors; 
	public HexagonalGrid(int row, int col, Collection<CellConfig> cellConfig, CellGenerator generator) {
        super(row, col, cellConfig, generator); 
    }
	
	
	private Coordinates[] createRegularNeighbors(){
		Coordinates[] regularNeighbors = new Coordinates[6];
		int i = 0;
		possibleXNeighbors = new int[]{-1,0,1};
		possibleYNeighbors = new int[]{-1,0,1};
		for (int x : possibleXNeighbors){	
			for (int y : possibleYNeighbors){
				if (!(x==-1 && y==-1) || !(x==0 && y==0) || !(x==-1 && y==1)){
					regularNeighbors[i] = new Coordinates(x,y);
					i++;
				}
			}
		}
		return regularNeighbors;
	}
 
    @Override
    protected Set<Cell> findNeighbor(int row, int col) {
        Set<Cell> neighbors = new HashSet<Cell>();
        Coordinates[] regularNeighbors = createRegularNeighbors();
        for (Coordinates coord : getToroidalNeighbors(row,col,regularNeighbors)){
        	neighbors.add(get(coord.getX(),coord.getY()));
        }
        return neighbors;
    }	
}
