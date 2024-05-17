package com.assistant.aiassistant;

public class User {
    private String name;
    private String prefferedLanguage;

    public User(String name, String prefferedLanguage) {
        this.name = name;
        this.prefferedLanguage = prefferedLanguage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrefferedLanguage(String prefferedLanguage) {
        this.prefferedLanguage = prefferedLanguage;
    }

    public String getName() {
        return name;
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
