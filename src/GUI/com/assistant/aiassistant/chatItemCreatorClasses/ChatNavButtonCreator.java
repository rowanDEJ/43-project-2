package com.assistant.aiassistant.chatItemCreatorClasses;

import com.assistant.aiassistant.FileIOManager;
import com.assistant.aiassistant.MainController;
import com.assistant.aiassistant.UserInterfaceManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class ChatNavButtonCreator {
    public static String moreOptionsIconPath = "file:src/resources/com/assistant/aiassistant/icons/three-dots-vertical.png";
    public static HBox Create(String topic, EventHandler<ActionEvent> buttonEvent) {
        HBox buttonHbox = createContainerHBox();

        Button chatButton = createChatButton(topic);
        ImageView optionsImageView = createOptionsImageView();
        ContextMenu contextMenu = createChatOptions(topic);

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
    private static ContextMenu createChatOptions(String buttonChatTopic) {

        ContextMenu contextMenu = new ContextMenu();

        MenuItem deleteItem = new MenuItem("Delete chat");
        CustomMenuItem renameItem = new CustomMenuItem(new Text("Change topic"));
        renameItem.setHideOnClick(false);

        TextField textField = new TextField();
        textField.setPromptText("Enter new topic");

        CustomMenuItem inputMenuItem = new CustomMenuItem(textField);
        inputMenuItem.setHideOnClick(false);

        deleteItem.setOnAction(e -> Platform.runLater(() -> {
            try {
                FileIOManager.deleteConversation(MainController.getInstance().getConversationWithTopic(buttonChatTopic));
                UserInterfaceManager.getInstance().switchCurrentViewTo(UserInterfaceManager.getInstance().mainViewFilename);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }));

        renameItem.setOnAction(e -> Platform.runLater(() -> {
            contextMenu.getItems().remove(renameItem);
            contextMenu.getItems().add(inputMenuItem);

            TextField field = (TextField) inputMenuItem.getContent();
            field.setOnKeyPressed(keyEvent -> {
                if(keyEvent.getCode() == KeyCode.ENTER) {
                    if(field.getText() != null && (! field.getText().isBlank())) {
                        boolean success = FileIOManager.changeConversationTopic(MainController.getInstance().getConversationWithTopic(buttonChatTopic), field.getText());
                        if(success) {
                            try {
                                UserInterfaceManager.getInstance().switchCurrentViewTo(UserInterfaceManager.getInstance().mainViewFilename);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }
                keyEvent.consume();
            });
        }));


        contextMenu.getItems().addAll(deleteItem, renameItem);
        return contextMenu;
    }
}
