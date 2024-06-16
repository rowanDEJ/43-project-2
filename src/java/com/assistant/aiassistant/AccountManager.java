package com.assistant.aiassistant;

public class AccountManager {
    private static AccountManager instance = null;
    private User activeUser;
    FileIOManager fileManager = new FileIOManager();

    public AccountManager() {
        setUserWithUsername(null); // null betekent dat er niemand is ingelogd
        instance = this;
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

    protected boolean checkUsernamePassword(String userName, String password) {
        for (User user : fileManager.getUsersFromFile()) {
            if (user.getUsername().equals(userName) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    protected boolean checkEmailPassword(String email, String password) {
        for (User user : fileManager.getUsersFromFile()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfUserWithEmailExists(String emailToSearch) {
        for (User user : fileManager.getUsersFromFile()) {
            if (user.getEmail().equalsIgnoreCase(emailToSearch)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfUserWithUsernameExists(String usernameToSearch) {
        for (User user : fileManager.getUsersFromFile()) {
            if (user.getUsername().equalsIgnoreCase(usernameToSearch)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfUserWithFullNameExists(String fullNameToSearch) {
        for (User user : fileManager.getUsersFromFile()) {
            if (user.getFullName().equalsIgnoreCase(fullNameToSearch)) {
                return true;
            }
        }
        return false;
    }

    public void createAccount(String username, String password, String email, String fname, String lname, String preferredlanguage, String aiLanguage) {
        if (checkIfUserWithUsernameExists(username)) {
            return;
        }
        User newlyCreatedUser = new User(username, password, email, fname, lname, preferredlanguage, aiLanguage);
        fileManager.saveUserToFile(newlyCreatedUser);
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
