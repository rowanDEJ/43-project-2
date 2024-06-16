package com.assistant.aiassistant.chatItemCreatorClasses;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ChatBubbleCreator {
    public static VBox Create(String message, String bgColor, String senderName, boolean alightToTheRight) {
        Text text = ChatBubbleItemsCreator.createTextItem(message);
        TextFlow textFlow = ChatBubbleItemsCreator.createTextFlow(text, bgColor, alightToTheRight);
        Label label = ChatBubbleItemsCreator.createMessageSenderLabel(senderName, alightToTheRight);
        VBox vbox = ChatBubbleItemsCreator.createContainer(alightToTheRight);

        HBox textFlowContainer = new HBox();
        if(alightToTheRight) {
            textFlowContainer.setAlignment(Pos.CENTER_RIGHT);
        } else {
            textFlowContainer.setAlignment(Pos.CENTER_LEFT);
        }
        textFlowContainer.setPadding(Insets.EMPTY);
        textFlowContainer.getChildren().add(textFlow);

        textFlowContainer.setEffect(createShadow(Color.BLACK));

        vbox.getChildren().addAll(label, textFlowContainer);

        return vbox;
    }
    private static DropShadow createShadow(Color color) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setBlurType(BlurType.ONE_PASS_BOX);
        dropShadow.setColor(color);
        dropShadow.setRadius(15);
        dropShadow.setSpread(0.15);
        dropShadow.setOffsetX(0);
        dropShadow.setOffsetY(0);
        return dropShadow;
    }
}
