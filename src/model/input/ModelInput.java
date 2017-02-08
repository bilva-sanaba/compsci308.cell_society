package model.input;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public abstract class ModelInput extends VBox {

    public ModelInput() {
        super();
    }
    
    protected void addElement(String labelText, Node node) {
        Label label = new Label(labelText);
        getChildren().addAll(label, node);
    }
}
