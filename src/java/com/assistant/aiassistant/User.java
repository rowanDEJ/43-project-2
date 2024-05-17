package com.assistant.aiassistant;

public class User {
    private String username;
    private String password;
    private String email;
    private String voornaam;
    private String achternaam;
    private String preferredLanguage;

    public User(String username, String wachtwoord, String email, String voornaam, String achternaam, String preferredLanguage) {
        this.username = username;
        this.password = wachtwoord;
        this.email = email;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.preferredLanguage = preferredLanguage;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
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

    }

    public void login() {

    }

    public void logout() {

    }
}
