package com.assistant.aiassistant;

public class Language {
    private final String name;

    public Language(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
