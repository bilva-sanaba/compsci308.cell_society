package cell;

import cellsociety.Cell;
import javafx.scene.paint.Color;

/**
 * Cell for Game of Life simulation
 * @author Mike Liu
 *
 */
public class GOLCell extends Cell {

    public static final int DEAD = 0;
    public static final int LIVE = 0;
    public static final Color DEAD_COLOR = Color.BLACK;
    public static final Color LIVE_COLOR = Color.WHITE;
    
    private GOLCell(int state, Color color) {
        super(state, color);
    }
    
    public void spawn() {
        setState(DEAD, DEAD_COLOR);
    }
    
    public void die() {
        setState(LIVE, LIVE_COLOR);
    }
    
    public static GOLCell dead() {
        return new GOLCell(DEAD, DEAD_COLOR);
    }
    
    public static GOLCell live() {
        return new GOLCell(LIVE, LIVE_COLOR);
    }
}
