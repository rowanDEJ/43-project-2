package com.assistant.aiassistant.chatItemCreatorClasses;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class ChatBubbleItemsCreator {
    public static HBox createContainerHBox() {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.TOP_RIGHT);
        hbox.setLayoutX(10.0);
        hbox.setLayoutY(10.0);
        return hbox;
    }
    public static Text createTextItem(String contents) {
        Text text = new Text(contents);
        text.setWrappingWidth(539.1171875);
        return text;
    }

    public static Label createMessageSenderLabel(String text, boolean alignToTheRight) {
        Label label = new Label(text);
        label.setPrefHeight(12.0);
        label.setPrefWidth(12.0);
        if(alignToTheRight) {
            label.setTextAlignment(TextAlignment.RIGHT);
        }
        HBox.setMargin(label, new Insets(5.0, 5.0, 0, 0));
        return label;
    }
    public static TextFlow createTextFlow(Text text, String bgColor) {
        TextFlow textFlow = new TextFlow(text);
        textFlow.setMaxWidth(440.0);
        textFlow.setMinWidth(30.0);
        textFlow.setStyle("-fx-background-color: " + bgColor + "; -fx-background-radius: 10;");
        textFlow.setTextAlignment(TextAlignment.CENTER);
        textFlow.setPadding(new Insets(10.0, 8.0, 10.0, 8.0));
        HBox.setMargin(textFlow, new Insets(25.0, 10.0, 5.0, 10.0));
        return textFlow;
    }
}
