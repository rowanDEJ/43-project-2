package com.assistant.aiassistant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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