package com.assistant.aiassistant;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;

public class MainController {
    public Button newChatButton;
    @FXML
    private Stage primaryStage;
    public VBox convVBox;

    private static final String FILE_PATH = "files/";

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }
    @FXML
    private void handleNewChat() {
        showNewChatDialog();
    }

    public void initialize() {
        File folder = new File(FILE_PATH + "conversations/");
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    String topic = file.getName().replace(".txt", "");
                    createHBoxWithButtons(topic);
                }
            }
        }
    }

    public void createHBoxWithButtons(String topic) {
        HBox hbox = new HBox();
        hbox.setPrefHeight(14.0);
        hbox.setPrefWidth(146.0);
        hbox.getStyleClass().add("HBox");

        Button parentButton = new Button(topic);
        parentButton.setGraphicTextGap(0.0);
        parentButton.setMaxHeight(Double.MAX_VALUE);
        parentButton.setMaxWidth(Double.MAX_VALUE);
        parentButton.setPrefHeight(43.0);
        parentButton.setPrefWidth(302.0);
        parentButton.getStyleClass().add("parentButton");
        parentButton.setId(topic);

        Button innerButton = new Button();
        innerButton.setAlignment(Pos.CENTER_RIGHT);
        innerButton.setContentDisplay(ContentDisplay.RIGHT);
        innerButton.setMaxHeight(Double.MAX_VALUE);
        innerButton.setMaxWidth(Double.MAX_VALUE);
        innerButton.setPrefHeight(43.0);
        innerButton.setPrefWidth(9.0);
        innerButton.getStyleClass().add("innerButton");

        ImageView imageView = new ImageView(new Image("file:src/resources/com/assistant/aiassistant/icons/three-dots-vertical.png"));
        imageView.setFitHeight(24.0);
        imageView.setFitWidth(23.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        innerButton.setGraphic(imageView);

        hbox.getChildren().addAll(parentButton, innerButton);
        convVBox.getChildren().add(hbox);
    }


    private void showNewChatDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);

        VBox dialogVBox = new VBox(10);
        Label label = new Label("New chat dialog");
        TextField textField = new TextField();
        Button button = new Button("Start Chat");
        button.setOnAction(e -> {
            String topic = textField.getText();
            StartNewConversationAction action = new StartNewConversationAction(topic, "Hello");
            action.execute();
            dialog.close();
        });

        dialogVBox.getChildren().addAll(label, textField, button);

        Scene dialogScene = new Scene(dialogVBox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.setTitle("New Chat");
        dialog.show();
    }
}