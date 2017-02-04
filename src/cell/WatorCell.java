package cell;

import javafx.scene.paint.Color;

import cellsociety.Cell;

/**
 * Cell for Wa-Tor simulation
 * @author Mike Liu
 *
 */
public class WatorCell extends Cell {

    public static final int WATER = 0;
    public static final int FISH = 1;
    public static final int SHARK = 2;
    public static final Color WATER_COLOR = Color.BLUE;
    public static final Color FISH_COLOR = Color.BISQUE;
    public static final Color SHARK_COLOR = Color.GREY;
    public static final int energyMax=10;
    public static final int sharkReproductionPeriod=10;
    public static final int fishReproductionPeriod=5;
    public static final int energyPerFish = 5;
    
    private int energy;
    private int fishReproduction; 
    private int sharkReproduction;
    
    private WatorCell(int state, Color color) {
        super(state, color);
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
        setNextState(WATER, WATER_COLOR);
    }
    
    public void toFish() {
        setNextState(FISH, FISH_COLOR);
    }
    
    public void toShark() {
        setNextState(SHARK, SHARK_COLOR);
    }
    
    public static WatorCell water() {
        return new WatorCell(WATER, WATER_COLOR);
    }
    
    public static WatorCell fish() {
        return new WatorCell(FISH, FISH_COLOR);
    }
    
    public static WatorCell shark() {
        return new WatorCell(SHARK, SHARK_COLOR);
    }
}
