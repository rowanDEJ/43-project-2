package com.assistant.aiassistant;

import java.util.ArrayList;

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
        if (!checkEmail(newEmail)) {
            throw new IllegalArgumentException("Email already in use");
        }
        fileIOManager.editUser(selectedUser, newEmail, "email");
    }

    public void changePreferredLanguage(User selectedUser, String newPreferredLanguage) {
        fileIOManager.editUser(selectedUser, newPreferredLanguage, "preferredLanguage");
    }

    public void changeAiLanguage(User selectedUser, String newAiLanguage) {
        fileIOManager.editUser(selectedUser, newAiLanguage, "aiLanguage");
    }

    public boolean checkEmail(String newEmail) {
        users = fileIOManager.getUsersFromFile();
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(newEmail)) {
                return false;
            }
        }
        return true;
    }
}
