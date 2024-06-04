package com.assistant.aiassistant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoadSavedConversationActionTest {
    private LoadSavedConversationAction action;
    private FileIOManager fileIOManager;
    private Conversation conversation;

    @BeforeEach
    void setUp() throws IOException {
        action = new LoadSavedConversationAction();
        fileIOManager = new FileIOManager();
        Files.createDirectories(Paths.get("files/conversations"));
        List<String> messages = List.of("Message 1", "Message 2");
        conversation = new Conversation("TestTopic", messages);
        FileIOManager.saveConversation(conversation);
    }

    @Test
    void testExecute() {
        List<String> savedConversations = FileIOManager.getSavedConversations();
        assertTrue(savedConversations.contains("TestTopic"));
    }
}
