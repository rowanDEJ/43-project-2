package com.assistant.aiassistant;

import java.util.ArrayList;

// De meeste van de souts kunnen worden weggehaald als hij aan de applicatie wordt gelinkt
public class ChangePersonalData {

    public FileIOManager fileIOManager = new FileIOManager();
    public ArrayList<User> users = new ArrayList<>();

    public void changeFirstName(User selectedUser, String newFirstName) {
        fileIOManager.editUser(selectedUser, newFirstName, "firstname");
    }

    public void changeLastName(User selectedUser, String newLastName) {
        fileIOManager.editUser(selectedUser, newLastName, "lastname");
    }

    public void changePassword(User selectedUser, String newPassword) {

        fileIOManager.editUser(selectedUser, newPassword, "password");
    }

    public void changeEmail(User selectedUser, String newEmail) {

        // Miss nog methode om email te checken of hij al in gebruik is
        if (checkEmail(newEmail)) {
            fileIOManager.editUser(selectedUser, newEmail, "email");
        }
    }

    // Hier misschien handig om opties te geven in welke taal de gebruiker zijn voorkeur heeft
    // Deze methode moet nog worden gebruikt als de dropdown bar werkt op de settings pagina
    public void changePreferredLanguage(User selectedUser, String newPreferredLanguage) {
        fileIOManager.editUser(selectedUser, newPreferredLanguage, "preferredLanguage");
    }

    // Misschien deze methode verplaatsen naar een andere class
    public boolean checkEmail(String newEmail) {
        users = fileIOManager.getUsersFromFile();

        for (User u : users) {
            if (u.getEmail().equals(newEmail)) {

                return false;
            }
        }
        return true;
    }
}
