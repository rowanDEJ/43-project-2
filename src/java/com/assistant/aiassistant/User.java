package com.assistant.aiassistant;

public class User {
    private String username;
    private String password;
    private String email;
    private String voornaam;
    private String achternaam;
    private String prefferedLanguage;

    public User(String userame, String wachtwoord, String email, String voornaam, String achternaam, String prefferedLanguage) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.prefferedLanguage = prefferedLanguage;
    }

    public void setUsername(String name) {
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

    public void setPrefferedLanguage(String prefferedLanguage) {
        this.prefferedLanguage = prefferedLanguage;
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

    public String getPrefferedLanguage() {
        return prefferedLanguage;
    }

    public void changeLanguage() {

    }

    public void login() {

    }

    public void logout() {

    }
}
