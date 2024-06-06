package com.assistant.aiassistant;
import java.util.List;

public class LoadSavedConversationActionSelect {

    private List<String> savedConversations;

    // Add a method to set the saved conversations list
    public void setSavedConversations(List<String> savedConversations) {
        this.savedConversations = savedConversations;
    }

    public void Select() {
        System.out.println("Kies een gesprek om te laden:");
        for (int i = 0; i < savedConversations.size(); i++) {
            System.out.println((i + 1) + ". " + savedConversations.get(i));
        }
        System.out.print("Maak een keuze: ");
    }
}