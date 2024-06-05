package com.assistant.aiassistant;

import javafx.fxml.FXML;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.control.*;

import java.util.Locale;
import java.util.ResourceBundle;

import java.io.IOException;

public class SettingController {
    @FXML
    public Label showName;
    public TextField password;
    public TextField email;
    public TextField firstName;
    public TextField lastName;
    public ChoiceBox preferredLanguage;

    public Label settingsLabel;
    public Label profileLabel;
    public Label profileLabel2;
    public Label firstNameLabel;
    public Label lastNameLabel;
    public Label passwordLabel;
    public Label emailLabel;
    public Label preferredLanguageLabel;
    public Button saveButton;

    public AccountManager accountManager = AccountManager.getInstance();
    public Locale appLocale = new Locale(accountManager.getActiveUser().getPreferredLanguage());
    ResourceBundle bundle = ResourceBundle.getBundle("MessageBundle", appLocale);

    public ChangePersonalData changePersonalData;


    public void initialize() {
        changePersonalData = new ChangePersonalData();

        accountManager = AccountManager.getInstance();
        setUsername();
        addLanguageOptionsToDropdownMenu();

        settingsLabel.setText(bundle.getString("settings")); //Settings
        profileLabel.setText(bundle.getString("profile")); //Profile
        profileLabel2.setText(bundle.getString("profile")); //Profile
        firstNameLabel.setText(bundle.getString("firstName")); //Name
        lastNameLabel.setText(bundle.getString("lastName")); //Surname
        passwordLabel.setText(bundle.getString("password")); //Password
        emailLabel.setText(bundle.getString("email")); //Email
        preferredLanguageLabel.setText(bundle.getString("preferredLanguage")); //Preferred Language
        saveButton.setText(bundle.getString("save")); //Save
    }

    public void checkChangable() {
        User activeUser = accountManager.getActiveUser();

        if (notNull(firstName.getText())) {
            changePersonalData.changeFirstName(activeUser, firstName.getText());
        }
        if (notNull(lastName.getText())) {
            changePersonalData.changeLastName(activeUser, lastName.getText());
        }
        if (notNull(password.getText())) {
            changePersonalData.changePassword(activeUser, password.getText());
        }
        if (notNull(email.getText())) {
            changePersonalData.changeEmail(activeUser, email.getText());
        }
        if (notNull(preferredLanguage.getValue().toString())) {
            changePersonalData.changePreferredLanguage(activeUser, preferredLanguage.getValue().toString());
        }
    }

    public void addLanguageOptionsToDropdownMenu() {
        FileIOManager ioManager = new FileIOManager();
        preferredLanguage.getItems().addAll(ioManager.getAvailableLanguages());
    }

    @FXML
    void setUsername() {
       showName.setText(AccountManager.getInstance().getActiveUser().getUsername());
    }

    @FXML
    private void editImage() {

    }

    @FXML
    private void returnToChatGUI() {
        try {
            UserInterfaceManager uiManager = UserInterfaceManager.getInstance();
            uiManager.switchCurrentViewTo(uiManager.mainViewFilename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean notNull(String text) {
        return text != null && !text.isEmpty();
    }
}
