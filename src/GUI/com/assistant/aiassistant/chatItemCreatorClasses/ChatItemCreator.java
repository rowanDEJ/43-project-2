package com.assistant.aiassistant.chatItemCreatorClasses;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ChatItemCreator {
    public static HBox createChatNavButton(String topic, EventHandler<ActionEvent> buttonEvent) {
        return ChatNavButtonCreator.Create(topic, buttonEvent);
    }
    public static VBox createAnswerBubble(String message) {
        return ChatBubbleCreator.Create(message, "WHITE", "AI", false);
    }
    public static VBox createQuestionBubble(String message) {
        return ChatBubbleCreator.Create(message, "LIGHTGREY", "Jij", true);
    }
}
