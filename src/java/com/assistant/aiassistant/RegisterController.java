package com.assistant.aiassistant;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    public TextField usernameInput;
    @FXML
    public PasswordField passwordInput;
    @FXML
    public TextField emailInput;
    @FXML
    public TextField firstNameInput;
    @FXML
    public TextField lastNameInput;
    @FXML
    public VBox masterPane;
    @FXML
    public Label errorLabel;
    @FXML
    public Button loginButton;
    @FXML
    public Button registerButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        removeAutoFocusFromTextField(usernameInput);
    }
    private void removeAutoFocusFromTextField(TextField textField) {
        final BooleanProperty firstTime = new SimpleBooleanProperty(true);
        textField.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                masterPane.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });
    }

    @FXML
    private void showLoginScreen() {
        try {
            Main.showLoginScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
