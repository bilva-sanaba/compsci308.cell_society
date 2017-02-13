package cellsociety.model.input;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Secondary user interface that allows user to set the parameters specific to models
 * @author Mike Liu
 *
 */
public abstract class ModelInput {

    private VBox myBox;
    
    public ModelInput() {
        myBox = new VBox();
    }
    
    public Region getRoot() {
        return myBox;
    }
    
    /**
     * Adds an element
     * Helper method uesed by subclasses
     * @param labelText
     * @param node
     */
    protected void addElement(String labelText, Node node) {
        Label label = new Label(labelText);
        myBox.getChildren().addAll(label, node);
    }
    
    /**
     * Adds a number field
     * Helper method uesed by subclasses
     * @param labelText
     * @param defaultValue
     * @param setter
     */
    protected void addNumberField(String labelText, int defaultValue, ValueSetter setter) {
        TextField textField = new TextField(Integer.toString(defaultValue));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        textField.setOnAction(e -> {
            setter.set(Integer.parseInt(textField.getText()));
        });
        addElement(labelText, textField);
    }
}
