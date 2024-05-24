package com.assistant.aiassistant;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    public TextField emailInput;
    @FXML
    public TextField passwordInput;

    @FXML
    public VBox masterPane;

    @FXML
    public Button loginButton;
    @FXML
    public Button registerButton;
    @FXML
    public Label errorLabel;

    private Security loginManager = Security.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        removeAutoFocusFromTextField(emailInput);
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

    public void tryLogin(ActionEvent actionEvent) {
        if(emailInput.getText().equalsIgnoreCase("")) {
            errorLabel.setText("Enter Email Address.");
            return;
        }

        if(passwordInput.getText().equalsIgnoreCase("")) {
            errorLabel.setText("Enter Password.");
            return;
        }

        if(loginManager.login(emailInput.getText(), passwordInput.getText())) {
            try {
                Main.showMainScreen();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            errorLabel.setText("Invalid Credentials");
        }
    }

    @FXML
    private void showRegisterScreen() {
        try {
            Main.showRegisterScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
