package com.assistant.aiassistant;

import java.util.Scanner;


// zorgt ervoor dat de user kan in- en uitloggen
public class Security {
    private static Security instance = null;
    private User activeUser;
    FileIOManager fileManager = new FileIOManager();


    private Security() {
        setUserWithUsername(null); // null betekent dat er niemand is ingelogd
    }

    // als er nog geen instance is, wordt er een aangemaakt
    public static Security getInstance() {
        if (instance == null) {
            instance = new Security();
        }
        return instance;
    }

    private void setUserWithUsername(String userName) {
        // als de gebruikersnaam gelijk is aan de gebruikersnaam in het bestand, wordt de gebruiker actief
        fileManager.getUsersFromFile().forEach(user -> {
            if (user.getUsername().equals(userName)) {
                this.activeUser = user;
            }
        });
    }

    private void setUserWithEmail(String email) {
        // als de email gelijk is aan de email in het bestand, wordt de gebruiker actief
        fileManager.getUsersFromFile().forEach(user -> {
            if (user.getEmail().equals(email)) {
                this.activeUser = user;
            }
        });
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void logout() {
        setUserWithUsername(null);
    }

    // is er ingelogd of niet?
    public boolean isLoggedIn() {
        return getActiveUser() != null;
    }

    private boolean checkUsernamePassword(String userName, String password) {
        // Check alle gebruikers in het bestand, als de gebruikersnaam gelijk is aan het wachtwoord, return true
        for (User user : fileManager.getUsersFromFile()) {
            if (user.getUsername().equals(userName) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkEmailPassword(String email, String password) {
        // Check alle gebruikers in het bestand, als de gebruikersnaam gelijk is aan het wachtwoord, return true
        for (User user : fileManager.getUsersFromFile()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    private User getUserFromEmail(String emailToSearch) {
        for (User user : fileManager.getUsersFromFile()) {
            if (user.getEmail().equals(emailToSearch)) {
                return user;
            }
        }
        return null;
    }


    public boolean login(String email, String password) {
        if (checkEmailPassword(email, password)) {
            setUserWithEmail(email);
            return true;
        } else {
            return false;
        }
    }
}


