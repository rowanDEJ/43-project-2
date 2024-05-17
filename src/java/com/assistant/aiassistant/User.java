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

    public void setPrefferedLanguage(String prefferedLanguage) {
        this.prefferedLanguage = prefferedLanguage;
    }

    public String getUserName() {
        return userName;
    }

    public String getPrefferedLanguage() {
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
