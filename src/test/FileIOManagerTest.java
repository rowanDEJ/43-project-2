package com.assistant.aiassistant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileIOManagerTest {
    private FileIOManager fileIOManager;
    private User user;

    @BeforeEach
    void setUp() {
        fileIOManager = new FileIOManager();
        user = new User("testUser", "password", "first", "last", "test@example.com", "EN");
    }

    @Test
    void testSaveAndReadUser() throws IOException {
        fileIOManager.saveUserToFile(user);
        List<User> users = fileIOManager.getUsersFromFile();

        assertFalse(users.isEmpty());
        assertEquals("testUser", users.get(0).getUsername());

        Files.deleteIfExists(Paths.get("files/gebruikers.txt"));
    }
}