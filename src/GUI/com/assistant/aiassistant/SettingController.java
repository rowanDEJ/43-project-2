package com.assistant.aiassistant;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

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

    public ChangePersonalData changePersonalData;
    public AccountManager accountManager;

    public void initialize() {
        changePersonalData = new ChangePersonalData();
        accountManager = AccountManager.getInstance();
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
