package com.assistant.aiassistant.chatItemCreatorClasses;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ChatNavButtonCreator {
    public static String moreOptionsIconPath = "file:src/resources/com/assistant/aiassistant/icons/three-dots-vertical.png";
    public static HBox Create(String topic, EventHandler<ActionEvent> buttonEvent) {
        HBox buttonHbox = createContainerHBox();

        Button chatButton = createChatButton(topic);
        ImageView optionsImageView = createOptionsImageView();
        ContextMenu contextMenu = createChatOptions();

        optionsImageView.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            double mouseX = e.getScreenX();
            double mouseY = e.getScreenY();

            contextMenu.show(optionsImageView, mouseX, mouseY);
        });

        chatButton.setOnAction(buttonEvent);

        buttonHbox.getChildren().addAll(chatButton, optionsImageView);
        return buttonHbox;
    }
    private static HBox createContainerHBox() {
        HBox buttonHbox = new HBox();
        buttonHbox.setPrefHeight(14.0);
        buttonHbox.setPrefWidth(146.0);
        buttonHbox.getStyleClass().add("HBox");
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
    private static ImageView createOptionsImageView() {
        ImageView imageView = new ImageView(new Image(moreOptionsIconPath));
        imageView.setFitHeight(24.0);
        imageView.setFitWidth(23.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.getStyleClass().add("optionsImage");
        return imageView;
    }
    private static ContextMenu createChatOptions() {

        ContextMenu contextMenu = new ContextMenu();

        MenuItem deleteItem = new MenuItem("Verwijder chat"); //TODO: talen
        MenuItem renameItem = new MenuItem("Hernoem chat");

        deleteItem.setOnAction(e -> {
            //TODO: add funct ionatlihaoysdpo fyaosudhf oaushdf pliasduhd
        });

        renameItem.setOnAction(e -> {
        });

        contextMenu.getItems().addAll(deleteItem, renameItem);
        return contextMenu;
    }
}
