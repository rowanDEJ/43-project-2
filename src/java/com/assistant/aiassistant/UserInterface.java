package com.assistant.aiassistant;

public class UserInterface {
    private String currentLanguage;
    private String font;
    private String color;

    public UserInterface(String currentLanguage, String font, String color) {
        this.currentLanguage = currentLanguage;
        this.font = font;
        this.color = color;
    }

    public void setCurrentLanguage(String currentLanguage) {
        this.currentLanguage = currentLanguage;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCurrentLanguage() {
        return currentLanguage;
    }

    public String getFont() {
        return font;
    }

    public String getColor() {
        return color;
    }

    public void start() {

    }

    public void display() {

    }

    public void switchTab() {

    }

    public void showInfo() {

    }
}
