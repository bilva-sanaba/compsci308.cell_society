package model;

import java.util.ArrayList;
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
                if ((getGrid().get(row, col)).is(WatorState.FISH)){
                    WatorCell currentCell = (WatorCell)getGrid().get(row, col);
                    currentCell.setFishDays(fishBreedPeriod);
                }else{
                    if (getGrid().get(row, col).is(WatorState.SHARK)){
                        WatorCell currentCell = (WatorCell) getGrid().get(row, col);
                        currentCell.setFishDays(sharkBreedPeriod);
                    }
                }
            }
        }
    }
    @Override
    public void update() {
        for (Cell shark : getGrid().getCells(WatorState.SHARK)){
            moveShark((WatorCell) shark);
            getGrid().update();
        }
        for(Cell fish : getGrid().getCells(WatorState.FISH)){
            moveFish((WatorCell) fish);
            getGrid().update();
        }
    }
    private void sharkMovement(WatorCell notShark, WatorCell shark){
        if (notShark!=null){
            notShark.toShark();
            if (notShark.is(WatorState.FISH)){
                notShark.setEnergy(shark.getEnergy()+fishEnergy-1);
            }else{
                notShark.setEnergy(shark.getEnergy()-1);
            }
            notShark.setSharkDays(shark.getSharkDays()-1);
            handleReproduction(notShark,shark);
        }

    }
    //	private void fishToShark(WatorCell fish, WatorCell shark){
    //		if (fish!=null){
    //			fish.toShark();
    //			fish.setEnergy(shark.getEnergy()+fishEnergy-1);
    //			fish.setSharkDays(shark.getSharkDays()-1);
    //			handleReproduction(fish,shark);
    //		}
    //	}
    private void handleReproduction(WatorCell notShark, WatorCell shark){
        if (shark.canReproduce()){
            shark.setEnergy(energyMax);
            shark.setSharkDays(sharkBreedPeriod);
            notShark.setSharkDays(sharkBreedPeriod);
        }else{
            shark.toWater();
        }
    }
    //	private void waterToShark(WatorCell water, WatorCell shark){
    //		 if(water != null) {
    //             water.toShark();
    //             water.setEnergy(shark.getEnergy()-1);
    //             water.setSharkDays(shark.getSharkDays()-1);
    //             handleReproduction(water,shark);
    //         }
    //	}
    private void sharkStays(WatorCell cell){
        cell.setSharkDays(cell.getSharkDays()-1);
        cell.setEnergy(cell.getEnergy()-1);	
    }

    private void moveShark(WatorCell cell){
        if (cell.is(WatorState.SHARK)){
            if (cell.shouldDie()){
                cell.toWater();
            }else{
                WatorCell randomFish = (WatorCell) pickRandomCell(cell.getCertainNeighbors(WatorState.FISH));
                WatorCell randomWater = (WatorCell) pickRandomCell(cell.getCertainNeighbors(WatorState.WATER));
                sharkMovement(randomFish,cell);
                if (randomFish==null){
                    sharkMovement(randomWater, cell);
                }
                if (randomFish==null && randomWater==null){
                    sharkStays(cell);
                }		
            }
        }
    }
    private void moveFish(WatorCell cell){
        if (cell.is(WatorState.FISH)){
            List<Cell> water = new ArrayList<Cell>();
            for (Cell neighbor : cell.getNeighbors()){
                if(neighbor.is(WatorState.WATER)) {
                    water.add(neighbor);
                }
            }

            WatorCell randomWater = (WatorCell) pickRandomCell(water);
            if(randomWater != null) {
                randomWater.toFish();
                randomWater.setFishDays(cell.getFishDays()-1);
                if (cell.canReproduce()){
                    cell.setFishDays(fishBreedPeriod);
                    randomWater.setFishDays(fishBreedPeriod);

                }else{
                    cell.toWater();

                }
            }else{
                cell.setFishDays(cell.getFishDays()-1);
            }  
        }
    }
}
