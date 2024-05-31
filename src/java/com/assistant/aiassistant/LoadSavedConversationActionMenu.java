package com.assistant.aiassistant;

public class LoadSavedConversationActionMenu implements Action {

    @Override
    public void execute() {
        System.out.println("1. Bewerk de conversatie");
        System.out.println("2. Verwijder de conversatie");
        System.out.println("3. Lees de conversatie");
        System.out.print("Maak een keuze: ");
        System.out.println();
    }
}
