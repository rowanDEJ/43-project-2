package com.assistant.aiassistant;

import java.util.ArrayList;

public class Conversation {
    private String topic;
    private ArrayList message;

    public Conversation(String topic, ArrayList message) {
        this.topic = topic;
        this.message = message;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setMessage(ArrayList message) {
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public ArrayList getMessage() {
        return message;
    }

    public void displayTopic() {

    }
}
