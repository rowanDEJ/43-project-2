package com.assistant.aiassistant;

import java.util.ArrayList;

public class AccountManager {
    private static AccountManager instance = null;
    private User activeUser;
    FileIOManager fileManager = new FileIOManager();

    private AccountManager() {
        setUserWithUsername(null); // null betekent dat er niemand is ingelogd
    }

    public static AccountManager getInstance() {
        if (instance == null) {
            instance = new AccountManager();
        }
        return instance;
    }

    protected void setUserWithUsername(String userName) {
        boolean userFound = false;
        for (User user : fileManager.getUsersFromFile()) {
            if (user.getUsername().equals(userName)) {
                this.activeUser = user;
                userFound = true;
                break;
            }
        }
        if (!userFound) {
            this.activeUser = null;
        }
    }

    private void setUserWithEmail(String email) {
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

    public boolean isLoggedIn() {
        return getActiveUser() != null;
    }

    protected boolean checkUsernamePassword(String userName, String password) {
        for (User user : fileManager.getUsersFromFile()) {
            //System.out.println("Checking username: " + user.getUsername() + " with password: " + user.getPassword());
            if (user.getUsername().equals(userName) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    protected boolean checkEmailPassword(String email, String password) {
        for (User user : fileManager.getUsersFromFile()) {
            //System.out.println("Checking email: " + user.getEmail() + " with password: " + user.getPassword());
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfUserWithEmailExists(String emailToSearch) {
        for (User user : fileManager.getUsersFromFile()) {
            System.out.println("Checking email: " + user.getEmail() + " against: " + emailToSearch);
            if (user.getEmail().equalsIgnoreCase(emailToSearch)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfUserWithUsernameExists(String usernameToSearch) {
        for (User user : fileManager.getUsersFromFile()) {
            //System.out.println("Checking username: " + user.getUsername() + " against: " + usernameToSearch);
            if (user.getUsername().equalsIgnoreCase(usernameToSearch)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfUserWithFullNameExists(String fullNameToSearch) {
        for (User user : fileManager.getUsersFromFile()) {
            //System.out.println("Checking full name: " + user.getFullName() + " against: " + fullNameToSearch);
            if (user.getFullName().equalsIgnoreCase(fullNameToSearch)) {
                return true;
            }
        }
        return false;
    }

    public void createAccount(String username, String password, String email, String fname, String lname, String preferredlanguage, String aiLanguage) {
        if (checkIfUserWithUsernameExists(username)) {
            System.out.println("Gebruikersnaam bestaat al: " + username);
            return;
        }
        User newlyCreatedUser = new User(username, password, email, fname, lname, preferredlanguage, aiLanguage);
        fileManager.saveUserToFile(newlyCreatedUser);
        ArrayList<User> users = fileManager.getUsersFromFile();
        users.add(newlyCreatedUser);
        fileManager.rewriteUsersToFile(users);
        // Log de gebruikers voor debugging doeleinden
        for (User user : users) {
            System.out.println("User in list: " + user.getUsername());
        }
    }

    public boolean login(String email, String password) {
        if (checkEmailPassword(email, password)) {
            setUserWithEmail(email);
            return true;
        } else {
            return false;
        }
    }

    public void setActiveUser(User user) {
        // zet de actieve gebruiker
        this.activeUser = user;
    }
}
