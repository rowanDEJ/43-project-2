package com.assistant.aiassistant;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserInterfaceManager {
    private static UserInterfaceManager instance;

    private String currentLanguage;
    private Stage currentStage;

    public final int defaultWidth = 800;
    public final int defaultHeight = 600;

    public String loginViewFilename = "login-view.fxml";
    public String mainViewFilename = "main-view.fxml";
    public String registerViewFilename = "register-view.fxml";

    public UserInterfaceManager() {
        this.currentLanguage = null;
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
        // start het UI (laat het login scherm zien met default grootte, en zet de titel van het window)
        this.currentStage = stage;
        this.currentStage.setTitle("43-AI-Assistant");

        Scene scene = getSceneWithDefaultSize(loginViewFilename);
        this.currentStage.setScene(scene);
        this.currentStage.show();
    }

    public void switchCurrentViewTo(String fxmlFileName) throws IOException {
        // laat een andere fxml view ding zien op het scherm.
        Scene scene = getSceneWithCurrentSize(fxmlFileName);
        this.currentStage.setScene(scene);
        this.currentStage.show();
    }

    private Scene getSceneWithCurrentSize(String fxmlFileName) throws IOException {
        // geeft een Scene object van de gespecificeerde fxml view file, met de huidige maten.
        double currentWidth = this.currentStage.getScene().getWidth();
        double currentHeight = this.currentStage.getScene().getHeight();
        return getScene(fxmlFileName, currentWidth, currentHeight);
    }

    private Scene getSceneWithDefaultSize(String fxmlFileName) throws IOException {
        // geeft een Scene object van de gespecificeerde fxml view file, met de default maten.
        return getScene(fxmlFileName, defaultWidth, defaultHeight);
    }

    private Scene getScene(String fxmlFileName, double width, double height) throws IOException {
        // geeft een Scene object van de gespecificeerde fxml view file ding.
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFileName));
        return new Scene(fxmlLoader.load(), width, height);
    }
}
