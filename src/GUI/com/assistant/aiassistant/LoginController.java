package com.assistant.aiassistant;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
    @FXML
    public TextField visiblePasswordInput;
    @FXML
    public CheckBox showPasswordCheckbox;

    private final AccountManager loginManager = AccountManager.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        removeAutoFocusFromTextField(emailInput);
        passwordVisibilityToggleSetup.execute(visiblePasswordInput, passwordInput, showPasswordCheckbox);
    }

    private void removeAutoFocusFromTextField(TextField textField) {
        final BooleanProperty firstTime = new SimpleBooleanProperty(true);
        textField.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                masterPane.requestFocus();
                firstTime.setValue(false);
            }
        });
    }

    public void tryLogin() {
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
                UserInterfaceManager uiManager = UserInterfaceManager.getInstance();
                uiManager.switchCurrentViewTo(uiManager.mainViewFilename);
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
            UserInterfaceManager uiManager = UserInterfaceManager.getInstance();
            uiManager.switchCurrentViewTo(uiManager.registerViewFilename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
