package model;

import java.util.HashSet;
import java.util.Set;
import cell.WatorCell;
import cellsociety.Cell;
import cellsociety.Model;
import grid.RectangleGrid;
import util.CAData;

public class WatorModel extends Model {

    public static final String NAME = "wator";
    
    private int energyMax=5;
    private int sharkBreedPeriod=25;
    private int fishBreedPeriod=5;
    private int fishEnergy= 5;
    
    public WatorModel(CAData data) {
        super(new RectangleGrid(data.numRows(), data.numCols(), data.getCell(), WatorCell.getGenerator(), false));
        for(int row = 0; row < getGrid().numRows(); row++) {
        	for(int col = 0; col < getGrid().numCols(); col++) {
        		if ((getGrid().get(row, col)).is(WatorCell.FISH)){
        			WatorCell currentCell = (WatorCell)getGrid().get(row, col);
        			currentCell.setFishDays(fishBreedPeriod);
        		}else{
        			if (getGrid().get(row, col).is(WatorCell.SHARK)){
        				WatorCell currentCell = (WatorCell) getGrid().get(row, col);
            			currentCell.setFishDays(sharkBreedPeriod);
        			}
        		}
        	}
        }
    }
    @Override
    public void update() {
    	for (Cell shark : getCertainCells(WatorCell.SHARK)){
	            moveShark((WatorCell) shark);
    			getGrid().update();
    	}
    	for(Cell fish : getCertainCells(WatorCell.FISH)){
	            moveFish((WatorCell) fish);
	            getGrid().update();
	            }
    }
    private void sharkMovement(WatorCell notShark, WatorCell shark){
    	if (notShark!=null){
    		notShark.toShark();
    		if (notShark.is(WatorCell.FISH)){
    			notShark.setEnergy(shark.getEnergy()+fishEnergy-1);
    		}else{
    			notShark.setEnergy(shark.getEnergy()-1);
    		}
			notShark.setSharkDays(shark.getSharkDays()-1);
			handleReproduction(notShark,shark);
    	}
    		
    }
//	private void fishToShark(WatorCell fish, WatorCell shark){
//		if (fish!=null){
//			fish.toShark();
//			fish.setEnergy(shark.getEnergy()+fishEnergy-1);
//			fish.setSharkDays(shark.getSharkDays()-1);
//			handleReproduction(fish,shark);
//		}
//	}
	private void handleReproduction(WatorCell notShark, WatorCell shark){
		if (shark.canReproduce()){
         	shark.setEnergy(energyMax);
         	shark.setSharkDays(sharkBreedPeriod);
         	notShark.setSharkDays(sharkBreedPeriod);
         }else{
         shark.toWater();
         }
	}
//	private void waterToShark(WatorCell water, WatorCell shark){
//		 if(water != null) {
//             water.toShark();
//             water.setEnergy(shark.getEnergy()-1);
//             water.setSharkDays(shark.getSharkDays()-1);
//             handleReproduction(water,shark);
//         }
//	}
	private void sharkStays(WatorCell cell){
		cell.setSharkDays(cell.getSharkDays()-1);
		cell.setEnergy(cell.getEnergy()-1);	
	}
	
    private void moveShark(WatorCell cell){
    	if (cell.is(WatorCell.SHARK)){
    		if (cell.shouldDie()){
    			cell.toWater();
    		}else{
    		WatorCell randomFish = (WatorCell) pickRandomCell(cell.getCertainNeighbors(WatorCell.FISH));
    		WatorCell randomWater = (WatorCell) pickRandomCell(cell.getCertainNeighbors(WatorCell.WATER));
    		sharkMovement(randomFish,cell);
    		if (randomFish==null){
    			sharkMovement(randomWater, cell);
    		}
    		if (randomFish==null && randomWater==null){
    			sharkStays(cell);
    		}		
    	}
    	}
    }
    private void moveFish(WatorCell cell){
        if (cell.is(WatorCell.FISH)){
            Set<Cell> water = new HashSet<Cell>();
            for (Cell neighbor : cell.getNeighbors()){
                if(neighbor.is(WatorCell.WATER)) {
                    water.add(neighbor);
                }
            }

            WatorCell randomWater = (WatorCell) pickRandomCell(water);
            if(randomWater != null) {
             randomWater.toFish();
                    randomWater.setFishDays(cell.getFishDays()-1);
                    if (cell.canReproduce()){
                    	cell.setFishDays(fishBreedPeriod);
                    	randomWater.setFishDays(fishBreedPeriod);
                    	
                    }else{
                    cell.toWater();

                }
            }else{
                cell.setFishDays(cell.getFishDays()-1);
            }  
        }
    }
}
