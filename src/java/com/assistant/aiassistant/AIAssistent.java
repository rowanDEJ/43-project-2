package com.assistant.aiassistant;

public class AIAssistent {
    private String name;
    private String prefferedLanguage;
    private String answers;

    public AIAssistent(String name, String prefferedLanguage, String answers) {
        this.name = name;
        this.prefferedLanguage = prefferedLanguage;
        this.answers = answers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrefferedLanguage(String prefferedLanguage) {
        this.prefferedLanguage = prefferedLanguage;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getName() {
        return name;
    }

    public String getPrefferedLanguage() {
        return prefferedLanguage;
    }

    public String getAnswer() {
        return answers;
    }

    public void changeLanguage() {

    }

    public void respond() {

    }

    public void login() {

    }

    public void logout() {

    }
}
