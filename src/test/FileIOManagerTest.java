package com.assistant.aiassistant;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileIOManagerTest {

    private FileIOManager fileIOManager;
    private static final String TEST_DIRECTORY_PATH = "files/";
    private static final String TEST_USERS_FILE_PATH = TEST_DIRECTORY_PATH + "test_gebruikers.txt";
    private static final String TEST_CONVERSATION_FILE_PATH = TEST_DIRECTORY_PATH + "test_conversations/";

    @BeforeEach
    public void setUp() {
        fileIOManager = new FileIOManager();
        setupTestFiles();
    }

    private void setupTestFiles() {
        // Setup test users file
        File testUsersFile = new File(TEST_USERS_FILE_PATH);
        if (!testUsersFile.exists()) {
            try {
                testUsersFile.createNewFile();
                try (FileWriter writer = new FileWriter(testUsersFile)) {
                    writer.write("testUser1/~/password1/~/email1@example.com/~/First1/~/Last1/~/EN\n");
                    writer.write("testUser2/~/password2/~/email2@example.com/~/First2/~/Last2/~/EN\n");
                }
            } catch (IOException e) {
                fail("Failed to create test users file: " + e.getMessage());
            }
        }

        // Setup test conversation directory
        File testConversationDir = new File(TEST_CONVERSATION_FILE_PATH);
        if (!testConversationDir.exists()) {
            testConversationDir.mkdirs();
        }

        // Create a test conversation file
        File testConversationFile = new File(TEST_CONVERSATION_FILE_PATH + "testConversation.txt");
        if (!testConversationFile.exists()) {
            try {
                testConversationFile.createNewFile();
                try (FileWriter writer = new FileWriter(testConversationFile)) {
                    writer.write("Hello\nHow are you?\n");
                }
            } catch (IOException e) {
                fail("Failed to create test conversation file: " + e.getMessage());
            }
        }
    }

    @Test
    public void testReadFile() {
        ArrayList<String> data = fileIOManager.readFile(TEST_USERS_FILE_PATH);
        assertNotNull(data);
        assertFalse(data.isEmpty());
        System.out.println("Read file data: " + data);
    }

    @Test
    public void testGetUsersFromFile() {
        ArrayList<User> users = fileIOManager.getUsersFromFile();
        assertNotNull(users);
        assertFalse(users.isEmpty());
        System.out.println("Users from file: " + users);
    }

    @Test
    public void testSaveUserToFile() {
        User user = new User("testUser3", "password3", "email3@example.com", "First3", "Last3", "EN");
        fileIOManager.saveUserToFile(user);

        ArrayList<User> users = fileIOManager.getUsersFromFile();
        System.out.println("Users after saving new user: " + users);

        // We need to check the list from the file, so we retrieve the users again
        boolean userFound = false;
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername())) {
                userFound = true;
                break;
            }
        }
        assertTrue(userFound, "User was not found in the list after saving");
    }

    @Test
    public void testRemoveUserFromFile() {
        User user = new User("testUser1", "password1", "email1@example.com", "First1", "Last1", "EN");
        fileIOManager.removeUserFromFile(user);
        ArrayList<User> users = fileIOManager.getUsersFromFile();
        assertFalse(users.contains(user));
        System.out.println("Users after removing user: " + users);
    }

    @Test
    public void testEditUser() {
        User user = new User("testUser1", "password1", "email1@example.com", "First1", "Last1", "EN");
        fileIOManager.editUser(user, "newPassword", "password");
        ArrayList<User> users = fileIOManager.getUsersFromFile();
        for (User u : users) {
            if (u.getUsername().equals("testUser1")) {
                assertEquals("newPassword", u.getPassword());
            }
        }
        System.out.println("Users after editing user: " + users);
    }

    @Test
    public void testSaveConversation() {
        ArrayList<String> messages = new ArrayList<>();
        messages.add("Hello");
        messages.add("How are you?");
        Conversation conversation = new Conversation("testConversation", messages);
        FileIOManager.saveConversation(conversation);

        File file = new File(TEST_CONVERSATION_FILE_PATH + "testConversation.txt");
        assertTrue(file.exists());
        System.out.println("Conversation file exists: " + file.exists());
    }

    @Test
    public void testLoadConversation() {
        ArrayList<String> messages = new ArrayList<>();
        messages.add("Hello");
        messages.add("How are you?");
        Conversation conversation = new Conversation("testConversation", messages);
        FileIOManager.saveConversation(conversation);

        Conversation loadedConversation = new Conversation("testConversation", new ArrayList<>());
        FileIOManager.loadConversation(loadedConversation);

        assertEquals(conversation.getMessages(), loadedConversation.getMessages());
        System.out.println("Loaded conversation messages: " + loadedConversation.getMessages());
    }
}
