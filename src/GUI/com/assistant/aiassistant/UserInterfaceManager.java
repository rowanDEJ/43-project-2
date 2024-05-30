package com.assistant.aiassistant;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserInterfaceManager {
    private static UserInterfaceManager instance;

    private String currentLanguage;
    private Stage currentStage;
    private String font;
    private String color;

    private int defaultWidth;
    private int defaultHeight;

    public String loginViewFilename = "login-view.fxml";
    public String mainViewFilename = "main-view.fxml";
    public String registerViewFilename = "register-view.fxml";

    public UserInterfaceManager() {
        this.currentLanguage = null;
        this.defaultHeight = 600;
        this.defaultWidth = 800;
        if(AccountManager.getInstance().getActiveUser() != null) {
            this.currentLanguage = AccountManager.getInstance().getActiveUser().getPreferredLanguage();
        }
    }

    public static UserInterfaceManager getInstance() {
        if (instance == null) {
            instance = new UserInterfaceManager();
        }
        return instance;
    }

    public void start(Stage stage) throws IOException {
        this.currentStage = stage;

        this.currentStage.setTitle("43-AI-Assistant");

        Scene scene = getSceneWithDefaultSize(loginViewFilename);
        this.currentStage.setScene(scene);
        this.currentStage.show();
    }

    public void switchCurrentViewTo(String fxmlFileName) throws IOException {
        Scene scene = getSceneWithCurrentSize(fxmlFileName);
        this.currentStage.setScene(scene);
        this.currentStage.show();
    }

    private Scene getSceneWithCurrentSize(String fxmlFileName) throws IOException {
        return getScene(fxmlFileName, this.currentStage.getScene().getWidth(), this.currentStage.getScene().getHeight());
    }

    private Scene getSceneWithDefaultSize(String fxmlFileName) throws IOException {
        return getScene(fxmlFileName, defaultWidth, defaultHeight);
    }

    private Scene getScene(String fxmlFileName, double width, double height) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFileName));
        return new Scene(fxmlLoader.load(), width, height);
    }


    public void setCurrentLanguage(String currentLanguage) {
        this.currentLanguage = currentLanguage;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCurrentLanguage() {
        return currentLanguage;
    }

    public String getFont() {
        return font;
    }

    public String getColor() {
        return color;
    }

    public void showInfo() {

    }
}
