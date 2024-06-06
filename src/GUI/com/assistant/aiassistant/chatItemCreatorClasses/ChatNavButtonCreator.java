package com.assistant.aiassistant.chatItemCreatorClasses;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ChatNavButtonCreator {
    public static String moreOptionsIconPath = "file:src/resources/com/assistant/aiassistant/icons/three-dots-vertical.png";
    public static HBox Create(String topic, EventHandler<ActionEvent> buttonEvent) {
        HBox buttonHbox = new HBox();
        buttonHbox.setPrefHeight(14.0);
        buttonHbox.setPrefWidth(146.0);
        buttonHbox.getStyleClass().add("HBox");

        Button chatButton = createChatButton(topic);
        Button optionsButton = createOptionsButton();
        ImageView optionsImageView = createOptionsImageView();

        optionsButton.setGraphic(optionsImageView);

        chatButton.setOnAction(buttonEvent);

        buttonHbox.getChildren().addAll(chatButton, optionsButton);
        return buttonHbox;
    }

    private static Button createChatButton(String topic) {
        Button button = new Button(topic);
        button.setGraphicTextGap(0.0);
        button.setMaxHeight(Double.MAX_VALUE);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setPrefHeight(43.0);
        button.setPrefWidth(302.0);
        button.getStyleClass().add("parentButton");
        button.setId(topic);
        return button;
    }
    private static Button createOptionsButton() {
        Button optionsButton = new Button();
        optionsButton.setAlignment(Pos.CENTER_RIGHT);
        optionsButton.setContentDisplay(ContentDisplay.RIGHT);
        optionsButton.setMaxHeight(Double.MAX_VALUE);
        optionsButton.setMaxWidth(Double.MAX_VALUE);
        optionsButton.setPrefHeight(43.0);
        optionsButton.setPrefWidth(9.0);
        optionsButton.getStyleClass().add("innerButton");
        return optionsButton;
    }
    private static ImageView createOptionsImageView() {
        ImageView imageView = new ImageView(new Image(moreOptionsIconPath));
        imageView.setFitHeight(24.0);
        imageView.setFitWidth(23.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        return imageView;
    }
}
