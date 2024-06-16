package com.assistant.aiassistant.chatItemCreatorClasses;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class ChatBubbleItemsCreator {
    private static final Insets labelInsets = new Insets(5.0, 5.0, 5.0, 5.0);
    private static final Insets textFlowPadding = new Insets(10.0, 8.0, 10.0, 8.0);
    private static final Insets textFlowMargin = new Insets(25.0, 10.0, 5.0, 10.0);

    public static VBox createContainer(boolean alignToTheRight) {
        VBox vBox = new VBox();
        if(alignToTheRight) {
            vBox.setAlignment(Pos.TOP_RIGHT);
        } else {
            vBox.setAlignment(Pos.TOP_LEFT);
        }
        vBox.setSpacing(0.0);
        return vBox;
    }
    public static Text createTextItem(String contents) {
        Text text = new Text(contents);
        text.setFont(Font.font("Roboto Thin", 14));
        return text;
    }

    public static Label createMessageSenderLabel(String text, boolean alignToTheRight) {
        Label label = new Label(text);
        if(alignToTheRight) {
            label.setTextAlignment(TextAlignment.RIGHT);
        }
        HBox.setMargin(label, labelInsets);
        label.setTextFill(Color.BLACK);
        label.setFont(Font.font("Roboto Thin", 14));
        return label;
    }
    public static TextFlow createTextFlow(Text text, String bgColor, boolean alignToTheRight) {
        TextFlow textFlow = new TextFlow(text);
        textFlow.setStyle("-fx-background-color: " + bgColor + "; -fx-background-radius: 10;");
        if (alignToTheRight) {
            textFlow.setTextAlignment(TextAlignment.RIGHT);
        } else {
            textFlow.setTextAlignment(TextAlignment.LEFT);
        }
        textFlow.setPadding(textFlowPadding);
        HBox.setMargin(textFlow, textFlowMargin);
        return textFlow;
    }
}
