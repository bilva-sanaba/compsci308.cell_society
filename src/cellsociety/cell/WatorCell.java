package cellsociety.cell;

import java.util.ArrayList;
import java.util.List;

import cellsociety.cell.Cell;
import cellsociety.cell.state.CellState;
import cellsociety.cell.state.WatorState;


/**
 * Cell for Wa-Tor simulation
 * @author Bilva Sanaba
 * @author Mike Liu
 *
 */
public class WatorCell extends Cell {
        
    private int energy,reproductionDaysLeft;
    
    public WatorCell(CellState state) {
        super(state);
    }
    public List<Cell> getCertainNeighbors(CellState state){
    	List<Cell> certainNeighbors = new ArrayList<Cell>();
    	for (Cell neighbor : this.getNeighbors()){
    		if (neighbor.is(state)){
    			certainNeighbors.add(neighbor);
    		}
    	}
    	return certainNeighbors;
    }
    public int getEnergy(){
    	return energy;
    }
    public boolean shouldDie(){
		return this.getEnergy()==0;
    }
    public boolean canReproduce(){
		return this.getReproductionDays()==0;
	}
    public void setEnergy(int newEnergy){
    	energy= newEnergy;
    }   

    public void setReproductionDays(int daysTillReproduce){
    	reproductionDaysLeft = daysTillReproduce;
    }
    public int getReproductionDays(){
    	return reproductionDaysLeft;
    }
    public void toWater() {
        setNextState(WatorState.WATER);
    }
    
    public void toFish() {
        setNextState(WatorState.FISH);
    }
    
    public void toShark() {
        setNextState(WatorState.SHARK);
    }
    
    public void toState(WatorCell cell){
    	setNextState(cell.getState());
    }
}
