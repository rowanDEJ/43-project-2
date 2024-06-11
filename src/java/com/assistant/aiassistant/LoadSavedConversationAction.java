package com.assistant.aiassistant;

import java.util.ArrayList;

public class LoadSavedConversationAction implements Action {
    public ArrayList<Conversation> savedConversations;

    @Override
    public void execute() {
        savedConversations = FileIOManager.getSavedConversations();
        for (Conversation conversation : savedConversations) {
            FileIOManager fileIOManager = new FileIOManager();
            ArrayList<String> messages = fileIOManager.readFile("files/conversations/" + AccountManager.getInstance().getActiveUser().getUsername() + "/" + conversation.getTopic() + ".txt");
            for (String message : messages) {
                conversation.addMessage(message);
            }
        }
    }
}