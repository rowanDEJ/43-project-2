package com.assistant.aiassistant.chatItemCreatorClasses;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ChatBubbleCreator {
    public static HBox Create(String message, String bgColor, String senderName, boolean alightToTheRight) {
        Text text = ChatBubbleItemsCreator.createTextItem(message);
        TextFlow textFlow = ChatBubbleItemsCreator.createTextFlow(text, bgColor);
        Label label = ChatBubbleItemsCreator.createMessageSenderLabel(senderName, alightToTheRight);
        HBox hbox = ChatBubbleItemsCreator.createContainerHBox(alightToTheRight);

        if(alightToTheRight) {
            hbox.getChildren().addAll(textFlow, label);
        } else {
            hbox.getChildren().addAll(label, textFlow);
        }
        return hbox;
    }
}
