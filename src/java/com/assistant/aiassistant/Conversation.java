package com.assistant.aiassistant;
import java.util.List;

public class Conversation {
    final private String topic; // The topic of the conversation
    final private List<String> messages; // The messages in the conversation

    public Conversation(String topic, List<String> message) {
        this.topic = topic;
        this.messages = message;
    }

    public String getTopic() {
        return topic;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }
}