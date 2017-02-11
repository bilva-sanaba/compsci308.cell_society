package model;

import cell.WatorCell;
import cellsociety.Cell;
import cellsociety.Model;
import grid.RectangleGrid;
import util.CAData;

public class WatorModel extends Model {
	private int energyMax=5;
	private int sharkBreedPeriod=25;
	private int fishBreedPeriod=5;
	private int fishEnergy= 5;
	public static final String NAME = "wator";

	public WatorModel(CAData data) {
		super(new RectangleGrid(data.numRows(), data.numCols(), data.getCell(), WatorCell.getGenerator()));
		for(int row = 0; row < getGrid().numRows(); row++) {
			for(int col = 0; col < getGrid().numCols(); col++) {
				initializeCellAttributes((WatorCell) getGrid().get(row, col));
			}
		}
	}
	private void initializeCellAttributes(WatorCell cell){
		if (cell.inState(WatorCell.FISH)){
			cell.setReproductionDays(fishBreedPeriod);
		}
		if (cell.inState(WatorCell.SHARK)){
			cell.setReproductionDays(sharkBreedPeriod);
			cell.setEnergy(energyMax);
		}
	}
	@Override
	public void update() {
		for (Cell shark : getCertainCells(WatorCell.SHARK)){
			updateGridCell((WatorCell) shark);
		}
		for(Cell fish : getCertainCells(WatorCell.FISH)){
			updateGridCell((WatorCell) fish);
		}
	}
	private void updateGridCell(WatorCell cell){
		moveAnimal(cell);
		getGrid().update();
	}
	private void moveAnimal(WatorCell cell){
		if (cell.inState(WatorCell.SHARK)){
			moveShark(cell);
		}else{
			moveToWater(cell);
		}
	}
	private void moveToWater(WatorCell cell){
		WatorCell randomWater = (WatorCell) pickRandomCell(cell.getCertainNeighbors(WatorCell.WATER));
		if(randomWater != null) {
			animalMovement(randomWater,cell);
		}else{
			cellStays(cell);
		}  
	}
	private void moveShark(WatorCell cell){
		if (shouldDie(cell)){
			cell.toWater();
		}else{
			WatorCell randomFish = (WatorCell) pickRandomCell(cell.getCertainNeighbors(WatorCell.FISH));
			if (randomFish!=null){
				animalMovement(randomFish,cell);
			}else{
				moveToWater(cell);	
			}
		}
	}
	private void animalMovement(WatorCell newCell, WatorCell movingCell){
		if (newCell!=null){
			if (movingCell.inState(WatorCell.SHARK)){
				updateEnergies(newCell, movingCell);
			}
			newCell.toState(movingCell.getState());
			handleReproduction(newCell, movingCell);
		}
	}
	private void updateEnergies(WatorCell newCell, WatorCell shark ){
		if (newCell.inState(WatorCell.FISH)){
			newCell.setEnergy(shark.getEnergy()+fishEnergy-1);
		}else{
			newCell.setEnergy(shark.getEnergy()-1);
		}
	}
	private void handleReproduction(WatorCell replaced, WatorCell reproducing){
		replaced.setReproductionDays(reproducing.getReproductionDays()-1);
		if (reproducing.canReproduce()){
			if (reproducing.inState(WatorCell.SHARK)){
				sharkReproduction(replaced, reproducing);
			}
			if (reproducing.inState(WatorCell.FISH)){
				fishReproduction(replaced,reproducing);
			}
		}else{
			reproducing.toWater();
		}
	}
	private void sharkReproduction(WatorCell notShark, WatorCell shark){
		shark.setEnergy(energyMax);
		shark.setReproductionDays(sharkBreedPeriod);
		notShark.setReproductionDays(sharkBreedPeriod);
	}
	private void fishReproduction(WatorCell notFish, WatorCell fish){
		fish.setReproductionDays(fishBreedPeriod);
		notFish.setReproductionDays(fishBreedPeriod);
	}
	private void cellStays(WatorCell cell){
		cell.setReproductionDays(cell.getReproductionDays()-1);
		if (cell.inState(WatorCell.SHARK)){
			cell.setEnergy(cell.getEnergy()-1);	
		}
	}
	private boolean shouldDie(WatorCell shark){
		return (shark.getEnergy()==0);
	}
}
