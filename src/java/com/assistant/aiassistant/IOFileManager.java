package com.assistant.aiassistant;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IOFileManager {
    // Directory path where conversation files are stored
    private static final String DIRECTORY_PATH = "src/";

    public static void saveConversation(Conversation conversation) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DIRECTORY_PATH + conversation.getTopic() + ".txt"))) {
            for (String msg : conversation.getMessage()) {
                writer.write(msg);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadConversation(Conversation conversation) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(DIRECTORY_PATH + conversation.getTopic() + ".txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                conversation.addMessage(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getSavedConversations() {
        try {
            return Files.walk(Paths.get(DIRECTORY_PATH))
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".txt"))
                    .map(path -> path.getFileName().toString().replaceFirst("[.][^.]+$", ""))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void deleteConversation(Conversation conversation) {
        File file = new File(DIRECTORY_PATH + conversation.getTopic() + ".txt");
        if (file.delete()) {
            System.out.println("Conversation deleted successfully");
        } else {
            System.out.println("Failed to delete the conversation");
        }
    }
}