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
    
    private WatorCell(CellState state) {
        super(state);
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
