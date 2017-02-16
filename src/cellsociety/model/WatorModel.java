// This entire file is part of my masterpiece.
// BILVA SANABA

// This code allows the Program to run the WatorModel. 
// The class extends Model which is important because in  another class, an instance of Model is created based on CAData
// and that instance of model is what is updated allowing the simulation to run.This code is well designed for several reasons.
// First, it follows consistent naming conventions throughout. It also has its default parameters stored in a private
// instance of a class called WatorModelParameters. This allows default settings to be changed without affecting 
// the actual model class. Additionally, there are many helper methods designed to make the code significantly easier 
// to read and these methods have been improved and restructured in order to significantly lower duplicate code. 
// Finally, in order to further lower duplicate code, many methods such as pickRandomCell or getCertainCells have been
// moved out of this class to the superclass Model or superclass Grid (since Model contains an instance of Grid), and
// other methods have been moved to WatorCell if appropriate. Overall, my work on this class over the project time
// highlights how I have learned to utilize abstract classes, lower duplicate code, break classes down into smaller 
// classes, and refactor to make code more readable. 

package cellsociety.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import cellsociety.cell.Cell;
import cellsociety.cell.WatorCell;
import cellsociety.cell.generator.WatorCellGenerator;
import cellsociety.cell.state.WatorState;
import cellsociety.grid.FlatGrid;
import util.CAData;

/**
 * Model for Wa-Tor simulation
 * @author Bilva Sanaba
 * @author Mike Liu
 */

public class WatorModel extends Model {    
	public static final String NAME = "wator";
	public static final Collection<String> DOCUMENTED_STATES = Arrays.asList(WatorState.FISH.toString(),WatorState.SHARK.toString());    
	private WatorModelParameters myParameters = new WatorModelParameters();

	public WatorModel(CAData data) {
		super(new FlatGrid(data.numRows(), data.numCols(), data.getCell(), new WatorCellGenerator()));
		for(Cell cell: getGrid()) {
			initializeCellAttributes((WatorCell)cell);
		}
	}	
	
	@Override
	public String getName() {
		return NAME;
	}
	
	public WatorModelParameters getParameters(){
		return myParameters;
	}
	
	@Override
	protected Collection<String> getDocumentedStates() {
		return DOCUMENTED_STATES;
	}
	
	/**
	 * Method called when a cell is clicked. Cell state is rotated, and the new state is initialized with appropriate cell attributes
	 */
	@Override
	public void click(int row, int col) {
		super.click(row,col);
		WatorCell cell = (WatorCell)getGrid().get(row, col);
		initializeCellAttributes(cell);
	}
	
	/**
	 * Takes a cell and set its parameters to the defaults settings
	 * @param cell
	 */
	private void initializeCellAttributes(WatorCell cell){
		reproduce(cell,cell);
	}
	
	/**
	 * Updates the simulation by moving all sharks to appropriate locations and then all fish. 
	 */
	@Override
	public void update() {
		for (Cell shark : getGrid().getCells(WatorState.SHARK)){
			updateGridCell((WatorCell) shark);
		}
		for (Cell fish : getGrid().getCells(WatorState.FISH)){
			updateGridCell((WatorCell) fish);
		}
		updateCount();
	}
	
	private void updateCount(){
		setCount(WatorState.SHARK,getGrid().getCells(WatorState.SHARK).size());
		setCount(WatorState.FISH,getGrid().getCells(WatorState.FISH).size());
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
	
	private void moveShark(WatorCell cell){
		if (cell.shouldDie()){
			cell.toWater();
		}else{
			List<Cell> fishNeighbors = cell.getCertainNeighbors(WatorState.FISH);
			if(fishNeighbors.size() == 0) {
				moveToWater(cell);
			} else {
				animalMovement((WatorCell) pickRandomCell(fishNeighbors), cell);
			}
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
	
	private void animalMovement(WatorCell newCell, WatorCell movingCell){
			updateEnergies(newCell, movingCell);
			newCell.toState(movingCell);
			handleReproduction(newCell, movingCell);
	}
	
	private void updateEnergies(WatorCell newCell, WatorCell shark){
		if (shark.is(WatorState.SHARK)){
			newCell.setEnergy(shark.getEnergy()-1);
			if (newCell.is(WatorState.FISH)){
				newCell.setEnergy(newCell.getEnergy()+myParameters.getFishEnergy());
			}
		}
	}
	
	private void handleReproduction(WatorCell replaced, WatorCell reproducing){
		replaced.setReproductionDays(reproducing.getReproductionDays()-1);
		if (reproducing.canReproduce()){
			reproduce(replaced,reproducing);
		}else{
			reproducing.toWater();	
		}
	}
	
	private void reproduce(WatorCell replaced, WatorCell reproducing){
		reproducing.resetEnergy(myParameters);
		reproducing.resetReproductionDays(myParameters);
		replaced.resetReproductionDays(myParameters);
	}
	
	private void cellStays(WatorCell cell){
		cell.lowerReproductionDays();
		cell.lowerEnergy();	
	}
	
	/**
	 * Changes the energy in a shark when spawned. Updates all sharks, to this energy if they have more energy.   
	 * @param energy
	 */
	public void updateSharkEnergy(int energy){
		myParameters.setEnergyMax(energy);
		for (Cell shark : getGrid().getCells(WatorState.SHARK)){
			setSharkEnergy((WatorCell) shark);
		}
	}
	
	private void setSharkEnergy(WatorCell shark) {
		shark.setEnergy(Math.min(shark.getEnergy(),myParameters.getEnergyMax()));
	}

	/**
	 * Changes the days it takes for a shark to reproduce. Sharks reproduction days left are lowered if appropriate
	 * @param period
	 */
	public void updateSharkBreedPeriod(int period) {
		myParameters.setSharkBreedPeriod(period);
		for (Cell shark : getGrid().getCells(WatorState.SHARK)){
			setSharkBreedPeriod((WatorCell) shark);
		}
	}
	
	private void setSharkBreedPeriod(WatorCell shark){
		shark.setReproductionDays(Math.min(shark.getReproductionDays(),myParameters.getSharkBreedPeriod()));
	}

	/**
	 * Changes the days it takes for a fish to reproduce. Fish reproduction days left are lowered if appropriate
	 * @param period
	 */
	public void updateFishBreedPeriod(int period) {
		myParameters.setFishBreedPeriod(period);
		for (Cell fish : getGrid().getCells(WatorState.FISH)){
			setFishBreedPeriod((WatorCell) fish);
		}
	}
	
	private void setFishBreedPeriod(WatorCell fish){
		fish.setReproductionDays(Math.min(fish.getReproductionDays(),myParameters.getFishBreedPeriod()));
	}
}
