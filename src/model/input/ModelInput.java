package model.input;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public abstract class ModelInput {

    private VBox myBox;
    
    public ModelInput() {
        myBox = new VBox();
    }
    
    public Region getRoot() {
        return myBox;
    }
    
    protected void addElement(String labelText, Node node) {
        Label label = new Label(labelText);
        myBox.getChildren().addAll(label, node);
    }
    
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
