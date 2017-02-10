package cell;

import java.util.ArrayList;
import java.util.List;

import cell.state.CellState;
import cell.state.WatorState;

/**
 * Cell for Wa-Tor simulation
 * @author Mike Liu
 *
 */
public class WatorCell extends Cell {
    
    private int energy, fishReproduction, sharkReproduction;
    
    public WatorCell(WatorState state) {
        super(state);
//        energy = ENERGY_MAX;
//        fishReproduction = FISH_BREED_PERIOD;  
//        sharkReproduction = SHARK_BREED_PERIOD;
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
		if (this.is(WatorState.SHARK)){
			return (this.getSharkDays()<=0);
		}
		if (this.is(WatorState.FISH)){
			return (this.getFishDays()<=0);
		}
		return false;
	}
    public void setEnergy(int newEnergy){
    	energy= newEnergy;
    }
    
    public void setSharkDays(int daysTillReproduce ){
    	sharkReproduction = daysTillReproduce;
    }
    
    public void setFishDays(int daysTillReproduce ){
    	fishReproduction = daysTillReproduce;
    }
    
    public int getSharkDays(){
    	return sharkReproduction;
    }
    
    public int getFishDays(){
    	return fishReproduction;
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
}
