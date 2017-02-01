package cell;

import cellsociety.Cell;
import javafx.scene.paint.Color;

public class GOLCell extends Cell {

    public static final int DEAD = 0;
    public static final int LIVE = 0;
    
    private GOLCell(int state, Color color) {
        super(state, color);
    }
    
    public static GOLCell dead() {
        return new GOLCell(DEAD, Color.BLACK);
    }
    
    public static GOLCell live() {
        return new GOLCell(DEAD, Color.WHITE);
    }
}
