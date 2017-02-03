package model;


import cell.SegregationCell;
import cellsociety.Cell;
import cellsociety.Model;


public class SegregationModel extends Model{
	private int relocateRed =0;
	private int relocateBlue =0; 
	public static final double happyPercent = .3;
	
	 @Override
	 public void update() {
		
		 
	    for(int row = 0; row < getGrid().numRows(); row++) {
	        for(int col = 0; col < getGrid().numCols(); col++) {
	            removeUnhappy((SegregationCell) getGrid().get(row, col));
	            }
	     }
	    moveUnhappy();
	    getGrid().update();
	    }
	 private void moveUnhappy(){
		 for(int row = 0; row < getGrid().numRows(); row++) {
		        for(int col = 0; col < getGrid().numCols(); col++) {
		        		while (relocateRed!=0){
		        			SegregationCell currentCell = (SegregationCell) getGrid().get(row, col);
		        			if (currentCell.getState() == SegregationCell.EMPTY){
		        				currentCell.fillRed();
		        				relocateRed--;
		        			}
		        		}
		        		while (relocateBlue!=0){
		        			SegregationCell currentCell = (SegregationCell) getGrid().get(row, col);
		        			if (currentCell.getState() == SegregationCell.EMPTY){
		        				currentCell.fillBlue();
		        				relocateBlue--;
		        			}
		        		}
		        }
		 }
	 }
	 private void removeUnhappy(SegregationCell cell) {
	        int numberOfNeighbors = 0;
	        int numberSameNeighbors = 0;
	        if (cell.getState()!= SegregationCell.EMPTY){
	        for(Cell c: cell.getNeighbors()) {
	        	numberOfNeighbors++;
	            if(cell.getState() == c.getState()) {
	            	numberSameNeighbors++;
	            }
	        }
	        if ((double) numberSameNeighbors/(double) numberOfNeighbors < happyPercent){
	        	if (cell.getState() == SegregationCell.RED){
	        		relocateRed++;
	        	}else {
	        		relocateBlue++;
	        	}
	        	cell.leave();
	        }
	   
	    }
	 }
}
