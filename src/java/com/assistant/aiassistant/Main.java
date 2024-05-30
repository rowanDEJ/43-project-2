package com.assistant.aiassistant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // start het user interface
        UserInterfaceManager.getInstance().start(stage);
    }
    public static void main(String[] args) {
        launch();
    }
}