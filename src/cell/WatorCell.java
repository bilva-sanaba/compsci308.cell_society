package cell;

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
    public static final int energyMax=10;
    public static final int sharkReproductionPeriod=10;
    public static final int fishReproductionPeriod=5;
    public static final int energyPerFish = 5;
    
    private int energy;
    private int fishReproduction; 
    private int sharkReproduction;
    
    private WatorCell(CellState state) {
        super(state);
        energy = energyMax;
        fishReproduction = fishReproductionPeriod;  
        sharkReproduction = sharkReproductionPeriod;
    }
    
    public int getEnergy(){
    	return energy;
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
