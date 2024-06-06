package com.assistant.aiassistant;

public class  User {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String preferredLanguage;


    public User(String userName) {
        this.userName = userName;
    }

    public User(String userName, String password, String email, String firstName, String lastName, String preferredLanguage) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.preferredLanguage = preferredLanguage;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public String getUsername() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void changeLanguage() {
        if (preferredLanguage.equals("NL")) {
            preferredLanguage = "EN";
        } else {
            preferredLanguage = "NL";
        }

    }

    public void login() {
        // nog maken

    }

    public void logout() {
        // nog maken

    }

}
