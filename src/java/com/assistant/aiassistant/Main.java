package com.assistant.aiassistant;

import com.assistant.aiassistant.query_API.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("43-AI-Assistant");
        stage.setScene(scene);
        stage.show();
    }

    public static void executeQuery() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Voer een query in, voor de ai assistent: ");
        String query = scanner.nextLine();

        QueryResolutionForm<String> queryForm = new QueryResolutionForm<>(query);

        ExamplequeryResolutionStrategy exampleStrategy = new ExamplequeryResolutionStrategy();
        QueryResolutionResult<String> result = exampleStrategy.resolve(queryForm);

        System.out.println();
        System.out.println("Query data: " + queryForm.getQueryData());
        System.out.println("Result data: " + result.getData());

        scanner.close();
    }

    public static void main(String[] args) {
        //launch();
        executeQuery();
    }
}