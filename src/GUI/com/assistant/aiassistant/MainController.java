package com.assistant.aiassistant;

import com.assistant.aiassistant.chatItemCreatorClasses.ChatItemCreator;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class MainController {
    public Button newChatButton;
    @FXML
    private Stage primaryStage;
    public VBox chatNavigationBar;
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
        initializeMessagebox();
        LoadSavedConversationAction action = new LoadSavedConversationAction();
        action.execute();
        savedConversations = action.savedConversations;
        bericht.setDisable(true);
        bericht.setPromptText(bundle.getString("messagePrompt")); // Bericht...
        chatTitle.setText(bundle.getString("noChat")); // Geen Chat
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
        File folder = new File(FILE_PATH + "conversations/" + accountManager.getActiveUser().getUsername());
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            Arrays.sort(listOfFiles, Comparator.comparingLong(File::lastModified));

            for (File file : listOfFiles) {
                if (file.isFile()) {
                    String topic = file.getName().replace(".txt", "");
                    createChatNavigationButton(topic);
                }
            }
        }
    }

    private void fileCreationListener() {
        FileCreationMonitor monitor = new FileCreationMonitor(new File("files/conversations/" + accountManager.getActiveUser().getUsername()));
        monitor.addObserver(fileName -> {
            Platform.runLater(() -> {
                // fileName is the name of the file that was created
                String topic = fileName.replace(".txt", "");
                createChatNavigationButton(topic);
            });
        });
        monitor.start();
    }

    public void createChatNavigationButton(String topic) {
        if (createdConversations.contains(topic)) {
            return;
        }
        HBox buttonHBox = ChatItemCreator.createChatNavButton(topic, (e -> showChat(topic)));
        chatNavigationBar.getChildren().add(buttonHBox);
        createdConversations.add(topic);
    }

    private void showChat(String topic) {
        chatBox.getChildren().clear();
        chatTitle.setText(topic);
        bericht.setDisable(false);
        for (Conversation conversation : savedConversations) {
            if (conversation.getTopic().equals(topic)) {
                for (String message : conversation.getMessages()) {
                    if (message.startsWith("AI-")) {
                        String contents = message.substring(3);
                        HBox answerBubble = ChatItemCreator.createAnswerBubble(contents);
                        chatBox.getChildren().add(answerBubble);
                    } else {
                        HBox questionBubble = ChatItemCreator.createQuestionBubble(message);
                        chatBox.getChildren().add(questionBubble);
                    }
                    convScrollPane.setVvalue(1.0);
                }
                break;
            }
        }
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
            StartNewConversationAction action = new StartNewConversationAction(topic, "AI-Hello! How can I help you?");
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
        if (message.isBlank()) {
            return;
        }

        String topic = chatTitle.getText();
        for (Conversation conversation : savedConversations) {
            if (conversation.getTopic().equals(topic)) {
               FileIOManager.addMessageToConversation(message, conversation);
                HBox questionBubble = ChatItemCreator.createQuestionBubble(message);
                chatBox.getChildren().add(questionBubble);
            }
        }
    }

    public void showSettings() throws IOException {
        UserInterfaceManager uiManager = UserInterfaceManager.getInstance();
        uiManager.switchCurrentViewTo(uiManager.settingsViewFilename);
    }
}