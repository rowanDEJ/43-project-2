// File: StartNewConversationAction.java
package com.assistant.aiassistant;

import java.util.ArrayList;
import java.util.Scanner;

public class StartNewConversationAction implements Action {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Voer een onderwerp in voor de nieuwe conversatie: ");
        String topic = scanner.next();
        Conversation conversation = new Conversation(topic, new ArrayList<>());
        System.out.print("Voer een bericht in: ");
        String message = scanner.next();
        conversation.addMessage(message);
        IOFileManager.saveConversation(conversation);
    }
}