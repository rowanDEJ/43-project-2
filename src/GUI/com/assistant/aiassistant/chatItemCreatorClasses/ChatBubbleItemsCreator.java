package com.assistant.aiassistant.chatItemCreatorClasses;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class ChatBubbleItemsCreator {
    private static final Insets labelInsets = new Insets(5.0, 5.0, 5.0, 5.0);
    private static final Insets textFlowPadding = new Insets(10.0, 8.0, 10.0, 8.0);
    private static final Insets textFlowMargin = new Insets(25.0, 10.0, 5.0, 10.0);

    public static HBox createContainerHBox(boolean alignToTheRight) {
        HBox hbox = new HBox();
        if(alignToTheRight) {
            hbox.setAlignment(Pos.TOP_RIGHT);
        } else {
            hbox.setAlignment(Pos.TOP_LEFT);
        }
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
        HBox.setMargin(label, labelInsets);
        return label;
    }
    public static TextFlow createTextFlow(Text text, String bgColor) {
        TextFlow textFlow = new TextFlow(text);
        textFlow.setMaxWidth(440.0);
        textFlow.setMinWidth(30.0);
        textFlow.setStyle("-fx-background-color: " + bgColor + "; -fx-background-radius: 10;");
        textFlow.setTextAlignment(TextAlignment.CENTER);
        textFlow.setPadding(textFlowPadding);
        HBox.setMargin(textFlow, textFlowMargin);
        return textFlow;
    }
}
