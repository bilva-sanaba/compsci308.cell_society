package model;



import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import cell.Cell;
import cell.WatorCell;
import cell.generator.WatorCellGenerator;
import cell.state.WatorState;
import cellsociety.Model;
import grid.FlatGrid;
import util.CAData;

public class WatorModel extends Model {
    
    public static final String NAME = "wator";
    public static final List<String> DOCUMENTED_STATES = Arrays.asList(
            WatorState.FISH.toString(),
            WatorState.SHARK.toString());
    
    public static final int DEFAULT_FISH_BREED_PERIOD = 5;
    public static final int DEFAULT_FISH_ENERGY = 5;
    public static final int DEFAULT_SHARK_BREED_PERIOD = 25;
    public static final int DEFAULT_SHARK_ENERGY = 5;

    private int energyMax=DEFAULT_SHARK_ENERGY;
    private int sharkBreedPeriod=DEFAULT_SHARK_BREED_PERIOD;
    private int fishBreedPeriod=DEFAULT_FISH_BREED_PERIOD;
    private int fishEnergy= DEFAULT_FISH_ENERGY;
    
	public WatorModel(CAData data) {
		super(new FlatGrid(data.numRows(), data.numCols(), data.getCell(), new WatorCellGenerator(), false));
		for(Cell cell: getGrid()) {
		    initializeCellAttributes((WatorCell)cell);
		}
	}
	private void initializeCellAttributes(WatorCell cell){
		if (cell.is(WatorState.FISH)){
			cell.setReproductionDays(fishBreedPeriod);
		}
		if (cell.is(WatorState.SHARK)){
			cell.setReproductionDays(sharkBreedPeriod);
			cell.setEnergy(energyMax);
		}
	}
	
	public void setSharkEnergy(int energy) {
	    energyMax = energy;
	}
	
	public void setFishEnergy(int energy) {
	    fishEnergy = energy;
	}
	
	public void setSharkBreedPeriod(int period) {
	    sharkBreedPeriod = period;
	}
	
	public void setFishBreedPeriod(int period) {
	    fishBreedPeriod = period;
	}
    
	@Override
    public void click(int row, int col) {
	    WatorCell cell = (WatorCell)getGrid().get(row, col);
        cell.rotateState();
        if(cell.is(WatorState.SHARK)) {
            sharkReproduction(cell, cell);
        }
        else if(cell.is(WatorState.FISH)) {
            fishReproduction(cell, cell);
        }
    }
	
	@Override
	public void update() {
		for (Cell shark : getGrid().getCells(WatorState.SHARK)){
			updateGridCell((WatorCell) shark);
		}
		 for (Cell fish : getGrid().getCells(WatorState.FISH)){
			updateGridCell((WatorCell) fish);
		}
	}
    @Override
    protected Collection<String> getDocumentedStates() {
        return DOCUMENTED_STATES;
    }
	private void updateGridCell(WatorCell cell){
		moveAnimal(cell);
		getGrid().update();
	}
	private void moveAnimal(WatorCell cell){
		if (cell.is(WatorState.SHARK)){
			moveShark(cell);
		}else{
			moveToWater(cell);
		}
	}
	private void moveToWater(WatorCell cell){
		WatorCell randomWater = (WatorCell) pickRandomCell(cell.getCertainNeighbors(WatorState.WATER));
		if(randomWater != null) {
			animalMovement(randomWater,cell);
		}else{
			cellStays(cell);
		}  
	}
	private void moveShark(WatorCell cell){
		if (shouldDie(cell)){
			cell.toWater();
			addCount(cell.getState(), -1);
		}else{
			WatorCell randomFish = (WatorCell) pickRandomCell(cell.getCertainNeighbors(WatorState.FISH));
			if (randomFish!=null){
				animalMovement(randomFish,cell);
			}else{
				moveToWater(cell);	
			}
		}
	}
	private void animalMovement(WatorCell newCell, WatorCell movingCell){
		if (newCell!=null){
			if (movingCell.is(WatorState.SHARK)){
				updateEnergies(newCell, movingCell);
			}
			newCell.toState(movingCell);
			addCount(newCell.getState(), -1);
			addCount(movingCell.getState(), +1);
			handleReproduction(newCell, movingCell);
		}
	}
	private void updateEnergies(WatorCell newCell, WatorCell shark){
		if (newCell.is(WatorState.FISH)){
			newCell.setEnergy(shark.getEnergy()+fishEnergy-1);
		}else{
			newCell.setEnergy(shark.getEnergy()-1);
		}
	}
	private void handleReproduction(WatorCell replaced, WatorCell reproducing){
		replaced.setReproductionDays(reproducing.getReproductionDays()-1);
		if (reproducing.canReproduce()){
			if (reproducing.is(WatorState.SHARK)){
				sharkReproduction(replaced, reproducing);
			}
			if (reproducing.is(WatorState.FISH)){
				fishReproduction(replaced,reproducing);
			}
		}else{
			reproducing.toWater();
			addCount(reproducing.getState(), -1);
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
		if (cell.is(WatorState.SHARK)){
			cell.setEnergy(cell.getEnergy()-1);	
		}
	}
	private boolean shouldDie(WatorCell shark){
		return (shark.getEnergy()==0);
	}
}
