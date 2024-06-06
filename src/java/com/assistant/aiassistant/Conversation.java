package com.assistant.aiassistant;
import java.util.ArrayList;
import java.util.List;

public class Conversation {
    final private String topic; // The topic of the conversation
    final private ArrayList<String> messages; // The messages in the conversation

    public Conversation(String topic, ArrayList<String> messages) {
        this.topic = topic;
        this.messages = messages;
    }

    public String getTopic() {
        return topic;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void addMessage(String message) {
        messages.add(message);
    }
}