package cell;

import cellsociety.Cell;
import javafx.scene.paint.Color;

public class SegregationCell extends Cell{
	public static final int EMPTY = 0;
    public static final int RED = 1;
    public static final int BLUE = 2;
    public static final Color RED_COLOR = Color.RED;
    public static final Color BLUE_COLOR = Color.BLUE;
    public static final Color EMPTY_COLOR = Color.WHITE;
    
    private SegregationCell(int state, Color color) {
        super(state, color);
    }
    public void leave() {
    	setNextState(EMPTY,EMPTY_COLOR);
    }
    public void fillRed(){
    	setNextState(RED,RED_COLOR);
    }
    public void fillBlue(){
    	setNextState(BLUE,BLUE_COLOR);
    }
 
    public static SegregationCell red() {
        return new SegregationCell(RED, RED_COLOR);
    }
    
    public static SegregationCell blue() {
        return new SegregationCell(BLUE, BLUE_COLOR);
    }
    public static SegregationCell empty() {
        return new SegregationCell(EMPTY, EMPTY_COLOR);
    }
}

