package com.assistant.aiassistant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage currentStage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader LoginFxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));

        Scene loginScene = new Scene(LoginFxmlLoader.load(), 800, 600);

        stage.setTitle("43-AI-Assistant");
        stage.setScene(loginScene);
        stage.show();

        currentStage = stage;
    }
    public static void main(String[] args) {
        launch();
    }

    public static void showMainScreen() throws IOException {
        FXMLLoader MainFxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Scene mainScene = new Scene(MainFxmlLoader.load(), 800, 600);
        currentStage.setScene(mainScene);
        currentStage.show();
    }
}