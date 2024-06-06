package com.assistant.aiassistant.chatItemCreatorClasses;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;

public class ChatItemCreator {
    public static HBox createChatNavButton(String topic, EventHandler<ActionEvent> buttonEvent) {
        return ChatNavButtonCreator.Create(topic, buttonEvent);
    }
    public static HBox createAnswerBubble(String message) {
        return ChatBubbleCreator.Create(message, "LIGHTGREY", "AI-Assistent", false);
    }
    public static HBox createQuestionBubble(String message) {
        return ChatBubbleCreator.Create(message, "DARKGREY", "Jij", true);
    }
}
