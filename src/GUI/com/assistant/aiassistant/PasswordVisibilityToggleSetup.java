package com.assistant.aiassistant;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class PasswordVisibilityToggleSetup {

    private PasswordVisibilityToggleSetup() {
        throw new IllegalStateException("Utility class");
    }

    public static void execute(TextField visiblePasswordField, TextField obscuredPasswordField, CheckBox checkBox) {
        visiblePasswordField.setManaged(false);
        visiblePasswordField.setVisible(false);

        visiblePasswordField.managedProperty().bind(checkBox.selectedProperty());
        visiblePasswordField.visibleProperty().bind(checkBox.selectedProperty());

        obscuredPasswordField.managedProperty().bind(checkBox.selectedProperty().not());
        obscuredPasswordField.visibleProperty().bind(checkBox.selectedProperty().not());

        visiblePasswordField.textProperty().bindBidirectional(obscuredPasswordField.textProperty());
    }
}
