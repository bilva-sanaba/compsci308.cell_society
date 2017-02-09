package cell;

import cellsociety.CAException;
import cellsociety.Cell;
import javafx.scene.paint.Color;

public class SegregationCell extends Cell{
    
    public static final CellState EMPTY = new CellState("Empty", Color.WHITE);
    public static final CellState RED = new CellState("Red", Color.RED);
    public static final CellState BLUE = new CellState("Blue", Color.BLUE);
    
    private SegregationCell(CellState state) {
        super(state);
    }
    
    public void leave() {
    	setNextState(EMPTY);
    }
    
    public void fillRed(){
    	setNextState(RED);
    }
    
    public void fillBlue(){
    	setNextState(BLUE);
    }
    
    public static CellGenerator getGenerator() {
        return new CellGenerator() {

            @Override
            public Cell getBasicCell() {
                return new SegregationCell(EMPTY);
            }

            @Override
            public Cell getCell(int state) {
                if(EMPTY.equals(state)) {
                    return new SegregationCell(EMPTY);
                }
                else if(RED.equals(state)) {
                    return new SegregationCell(RED);
                }
                else if(BLUE.equals(state)) {
                    return new SegregationCell(BLUE);
                }
                throw new CAException(CAException.INVALID_CELL, "segregation");
            }
        };
    }
}

