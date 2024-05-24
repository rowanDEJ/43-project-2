package com.assistant.aiassistant;
import java.util.List;

public class Conversation {
    final private String topic; // The topic of the conversation
    final private List<String> message; // The messages in the conversation

    public Conversation(String topic, List<String> message) {
        this.topic = topic;
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public List<String> getMessage() {
        return message;
    }

    public void addMessage(String message) {
        this.message.add(message);
    }
}