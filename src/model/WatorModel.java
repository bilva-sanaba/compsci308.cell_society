package model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import cell.SegregationCell;
import cell.WatorCell;
import cellsociety.Cell;
import cellsociety.Model;

public class WatorModel extends Model {
	private Random rand = new Random();
	private Cell pickRandomFish(Set<Cell> fish){
		int i = 0;
		int random = rand.nextInt(fish.size());
		for(Cell neighbor : fish)
			{
		    if (i == random)
		        return neighbor;
		    i++;
		}
		return null;
	}
    private void moveShark(WatorCell cell){
    	if (cell.getState()== WatorCell.SHARK){
    		Set<Cell> fish = new HashSet<Cell>();
    		for (Cell neighbor : cell.getNeighbors()){
    			if (neighbor.getState() == WatorCell.FISH){
    				fish.add(neighbor);
    			}
    		}
    		WatorCell randomFish = (WatorCell) pickRandomFish(fish);
    		if (randomFish!=null){
    		randomFish.toShark();
    		randomFish.setEnergy(cell.getEnergy()+WatorCell.energyPerFish-1);
    		randomFish.setSharkDays(cell.getSharkDays()-1);
    		cell.toWater();
    		}else{
    			cell.setSharkDays(cell.getSharkDays()-1);
    			cell.setEnergy(cell.getEnergy()-1);
    		}	
    	}
    }
    private void moveFish(WatorCell cell){
    	//move fish and check to make sure water is included in find neighbors add this to update function 
    	//make sure update function does so twice
    }
    
    @Override
    public void update() {
    	for(int row = 0; row < getGrid().numRows(); row++) {
	        for(int col = 0; col < getGrid().numCols(); col++) {
	            moveShark((WatorCell) getGrid().get(row, col));
	            }
	     }
    	getGrid().update();
    	for(int row = 0; row < getGrid().numRows(); row++) {
	        for(int col = 0; col < getGrid().numCols(); col++) {
	            moveFish((WatorCell) getGrid().get(row, col));
	            }
	     }
    }

}
