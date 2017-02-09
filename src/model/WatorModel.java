package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import cell.WatorCell;
import cellsociety.Cell;
import cellsociety.Model;
import grid.CardinalRectangleGrid;
import util.CAData;

public class WatorModel extends Model {

    public static final String NAME = "wator";

    private Random rand = new Random();

    public WatorModel(CAData data) {
        super(new CardinalRectangleGrid(data.numRows(), data.numCols(), data.getCell(), WatorCell.getGenerator()));
    }
    private Cell pickRandomCell(Set<Cell> fish){
        int i = 0;
        if (fish.size()==0){
            return null;
        }
        int random = rand.nextInt(fish.size());
        for(Cell neighbor : fish)
        {
            if (i == random)
                return neighbor;
            i++;
        }
        return null;
    }
    private void fishToShark(WatorCell fish, WatorCell shark){
        if (fish!=null){
            fish.toShark();
            fish.setEnergy(shark.getEnergy()+WatorCell.FISH_ENERGY-1);
            fish.setSharkDays(shark.getSharkDays()-1);
            if (shark.canReproduce()){
                shark.setEnergy(WatorCell.ENERGY_MAX);
                fish.setSharkDays(WatorCell.SHARK_BREED_PERIOD);
                shark.setSharkDays(WatorCell.SHARK_BREED_PERIOD);
            }else{
                shark.toWater();
            }
        }
    }
    private void waterToShark(WatorCell water, WatorCell shark){
        if(water != null) {
            water.toShark();
            water.setEnergy(shark.getEnergy()-1);
            water.setSharkDays(shark.getSharkDays()-1);
            if (shark.canReproduce()){
                shark.setEnergy(WatorCell.ENERGY_MAX);
                shark.setSharkDays(WatorCell.SHARK_BREED_PERIOD);
                water.setSharkDays(WatorCell.SHARK_BREED_PERIOD);
            }else{
                shark.toWater();
            }
        }
    }
    private void sharkStays(WatorCell cell){
        cell.setSharkDays(cell.getSharkDays()-1);
        cell.setEnergy(cell.getEnergy()-1);	
    }
    private List<WatorCell> getFishCells(){
        ArrayList<WatorCell> fishes = new ArrayList<WatorCell>();
        for(int row = 0; row < getGrid().numRows(); row++) {
            for(int col = 0; col < getGrid().numCols(); col++) {
                if (getGrid().get(row, col).is(WatorCell.FISH)){
                    fishes.add((WatorCell)getGrid().get(row, col));
                }
            }
        }
        return fishes;	
    }
    private List<WatorCell> getSharkCells(){
        ArrayList<WatorCell> sharks = new ArrayList<WatorCell>();
        for(int row = 0; row < getGrid().numRows(); row++) {
            for(int col = 0; col < getGrid().numCols(); col++) {
                if (getGrid().get(row, col).is(WatorCell.SHARK)){
                    sharks.add((WatorCell)getGrid().get(row, col));
                }
            }
        }
        return sharks;	
    }


    private void moveShark(WatorCell cell){
        if (cell.is(WatorCell.SHARK)){
            if (cell.shouldDie()){
                cell.toWater();
            }else{
                Set<Cell> fish = cell.getCertainNeighbors(WatorCell.FISH);
                Set<Cell> water = cell.getCertainNeighbors(WatorCell.WATER);
                WatorCell randomFish = (WatorCell) pickRandomCell(fish);
                WatorCell randomWater = (WatorCell) pickRandomCell(water);
                fishToShark(randomFish,cell);
                if (randomFish==null){
                    waterToShark(randomWater, cell);
                }
                if (randomFish==null && randomWater==null){
                    sharkStays(cell);
                }		
            }
        }
    }
    private void moveFish(WatorCell cell){
        if (cell.is(WatorCell.FISH)){
            Set<Cell> water = new HashSet<Cell>();
            for (Cell neighbor : cell.getNeighbors()){
                if(neighbor.is(WatorCell.WATER)) {
                    water.add(neighbor);
                }
            }

            WatorCell randomWater = (WatorCell) pickRandomCell(water);
            if(randomWater != null) {
                randomWater.toFish();
                randomWater.setFishDays(cell.getFishDays()-1);
                if (cell.canReproduce()){
                    cell.setFishDays(WatorCell.FISH_BREED_PERIOD);
                    randomWater.setFishDays(WatorCell.FISH_BREED_PERIOD);

                }else{
                    cell.toWater();

                }
            }else{
                cell.setFishDays(cell.getFishDays()-1);
            }  
        }
    }

    @Override
    public void update() {


        for (WatorCell shark : getSharkCells()){
            moveShark(shark);
            getGrid().update();
        }
        for(WatorCell fish : getFishCells()){
            moveFish(fish);
            getGrid().update();
        }


    }

}
