package cell;

import cellsociety.CAException;
import cellsociety.Cell;
import javafx.scene.paint.Color;

/**
 * Cell for Game of Life simulation
 * @author Mike Liu
 *
 */
public class GOLCell extends Cell {
    
    public static final CellState DEAD = new CellState(0, Color.BLACK);
    public static final CellState LIVE = new CellState(1, Color.WHITE);
    
    private GOLCell(CellState state) {
        super(state);
    }
    
    public void spawn() {
        setNextState(LIVE);
    }
    
    public void die() {
        setNextState(DEAD);
    }
    
    public static CellGenerator getGenerator() {
        return new CellGenerator() {

            @Override
            public Cell getBasicCell() {
                return new GOLCell(DEAD);
            }

            @Override
            public Cell getCell(int state) {
                if(DEAD.equals(state)) {
                    return new GOLCell(DEAD);
                }
                else if(LIVE.equals(state)) {
                    return new GOLCell(LIVE);
                }
                throw new CAException(CAException.INVALID_CELL, "Game of Life");
            }
        };
    }
}
