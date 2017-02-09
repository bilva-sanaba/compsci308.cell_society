package cell;

import java.util.ArrayList;
import java.util.List;

import cellsociety.CAException;
import cellsociety.Cell;
import javafx.scene.paint.Color;

/**
 * Cell for Wa-Tor simulation
 * @author Mike Liu
 *
 */
public class WatorCell extends Cell {
  
    public static final CellState WATER = new CellState("Water", Color.BLUE);
    public static final CellState FISH = new CellState("Fish", Color.BISQUE);
    public static final CellState SHARK = new CellState("Shark", Color.GREY);
    
    private int energy, fishReproduction, sharkReproduction;
    
    private WatorCell(CellState state) {
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
		if (this.is(WatorCell.SHARK)){
			return (this.getSharkDays()<=0);
		}
		if (this.is(WatorCell.FISH)){
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
                if(state == 0) {
                    return new WatorCell(WATER);
                }
                else if(state == 1) {
                    return new WatorCell(FISH);
                }
                else if(state == 2) {
                    return new WatorCell(SHARK);
                }
                throw new CAException(CAException.INVALID_CELL, "Wa-Tor");
            }
        };
    }
}
