package model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import cell.WatorCell;
import cellsociety.Cell;
import cellsociety.Model;
import grid.CardinalRectangleGrid;
import util.CAData;

public class WatorModel extends Model {
    
    public static final String NAME = "wator";
    
	private Random rand = new Random();
	
    public WatorModel(CAData data) {
        super(new CardinalRectangleGrid(data.numRows(), data.numCols(), data.getCell(), WatorCell.getGenerator()));
    }
	private Cell pickRandomCell(Set<Cell> fish){
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
    	if (cell.inState(WatorCell.SHARK)){
    		if (cell.getEnergy()==0){
    			cell.toWater();
    		}else{
    		Set<Cell> fish = new HashSet<Cell>();
    		Set<Cell> water = new HashSet<Cell>();
    		for (Cell neighbor : cell.getNeighbors()){
    			if (neighbor.inState(WatorCell.FISH)){
    				fish.add(neighbor);
    			} else {
    			    if(neighbor.inState(WatorCell.WATER)) {
    			        water.add(neighbor);
    			    }
    			}
    		}
    		WatorCell randomFish = (WatorCell) pickRandomCell(fish);
    		if (randomFish!=null){
    		randomFish.toShark();
    		randomFish.setEnergy(cell.getEnergy()+WatorCell.FISH_ENERGY-1);
    		randomFish.setSharkDays(cell.getSharkDays()-1);
    		cell.toWater();
    		}else{
    		    WatorCell randomWater = (WatorCell) pickRandomCell(water);
                if(randomWater != null) {
                    randomWater.toShark();
                    randomWater.setEnergy(cell.getEnergy()-1);
                    randomWater.setSharkDays(cell.getSharkDays()-1);
                    if (cell.canReproduce()){
                    	cell.setEnergy(WatorCell.ENERGY_MAX);
                    	cell.setSharkDays(WatorCell.SHARK_BREED_PERIOD);
                    	randomWater.setSharkDays(WatorCell.SHARK_BREED_PERIOD);
                    }else{
                    cell.toWater();
                    }
                }
    			cell.setSharkDays(cell.getSharkDays()-1);
    			cell.setEnergy(cell.getEnergy()-1);
    		}	
    	}
    	}
    }
    private void moveFish(WatorCell cell){
    	if (cell.inState(WatorCell.FISH)){
    		if (cell.getEnergy()==0){
    			cell.toWater();
    		}else{
    		Set<Cell> water = new HashSet<Cell>();
    		for (Cell neighbor : cell.getNeighbors()){
    			if(neighbor.inState(WatorCell.WATER)) {
    			        water.add(neighbor);
    			    }
    		}
    		
    		WatorCell randomWater = (WatorCell) pickRandomCell(water);
            if(randomWater != null) {
             randomWater.toFish();
                    randomWater.setEnergy(cell.getEnergy()-1);
                    randomWater.setFishDays(cell.getFishDays()-1);
                    if (cell.canReproduce()){
                    	cell.setFishDays(WatorCell.FISH_BREED_PERIOD);
                    	randomWater.setFishDays(WatorCell.FISH_BREED_PERIOD);
                    }else{
                    cell.toWater();
                    }
             }else{
    			cell.setFishDays(cell.getFishDays()-1);
    			cell.setEnergy(cell.getEnergy()-1);
    		}
    	}
    	}
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
