package com.assistant.aiassistant;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public VBox masterPane;
    @FXML
    public Label errorLabel;
    @FXML
    public Label confirmationLabel;
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
        passwordVisibilityToggleSetup.execute(visiblePasswordInput, passwordInput, showPasswordCheckbox);
    }

    private void addLanguageOptionsToDropdownMenu() {
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
        // check of in alle input velden een waarde ingevoerd is, als dat zo is maak dan een account
        if(!areAllTextInputsValid()) {
            return;
        }

        loginManager.createAccount(usernameInput.getText(), passwordInput.getText(), emailInput.getText(), firstNameInput.getText(), lastNameInput.getText(), preferredLanguageChoiceBox.getValue().toString());
        loginManager.login(emailInput.getText(), passwordInput.getText());
        confirmationLabel.setText("Account created successfully. You can now log in.");
    }

    private boolean areAllTextInputsValid() {
        if(isTextfieldEmpty(usernameInput)) {
            errorLabel.setText("Enter Username");
            return false;
        }
        if(isTextfieldEmpty(passwordInput)) {
            errorLabel.setText("Enter Password");
            return false;
        }
        if(isTextfieldEmpty(emailInput)) {
            errorLabel.setText("Enter Email");
            return false;
        }
        if(isTextfieldEmpty(firstNameInput)) {
            errorLabel.setText("Enter First Name");
            return false;
        }
        if(isTextfieldEmpty(lastNameInput)) {
            errorLabel.setText("Enter Last Name");
            return false;
        }
        if(!isEmailValid(emailInput.getText())) {
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

    private boolean isTextfieldEmpty(TextField fieldToCheck) {
        return fieldToCheck.getText().equalsIgnoreCase("");
    }

    private boolean isEmailValid(String emailToCheck) {
        // checkt of een email adres valid is, met een regex / pattern
        // HIJ CHECKT:
        //   1 het email adres heeft alleen valid characters  voor de @ (alfanumerieke karakters, ! # & ' + = ? -  , zijn toegestaan)
        //   2 het kan (ook voor de @) een . hebben met nog meer tekst, die moet voldoen aan 1
        //   3 dan moet er een @ in zitten
        //   4 daarna moet het domeinnaam alleen bestaan uit letters en nummers, dan een . en daarna minimaal 2 letters en max 6.
        String regex = "^[\\w!#&'+=?-]+(?:\\.[\\w!#&'+=?-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(emailToCheck);
        return matcher.matches();
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
