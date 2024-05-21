package com.assistant.aiassistant;

public class User {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String prefferedLanguage;

    public User(String name) {
        this.userName = userName;
    }

    public void setName(String name) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
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
