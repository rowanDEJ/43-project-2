package com.assistant.aiassistant;

public class  User {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String prefferedLanguage;

    public User(String userName) {
        this.userName = userName;
    }

    public User(String userName, String password, String email, String firstName, String lastName, String prefferedLanguage) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.prefferedLanguage = prefferedLanguage;
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

    public void setPreferredLanguage(String prefferedLanguage) {
        this.prefferedLanguage = prefferedLanguage;
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
        return prefferedLanguage;
    }

    public void changeLanguage() {
        if (prefferedLanguage.equals("NL")) {
            prefferedLanguage = "EN";
        } else {
            prefferedLanguage = "NL";
        }

    }

    public void login() {
        // nog maken

    }

    public void logout() {
        // nog maken

    }
}
