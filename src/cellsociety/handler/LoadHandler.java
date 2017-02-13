package cellsociety.handler;

import javafx.scene.layout.Region;

/**
 * Event Handler that allows the GUI module to set model input
 * @author Mike Liu
 *
 */
public interface LoadHandler {

    /**
     * Sets the model input to root
     * @param root
     */
    public void setModelInput(Region root);
}
