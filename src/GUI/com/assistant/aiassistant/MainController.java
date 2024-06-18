package com.assistant.aiassistant;

import com.assistant.aiassistant.chatItemCreatorClasses.ChatItemCreator;
import com.assistant.aiassistant.query_API.ExamplequeryResolutionStrategy;
import com.assistant.aiassistant.query_API.QueryResolutionForm;
import com.assistant.aiassistant.query_API.QueryResolutionResult;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.io.IOException;
import java.util.*;

public class MainController {
    public Button newChatButton;
    public Button hideSidebarButton;
    public Button showSidebarButton;
    public VBox sideBar;
    private Stage primaryStage;
    public VBox chatNavigationBar;
    public VBox chatBox;
    public Label chatTitle;
    public TextArea bericht;
    public ScrollPane convScrollPane;
    public SplitPane root;

    private static final String FILE_PATH = "files/";
    public ArrayList<Conversation> savedConversations;
    private final ArrayList<String> createdConversations = new ArrayList<>();

    public AccountManager accountManager;
    public ResourceBundle bundle;

    private static MainController instance = null;

    private int splitPaneDividerPositionPX = 170;

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    public static MainController getInstance() {
        if(instance == null) {
            instance = new MainController();
        }
        return instance;
    }

    @FXML
    private void handleNewChat() {
        showNewChatPopup();
    }

    public void initialize() {
        instance = this;
        accountManager = AccountManager.getInstance();
        loadResourceBundle();
        loadSavedConversations();
        createChatScrollListener();
        fileCreationListener();
        initializeMessagebox();
        loadConversations();
        createResizeListener();
        createSplitpaneDividerChangedListener();
        showSidebarButton.setVisible(false);
        addBerichtTypingListener();


        bericht.setDisable(true);
        bericht.setPromptText(bundle.getString("messagePrompt")); // Bericht...
        chatTitle.setText(bundle.getString("noChat")); // Geen Chat
    }

    private void addBerichtTypingListener() {
        bericht.textProperty().addListener((observable, oldValue, newValue) -> {
            Text text = new Text(newValue);
            text.setFont(bericht.getFont());
            text.setWrappingWidth(bericht.getWidth());

            double textHeight = text.getLayoutBounds().getHeight();
            double prefHeight = textHeight + bericht.getPadding().getTop() + bericht.getPadding().getBottom() + 12;
            if(prefHeight < 75) {
                bericht.setPrefHeight(prefHeight); // Padding + some space
            } else {
                bericht.setPrefHeight(75);
            }
        });
    }

    private void createSplitpaneDividerChangedListener() {
        root.getDividers().getFirst().positionProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.intValue() == 0) {
                return;
            }

            double width = root.getWidth();
            double doubleValue = newValue.doubleValue() * width;
            splitPaneDividerPositionPX = (int) doubleValue;
        });
    }

    private void createResizeListener() {
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            if(sideBar.isVisible()) {
                double width = root.getWidth();
                root.setDividerPosition(0, splitPaneDividerPositionPX / width);
            } else {
                root.setDividerPosition(0, 0);
            }
        });
    }

    private void loadConversations() {
        LoadSavedConversationAction action = new LoadSavedConversationAction();
        action.execute();
        savedConversations = action.savedConversations;
    }

    private void fileCreationListener() {
        FileCreationMonitor monitor = new FileCreationMonitor(new File("files/conversations/" + accountManager.getActiveUser().getUsername()));
        monitor.addObserver(fileName -> Platform.runLater(() -> {
            // fileName is the name of the file that was created
            String topic = fileName.replace(".txt", "");
            createChatNavigationButton(topic);
        }));
        monitor.start();
    }

    private void createChatScrollListener() {
        chatBox.heightProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> convScrollPane.setVvalue(1.0)));
    }

    private void loadResourceBundle() {
        Locale appLocale = new Locale(accountManager.getActiveUser().getPreferredLanguage());
        bundle = ResourceBundle.getBundle("MessageBundle", appLocale);
    }

    public void refreshLanguage() {
        loadResourceBundle();
        initialize();
    }

    private void initializeMessagebox() {
        bericht.setOnKeyPressed(e -> {
            if (e.getCode().toString().equals("ENTER")) {
                String message = bericht.getText();
                message = message.replace("\n", "");
                bericht.clear();
                handleMessage(message);
            }
        });
        bericht.setWrapText(true);
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
                        VBox answerBubble = ChatItemCreator.createAnswerBubble(contents);
                        chatBox.getChildren().add(answerBubble);
                    } else {
                        VBox questionBubble = ChatItemCreator.createQuestionBubble(message);
                        chatBox.getChildren().add(questionBubble);
                    }
                }
                break;
            }
        }
    }

    private void showNewChatPopup() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);

        VBox dialogVBox = new VBox(10);
        Label label = new Label(bundle.getString("newChatDialog")); // New chat dialog
        TextField textField = new TextField();
        Button confirmButton = new Button(bundle.getString("startChat")); // Start Chat
        Button cancelButton = new Button(bundle.getString("cancel")); // Start Chat

        HBox buttonsHBox = new HBox();
        buttonsHBox.getChildren().addAll(confirmButton, cancelButton);

        Label errorLabel = new Label("");

        confirmButton.setOnAction(e -> {
            String topic = textField.getText();
            if(topic.isEmpty()) {
                errorLabel.setText(bundle.getString("emptyInput"));
                return;
            }
            if(getConversationWithTopic(topic) != null) {
                errorLabel.setText(bundle.getString("chatExists"));
                return;
            }

            new StartNewConversationAction(topic, "AI-Hello! How can I help you?").execute();
            loadConversations();
            showChat(topic);

            dialog.close();
        });

        cancelButton.setOnAction(e -> dialog.close());

        dialogVBox.getChildren().addAll(label, textField, buttonsHBox, errorLabel);

        Scene dialogScene = new Scene(dialogVBox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.setTitle(bundle.getString("newChat")); // New Chat
        dialog.show();
    }

    private Conversation getCurrentlyShowingConversation() {
        String topic = chatTitle.getText();
        return getConversationWithTopic(topic);
    }

    public Conversation getConversationWithTopic(String topic) {
        for (Conversation conversation : savedConversations) {
            if (conversation.getTopic().equals(topic)) {
                return conversation;
            }
        }
        return null;
    }


    private void handleMessage(String message) {
        if (message.isBlank()) {
            return;
        }

        Conversation conversation = getCurrentlyShowingConversation();

        if (conversation == null) {
            return;
        }

        // save message to the appropriate conversation file
        FileIOManager.addMessageToConversation(message, conversation);

        // create text bubble in gui, with the question in it
        VBox questionBubble = ChatItemCreator.createQuestionBubble(message);
        chatBox.getChildren().add(questionBubble);

        // geef message door aan AI
        sendQueryToAI(message);
    }

    private void sendQueryToAI(String query) {

        Conversation conversation = getCurrentlyShowingConversation();

        if (conversation == null) {
            return;
        }

        QueryResolutionForm<String> queryForm = new QueryResolutionForm<>(query);

        ExamplequeryResolutionStrategy exampleStrategy = new ExamplequeryResolutionStrategy();
        QueryResolutionResult<String> result = exampleStrategy.resolve(queryForm);

        String resultData = result.getData();

        FileIOManager.addMessageToConversation("AI-" + resultData, conversation);

        VBox questionBubble = ChatItemCreator.createAnswerBubble(resultData);
        chatBox.getChildren().add(questionBubble);
    }

    public void showSettings() throws IOException {
        UserInterfaceManager uiManager = UserInterfaceManager.getInstance();
        uiManager.switchCurrentViewTo(uiManager.settingsViewFilename);
    }

    @FXML
    public void hideSidebar() {
        sideBar.setManaged(false);
        sideBar.setVisible(false);

        showSidebarButton.setVisible(true);

        Platform.runLater(() -> root.getDividers().getFirst().setPosition(0));
    }

    @FXML
    public void showSidebar() {
        sideBar.setManaged(true);
        sideBar.setVisible(true);

        showSidebarButton.setVisible(false);

        Platform.runLater(() -> root.getDividers().getFirst().setPosition(splitPaneDividerPositionPX/root.getWidth()));
    }

    @FXML
    public void logOut() throws IOException{
        UserInterfaceManager.getInstance().switchCurrentViewTo(UserInterfaceManager.getInstance().loginViewFilename);
        AccountManager.getInstance().logout();
    }
}