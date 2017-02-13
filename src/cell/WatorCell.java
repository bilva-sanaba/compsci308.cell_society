package cell;

import java.util.ArrayList;
import java.util.List;


import cellsociety.CAException;
import cell.Cell;
import cell.generator.CellGenerator;
import cell.state.CellState;
import cell.state.WatorState;


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
    
    public static CellGenerator getGenerator() {
        return new CellGenerator() {

            @Override
            public Cell getBasicCell() {
                return new WatorCell(WatorState.WATER);
            }

            @Override
            public Cell getCell(int state) {
                if(WatorState.WATER.equals(state)) {
                    return new WatorCell(WatorState.WATER);
                }
                else if(WatorState.FISH.equals(state)) {
                    return new WatorCell(WatorState.FISH);
                }
                else if(WatorState.SHARK.equals(state)) {
                    return new WatorCell(WatorState.SHARK);
                }
                throw new CAException(CAException.INVALID_CELL, "Wa-Tor");
            }
        };
    }
}
