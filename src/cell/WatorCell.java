package cell;

import java.util.HashSet;
import java.util.Set;

import cellsociety.CAException;
import cellsociety.Cell;
import javafx.scene.paint.Color;

/**
 * Cell for Wa-Tor simulation
 * @author Mike Liu
 *
 */
public class WatorCell extends Cell {
    
    public static final CellState WATER = new CellState(0, Color.BLUE);
    public static final CellState FISH = new CellState(1, Color.BISQUE);
    public static final CellState SHARK = new CellState(2, Color.GREY);
    public static final int ENERGY_MAX=10;
    public static final int SHARK_BREED_PERIOD=10;
    public static final int FISH_BREED_PERIOD=5;
    public static final int FISH_ENERGY = 5;
    
    private int energy;
    private int fishReproduction; 
    private int sharkReproduction;
    
    private WatorCell(CellState state) {
        super(state);
        energy = ENERGY_MAX;
        fishReproduction = FISH_BREED_PERIOD;  
        sharkReproduction = SHARK_BREED_PERIOD;
    }
    public Set<Cell> getCertainNeighbors(CellState state){
    	Set<Cell> certainNeighbors = new HashSet<Cell>();
    	for (Cell neighbor : this.getNeighbors()){
    		if (neighbor.inState(state)){
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
		if (this.inState(WatorCell.SHARK)){
			return (this.getSharkDays()==0);
		}
		if (this.inState(WatorCell.FISH)){
			return (this.getFishDays()==0);
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
        setNextState(WATER);
    }
    
    public void toFish() {
        setNextState(FISH);
    }
    
    public void toShark() {
        setNextState(SHARK);
    }
    
    public static CellGenerator getGenerator() {
        return new CellGenerator() {

            @Override
            public Cell getBasicCell() {
                return new WatorCell(WATER);
            }

            @Override
            public Cell getCell(int state) {
                if(WATER.equals(state)) {
                    return new WatorCell(WATER);
                }
                else if(FISH.equals(state)) {
                    return new WatorCell(FISH);
                }
                else if(SHARK.equals(state)) {
                    return new WatorCell(SHARK);
                }
                throw new CAException(CAException.INVALID_CELL, "Wa-Tor");
            }
        };
    }
}
