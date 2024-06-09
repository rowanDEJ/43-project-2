package com.assistant.aiassistant;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    public TextField usernameInput;
    @FXML
    public TextField passwordInput;
    @FXML
    public TextField visiblePasswordInput;
    @FXML
    public TextField emailInput;
    @FXML
    public TextField firstNameInput;
    @FXML
    public TextField lastNameInput;
    @FXML
    public HBox masterPane;
    @FXML
    public Label errorLabel;
    @FXML
    public Button loginButton;
    @FXML
    public Button registerButton;
    @FXML
    public ComboBox<Language> preferredLanguageChoiceBox;
    @FXML
    public CheckBox showPasswordCheckbox;

    private final AccountManager loginManager = AccountManager.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        removeAutoFocusFromTextField(usernameInput);
        addLanguageOptionsToDropdownMenu();
        PasswordVisibilityToggleSetup.execute(visiblePasswordInput, passwordInput, showPasswordCheckbox);
    }

    public void addLanguageOptionsToDropdownMenu() {
        FileIOManager ioManager = new FileIOManager();
        preferredLanguageChoiceBox.getItems().addAll(ioManager.getAvailableLanguages());
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
        // check of in alle input velden een waarde ingevoerd is, als dat zo is, maak dan een account
        if(!areAllTextInputsValid()) {
            return;
        }

        loginManager.createAccount(usernameInput.getText(), passwordInput.getText(), emailInput.getText(), firstNameInput.getText(), lastNameInput.getText(), preferredLanguageChoiceBox.getValue().toString());
        loginManager.login(emailInput.getText(), passwordInput.getText());
        errorLabel.getStyleClass().removeAll();
        errorLabel.getStyleClass().add("confirmationLabel");
        errorLabel.setText("Account gecreëerd. Je kan nu inloggen.");
    }

    private boolean areAllTextInputsValid() {
        ArrayList<TextField> inputs = new ArrayList<>(List.of(usernameInput, passwordInput, emailInput, firstNameInput, lastNameInput));
        TextField firstEmptyTextField = InputValidator.findFirstEmptyTextField(inputs);
        errorLabel.getStyleClass().removeAll();
        errorLabel.getStyleClass().add("errorLabel");

        if(firstEmptyTextField != null) {
            errorLabel.setText("Voer " + firstEmptyTextField.getPromptText() + " in.");
            return false;
        }

        TextField firstTextfieldWithTooManyCharacters = InputValidator.findFirstTextFieldWithTooManyCharacters(inputs);
        if(firstTextfieldWithTooManyCharacters != null) {
            errorLabel.setText(firstTextfieldWithTooManyCharacters.getPromptText() + " has too many characters (" + firstTextfieldWithTooManyCharacters.getText().length() + "/" + InputValidator.maxInputLength + ")");
            return false;
        }


        if(InputValidator.isInvalidEmail(emailInput.getText())) {
            errorLabel.setText("Enter a valid email address");
            return false;
        }
        if(preferredLanguageChoiceBox.getValue() == null) {
            errorLabel.setText("Choose a preferred language");
            return false;
        }
        if(loginManager.checkIfUserWithEmailExists(emailInput.getText())) {
            errorLabel.setText("Account with this email already exists.");
            return false;
        }
        if(loginManager.checkIfUserWithUsernameExists(usernameInput.getText())) {
            errorLabel.setText("Account with this username already exists.");
            return false;
        }
        if(loginManager.checkIfUserWithFullNameExists(firstNameInput.getText() + " " + lastNameInput.getText())) {
            errorLabel.setText("Account with this name already exists.");
            return false;
        }
        return true;
    }

    @FXML
    private void showLoginScreen() {
        try {
            UserInterfaceManager uiManager = UserInterfaceManager.getInstance();
            uiManager.switchCurrentViewTo(uiManager.loginViewFilename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
