package com.assistant.aiassistant;

import javafx.fxml.FXML;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import java.io.IOException;
import java.util.stream.Collectors;

public class SettingController {

    public Label showName;
    public TextField password;
    public TextField email;
    public TextField firstName;
    public TextField lastName;
    public ChoiceBox<String> preferredLanguage;
    public Label settingsLabel;
    public Label profileLabel;
    public Label profileLabel2;
    public Label firstNameLabel;
    public Label lastNameLabel;
    public Label passwordLabel;
    public Label emailLabel;
    public Label preferredLanguageLabel;
    public Button saveButton;
    public ImageView profileImageView;
    public Button logOutButton;
    public Label notification;
    public Label policy;
    private AccountManager accountManager;
    private ResourceBundle bundle;
    public ChangePersonalData changePersonalData;

    public void initialize() {
        changePersonalData = new ChangePersonalData();

        accountManager = AccountManager.getInstance();
        setUsername();
        addLanguageOptionsToDropdownMenu();
        loadResourceBundle();
        setLabelTexts();
    }

    public void loadResourceBundle() {
        Locale appLocale = new Locale(accountManager.getActiveUser().getPreferredLanguage());
        bundle = ResourceBundle.getBundle("MessageBundle", appLocale);
    }

    public void setLabelTexts() {
        settingsLabel.setText(bundle.getString("settings")); //Settings
        profileLabel.setText(bundle.getString("profile")); //Profile
        profileLabel2.setText(bundle.getString("profile")); //Profile
        firstNameLabel.setText(bundle.getString("firstName")); //Name
        lastNameLabel.setText(bundle.getString("lastName")); //Surname
        passwordLabel.setText(bundle.getString("password")); //Password
        emailLabel.setText(bundle.getString("email")); //Email
        preferredLanguageLabel.setText(bundle.getString("preferredLanguage")); //Preferred Language
        saveButton.setText(bundle.getString("save")); //Save
        logOutButton.setText(bundle.getString("logOut")); //Log Out
        notification.setText(bundle.getString("notification")); //Notification
        policy.setText(bundle.getString("policy")); //Policy
    }

    public void checkChangable() {
        User activeUser = accountManager.getActiveUser();
        Boolean hasChanged = false;

        if (notNull(firstName.getText())) {
            changePersonalData.changeFirstName(activeUser, firstName.getText());
            hasChanged = true;
        }
        if (notNull(lastName.getText())) {
            changePersonalData.changeLastName(activeUser, lastName.getText());
            hasChanged = true;
        }
        if (notNull(password.getText())) {
            changePersonalData.changePassword(activeUser, password.getText());
            hasChanged = true;
        }
        if (notNull(email.getText())) {
            changePersonalData.changeEmail(activeUser, email.getText());
            hasChanged = true;
        }
        if (preferredLanguage.getValue() != null) {
            changePersonalData.changePreferredLanguage(activeUser, preferredLanguage.getValue().toString());
            UserInterfaceManager.getInstance().updateLanguage(preferredLanguage.getValue().toString());
            hasChanged = true;
        }
        
        // Als er wijzigingen zijn, update de gebruiker
        if (hasChanged) {
            User updatedUser = new User(
                    activeUser.getUsername(),
                    password.getText().isEmpty() ? activeUser.getPassword() : password.getText(), // Gebruik het nieuwe wachtwoord als het niet leeg is
                    email.getText().isEmpty() ? activeUser.getEmail() : email.getText(), // Gebruik de nieuwe email als het niet leeg is
                    firstName.getText().isEmpty() ? activeUser.getFirstName() : firstName.getText(), // Gebruik de nieuwe voornaam als het niet leeg is
                    lastName.getText().isEmpty() ? activeUser.getLastName() : lastName.getText(), // Gebruik de nieuwe achternaam als het niet leeg is
                    preferredLanguage.getValue() == null ? activeUser.getPreferredLanguage() : preferredLanguage.getValue().toString() // Gebruik de nieuwe taal als het niet null is
            );

            accountManager.setActiveUser(updatedUser); // Update de actieve gebruiker in AccountManager

            reloadSettings(); // Herlaad de instellingen om de veranderingen door te voeren
        } else {
            System.out.println("No changes made");
        }
    }

    public void reloadSettings() {
        try {
            loadResourceBundle();
            setLabelTexts();
            UserInterfaceManager.getInstance().switchCurrentViewTo(UserInterfaceManager.getInstance().settingsViewFilename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addLanguageOptionsToDropdownMenu() {
        FileIOManager ioManager = new FileIOManager();
        List<String> languages = ioManager.getAvailableLanguages().stream().map(Language::toString).collect(Collectors.toList());
        preferredLanguage.getItems().addAll(languages);
//        preferredLanguage.getItems().addAll(ioManager.getAvailableLanguages());
    }

    @FXML
    void setUsername() {
       showName.setText(AccountManager.getInstance().getActiveUser().getUsername());
    }

    @FXML
    public void editImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Profile Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        File selectedFile = fileChooser.showOpenDialog(profileImageView.getScene().getWindow());
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            profileImageView.setImage(image);
        }
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

    public void logOut() throws IOException {
        UserInterfaceManager uiManager = UserInterfaceManager.getInstance();
        uiManager.switchCurrentViewTo(uiManager.loginViewFilename);
    }
}
