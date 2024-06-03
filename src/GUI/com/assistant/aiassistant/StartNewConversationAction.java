// File: StartNewConversationAction.java
package com.assistant.aiassistant;

import java.util.ArrayList;

public class StartNewConversationAction implements Action {
    String topic;
    String message;

    @Override
    public void execute() {
        Conversation conversation = new Conversation(topic, new ArrayList<>());

    }

    public StartNewConversationAction(String topic, String message) {
        this.topic = topic;
        this.message = message;
    }
}