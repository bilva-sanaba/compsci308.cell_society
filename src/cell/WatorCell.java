package cell;

import javafx.scene.paint.Color;

import cellsociety.Cell;

/**
 * Cell for Wa-Tor simulation
 * @author liuzj
 *
 */
public class WatorCell extends Cell {

    public static final int WATER = 0;
    public static final int FISH = 1;
    public static final int SHARK = 2;
    
    private WatorCell(int state, Color color) {
        super(state, color);
    }
    
    public static WatorCell water() {
        return new WatorCell(WATER, Color.BLUE);
    }
    
    public static WatorCell fish() {
        return new WatorCell(FISH, Color.BISQUE);
    }
    
    public static WatorCell shark() {
        return new WatorCell(SHARK, Color.GREY);
    }
}
