package com.assistant.aiassistant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LoadSavedConversationAction implements Action {

    LoadSavedConversationActionMenu menu = new LoadSavedConversationActionMenu();
    LoadSavedConversationActionSelect selectAction = new LoadSavedConversationActionSelect();

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        List<String> savedConversations = IOFileManager.getSavedConversations();
        if (!savedConversations.isEmpty()) {

            selectAction.setSavedConversations(savedConversations);
            selectAction.Select();

            int conversationChoice = scanner.nextInt();
            if (conversationChoice > 0 && conversationChoice <= savedConversations.size()) {
                Conversation loadedConversation = new Conversation(savedConversations.get(conversationChoice - 1), new ArrayList<>());
                IOFileManager.loadConversation(loadedConversation);
                Map<Integer, Runnable> options = new HashMap<>();
                options.put(1, () -> LoadSavedConversationActionEditDelete.editConversation(loadedConversation));
                options.put(2, () -> IOFileManager.deleteConversation(loadedConversation));
                options.put(3, () -> LoadSavedConversationActionEditDelete.readConversation(loadedConversation));
                menu.execute();
                int editChoice = scanner.nextInt();
                Runnable action = options.get(editChoice);
                if (action != null) {
                    action.run();
                } else {
                    System.out.println("Ongeldige keuze. Probeer het opnieuw.");
                }
            } else {
                System.out.println("No saved conversations found.");
            }
        }
    }
}