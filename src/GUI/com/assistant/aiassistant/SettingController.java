package com.assistant.aiassistant;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Locale;
import java.util.ResourceBundle;

public class SettingController {
    @FXML
    public TextField password;
    public CheckBox buttonPassword;
    public TextField email;
    public CheckBox buttonEmail;
    public TextField firstName;
    public CheckBox buttonFirstName;
    public TextField lastName;
    public CheckBox buttonLastName;
//    public ChoiceBox preferredLanguage;
//    public CheckBox buttonPreferredLanguage;

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

        if (buttonFirstName.isSelected() && notNull(firstName.getText())) {
            changePersonalData.changeFirstName(activeUser, firstName.getText());
        }
        if (buttonLastName.isSelected() && notNull(lastName.getText())) {
            changePersonalData.changeLastName(activeUser, lastName.getText());
        }
        if (buttonPassword.isSelected() && notNull(password.getText())) {
            changePersonalData.changePassword(activeUser, password.getText());
        }
        if (buttonEmail.isSelected() && notNull(email.getText())) {
            changePersonalData.changeEmail(activeUser, email.getText());
        }
//        if (buttonPreferredLanguage.isSelected()) {
//            changePersonalData.changePreferredLanguage(activeUser, preferredLanguage.getValue().toString());
//        }
    }

    private boolean notNull(String text) {
        return text != null && !text.isEmpty();
    }


}
