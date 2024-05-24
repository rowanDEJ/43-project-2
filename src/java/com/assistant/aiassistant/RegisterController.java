package com.assistant.aiassistant;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    public TextField usernameInput;
    @FXML
    public TextField passwordInput;
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
    @FXML
    public TextField preferredLanguageInput;

    private final Security loginManager = Security.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        removeAutoFocusFromTextField(usernameInput);
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

    @FXML
    private void tryRegistering() {
        if(isTextfieldEmpty(usernameInput)) {
            errorLabel.setText("Enter Username");
            return;
        }
        if(isTextfieldEmpty(passwordInput)) {
            errorLabel.setText("Enter Password");
            return;
        }
        if(isTextfieldEmpty(emailInput)) {
            errorLabel.setText("Enter Email");
            return;
        }
        if(isTextfieldEmpty(firstNameInput)) {
            errorLabel.setText("Enter First Name");
            return;
        }
        if(isTextfieldEmpty(lastNameInput)) {
            errorLabel.setText("Enter Last Name");
            return;
        }
        if(isTextfieldEmpty(preferredLanguageInput)) {
            errorLabel.setText("Enter preferred language");
            return;
        }
        if(loginManager.checkIfUserWithEmailExists(emailInput.getText())) {
            errorLabel.setText("Account with this email already exists.");
            return;
        }
        if(loginManager.checkIfUserWithUsernameExists(usernameInput.getText())) {
            errorLabel.setText("Account with this username already exists.");
            return;
        }
        if(loginManager.checkIfUserWithFullNameExists(firstNameInput.getText() + " " + lastNameInput.getText())) {
            errorLabel.setText("Account with this name already exists.");
            return;
        }
        loginManager.createAccount(usernameInput.getText(), passwordInput.getText(), emailInput.getText(), firstNameInput.getText(), lastNameInput.getText(), preferredLanguageInput.getText());
        loginManager.login(emailInput.getText(), passwordInput.getText());
        errorLabel.setText("Account created successfully. You can now log in.");
    }
    private boolean isTextfieldEmpty(TextField fieldToCheck) {
        return fieldToCheck.getText().equalsIgnoreCase("");
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
