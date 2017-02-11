package model;



import cell.Cell;
import cell.WatorCell;
import cell.generator.WatorCellGenerator;
import cell.state.WatorState;
import cellsociety.Model;
import grid.FlatGrid;
import util.CAData;

public class WatorModel extends Model {
    
    public static final String NAME = "wator";
    public static final int DEFAULT_FISH_BREED_PERIOD = 5;
    public static final int DEFAULT_SHARK_BREED_PERIOD = 25;
    public static final int DEFAULT_SHARK_INITIAL_ENERGY = 5;
    public static final int DEFAULT_ENERGY_PER_FISH = 5;

    private int energyMax=DEFAULT_SHARK_INITIAL_ENERGY;
    private int sharkBreedPeriod=DEFAULT_SHARK_BREED_PERIOD;
    private int fishBreedPeriod=DEFAULT_FISH_BREED_PERIOD;
    private int fishEnergy= DEFAULT_ENERGY_PER_FISH;
	public WatorModel(CAData data) {
		super(new FlatGrid(data.numRows(), data.numCols(), data.getCell(), new WatorCellGenerator()), false);
		for(int row = 0; row < getGrid().numRows(); row++) {
			for(int col = 0; col < getGrid().numCols(); col++) {
				initializeCellAttributes((WatorCell) getGrid().get(row, col));
			}
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
	@Override
	public void update() {
		for (Cell shark : getGrid().getCells(WatorState.SHARK)){
			updateGridCell((WatorCell) shark);
		}
		 for (Cell fish : getGrid().getCells(WatorState.FISH)){
			updateGridCell((WatorCell) fish);
		}
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
			newCell.toState(movingCell.getState());
			handleReproduction(newCell, movingCell);
		}
	}
	private void updateEnergies(WatorCell newCell, WatorCell shark ){
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
