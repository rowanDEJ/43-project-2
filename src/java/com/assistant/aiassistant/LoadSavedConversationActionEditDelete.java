package com.assistant.aiassistant;

import java.util.Scanner;

public class LoadSavedConversationActionEditDelete {

    public static void editConversation(Conversation conversation) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voer een nieuw bericht in voor de conversatie:");
        String newMessage = scanner.nextLine();
        conversation.addMessage(newMessage);
        FileIOManager.saveConversation(conversation);
        System.out.println("Conversatie is bijgewerkt.");
    }

    public static void readConversation(Conversation conversation) {
        System.out.println("Conversatie onderwerp: " + conversation.getTopic());
        System.out.println("Berichten in deze conversatie:");
        for (String message : conversation.getMessages()) {
            System.out.println(message);
        }
    }
}
