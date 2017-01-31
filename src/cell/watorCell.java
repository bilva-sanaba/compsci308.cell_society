package cell;

import javafx.scene.paint.Color;

import cellsociety.Cell;

/**
 * Cell for Wa-Tor simulation
 * @author liuzj
 *
 */
public class watorCell extends Cell {

    public static final int WATER = 0;
    public static final int FISH = 1;
    public static final int SHARK = 2;
    
    private watorCell(int state, Color color) {
        super(state, color);
    }
    
    public static watorCell water() {
        return new watorCell(WATER, Color.BLUE);
    }
    
    public static watorCell fish() {
        return new watorCell(FISH, Color.BISQUE);
    }
    
    public static watorCell shark() {
        return new watorCell(SHARK, Color.GREY);
    }
}
