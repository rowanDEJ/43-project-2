package com.assistant.aiassistant;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    public TextField emailInput;
    @FXML
    public TextField passwordInput;
    @FXML
    public HBox masterPane;
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
        PasswordVisibilityToggleSetup.execute(visiblePasswordInput, passwordInput, showPasswordCheckbox);
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
        errorLabel.getStyleClass().removeAll();
        errorLabel.getStyleClass().add("errorLabel");

        ArrayList<TextField> inputs = new ArrayList<>(List.of(emailInput, passwordInput));
        TextField firstEmptyTextField = InputValidator.findFirstEmptyTextField(inputs);
        if(firstEmptyTextField != null) {
            errorLabel.setText("Voer " + firstEmptyTextField.getPromptText() + " in.");
            return;
        }

        TextField firstTextfieldWithTooManyCharacters = InputValidator.findFirstTextFieldWithTooManyCharacters(inputs);
        if(firstTextfieldWithTooManyCharacters != null) {
            errorLabel.setText(firstTextfieldWithTooManyCharacters.getPromptText() + " te lang (" + firstTextfieldWithTooManyCharacters.getText().length() + "/" + InputValidator.maxInputLength + ")");
            return;
        }

        if(InputValidator.isInvalidEmail(emailInput.getText())) {
            errorLabel.setText("Ongeldig email adres.");
            return;
        }

        if(loginManager.login(emailInput.getText(), passwordInput.getText())) {
            try {
                saveUser();
                UserInterfaceManager uiManager = UserInterfaceManager.getInstance();
                uiManager.switchCurrentViewTo(uiManager.mainViewFilename);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            errorLabel.setText("ongeldige inlog gegevens");
        }
    }

    public void saveUser() {
        User activeUser = loginManager.getActiveUser();
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
