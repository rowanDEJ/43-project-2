package com.assistant.aiassistant;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class MainController {
    public Button newChatButton;
    @FXML
    private Stage primaryStage;
    public VBox convVBox;
    public VBox chatBox;
    public Label chatTitle;
    public TextArea bericht;
    public ScrollPane convScrollPane;

    private static final String FILE_PATH = "files/";
    public ArrayList<Conversation> savedConversations;
    private ArrayList<String> createdConversations = new ArrayList<>();

    public AccountManager accountManager = AccountManager.getInstance();
    public Locale appLocale = new Locale(accountManager.getActiveUser().getPreferredLanguage());
    ResourceBundle bundle = ResourceBundle.getBundle("MessageBundle", appLocale);

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }
    @FXML
    private void handleNewChat() {
        showNewChatDialog();
    }

    public void initialize() {
        loadSavedConversations();
        fileCreationListener();
        messageCreationListener();
        initializeMessagebox();
        LoadSavedConversationAction action = new LoadSavedConversationAction();
        action.execute();
        savedConversations = action.savedConversations;
        bericht.setDisable(true);
    }

    private void initializeMessagebox() {
        bericht.setOnKeyPressed(e -> {
            if (e.getCode().toString().equals("ENTER")) {
                String message = bericht.getText();
                message = message.replace("\n", "");
                bericht.clear();
                MessageHandler(message);
            }
        });
    }

    private void loadSavedConversations() {
        File folder = new File(FILE_PATH + "conversations/");
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            Arrays.sort(listOfFiles, Comparator.comparingLong(File::lastModified));

            for (File file : listOfFiles) {
                if (file.isFile()) {
                    String topic = file.getName().replace(".txt", "");
                    createHBoxWithButtons(topic);
                }
            }
        }
    }

    private void fileCreationListener() {
        FileCreationMonitor monitor = new FileCreationMonitor(new File("files/conversations"));
        monitor.addObserver(fileName -> {
            Platform.runLater(() -> {
                // fileName is the name of the file that was created
                String topic = fileName.replace(".txt", "");
                createHBoxWithButtons(topic);
            });
        });
        monitor.start();
    }

    private void messageCreationListener() {
        File folder = new File(FILE_PATH + "conversations/");
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                MessageCreationMonitor monitor = new MessageCreationMonitor(file);
                monitor.addObserver(message -> {
                    Platform.runLater(() -> {
                        // message is the message that was added to the file
                        chatBox.getChildren().add(createQuestionHBox(message));

                        Platform.runLater(() -> convScrollPane.setVvalue(1.0));
                    });
                });
                monitor.start();
            }
        }
    }

    public void createHBoxWithButtons(String topic) {
      if (!(createdConversations.contains(topic))) {
          createdConversations.add(topic);
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
          parentButton.setOnAction(e -> showChat(topic));

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
    }

    private void showChat(String topic) {
        chatBox.getChildren().clear();
        chatTitle.setText(topic);
        bericht.setDisable(false);
        for (Conversation conversation : savedConversations) {
            if (conversation.getTopic().equals(topic)) {
                for (String message : conversation.getMessages()) {
                    if (message.startsWith("AI-")) {
                        chatBox.getChildren().add(createAnswerHBox(message.substring(3)));
                    } else {
                        chatBox.getChildren().add(createQuestionHBox(message));
                    }
                    convScrollPane.setVvalue(1.0);
                }
            }
        }
    }

    private HBox createQuestionHBox(String message) {
        // Create the Text
        Text text = new Text(message);
        text.setWrappingWidth(539.1171875);

        // Create the TextFlow and add the Text
        TextFlow textFlow = new TextFlow(text);
        textFlow.setMaxWidth(440.0);
        textFlow.setMinWidth(30.0);
        textFlow.setStyle("-fx-background-color: DARKGREY; -fx-background-radius: 10;");
        textFlow.setTextAlignment(TextAlignment.CENTER);
        textFlow.setPadding(new Insets(10.0, 8.0, 10.0, 8.0));
        HBox.setMargin(textFlow, new Insets(25.0, 10.0, 5.0, 10.0));

        // Create the Label
        Label label = new Label(bundle.getString("you")); // Jij
        label.setPrefHeight(20.0); // eerst op 12.0
        label.setPrefWidth(30.0); // eerst op 12.0
        label.setTextAlignment(TextAlignment.RIGHT);
        HBox.setMargin(label, new Insets(5.0, 5.0, 0, 0)); // 5.0, 5.0, 0, 0

        // Create the HBox and add the TextFlow and Label
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.TOP_RIGHT);
        hbox.setLayoutX(10.0);
        hbox.setLayoutY(10.0);
        hbox.getChildren().addAll(textFlow, label);

        return hbox;
    }

    private HBox createAnswerHBox(String message) {
        // Create the Text
        Text text = new Text(message);
        text.setWrappingWidth(539.1171875);
        text.setTextAlignment(TextAlignment.CENTER);

        // Create the TextFlow and add the Text
        TextFlow textFlow = new TextFlow(text);
        textFlow.setMaxWidth(440.0);
        textFlow.setMinWidth(30.0);
        textFlow.setStyle("-fx-background-color: LIGHTGREY; -fx-background-radius: 10;");
        textFlow.setTextAlignment(TextAlignment.CENTER);
        textFlow.setPadding(new Insets(10.0, 8.0, 10.0, 8.0));
        HBox.setMargin(textFlow, new Insets(25.0, 10.0, 5.0, 10.0));

        // Create the Label
        Label label = new Label("AI");
        label.setPrefHeight(12.0);
        label.setPrefWidth(12.0);
        HBox.setMargin(label, new Insets(5.0, 0, 0, 5.0));

        // Create the HBox and add the TextFlow and Label
        HBox hbox = new HBox();
        hbox.setLayoutX(10.0);
        hbox.setLayoutY(10.0);
        hbox.getChildren().addAll(label, textFlow);

        return hbox;
    }
    private void showNewChatDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);

        VBox dialogVBox = new VBox(10);
        Label label = new Label(bundle.getString("newChatDialog")); // New chat dialog
        TextField textField = new TextField();
        Button button = new Button(bundle.getString("startChat")); // Start Chat
        button.setOnAction(e -> {
            String topic = textField.getText();
            StartNewConversationAction action = new StartNewConversationAction(topic, "");
            action.execute();
            dialog.close();
        });

        dialogVBox.getChildren().addAll(label, textField, button);

        Scene dialogScene = new Scene(dialogVBox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.setTitle(bundle.getString("newChat")); // New Chat
        dialog.show();
    }


    private void MessageHandler(String message) {
        if (!(message.isBlank())) {
            String topic = chatTitle.getText();
            for (Conversation conversation : savedConversations) {
                if (conversation.getTopic().equals(topic)) {
                   FileIOManager.addMessageToConversation(message, conversation);
                }
            }
        }
    }

    public void showSettings() throws IOException {
        UserInterfaceManager uiManager = UserInterfaceManager.getInstance();
        uiManager.switchCurrentViewTo(uiManager.settingsViewFilename);
    }
}