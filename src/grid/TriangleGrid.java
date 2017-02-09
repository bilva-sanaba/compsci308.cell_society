package grid;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import cell.CellConfig;
import cell.CellGenerator;
import cellsociety.Cell;
import cellsociety.Grid;

public class TriangleGrid extends Grid {
	private int [] possibleXNeighbors; 
	private int [] possibleYNeighbors; 
	public TriangleGrid(int row, int col, Collection<CellConfig> cellConfig, CellGenerator generator) {
        super(row, col, cellConfig, generator); 
    }
	
	
	private Coordinates[] createRegularNeighbors(){
		Coordinates[] regularNeighbors = new Coordinates[12];
		int i = 0;
		possibleXNeighbors = new int[]{-2,-1,0,1,2};
		possibleYNeighbors = new int[]{-1,0,1};
		for (int x : possibleXNeighbors){	
			for (int y : possibleYNeighbors){
				if (y==-1){
					if (Math.abs(x)!=2){
					regularNeighbors[i] = new Coordinates(x,y);
					i++;
					}
				}
				if (y==0){
					if (x!=0){
						regularNeighbors[i] = new Coordinates(x,y);
						i++;
					}
				}
				if (y==1){
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