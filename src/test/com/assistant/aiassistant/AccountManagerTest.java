package com.assistant.aiassistant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AccountManagerTest {

    private AccountManager accountManager;
    private FileIOManager fileIOManager;

    @BeforeEach
    public void setUp() {
        accountManager = AccountManager.getInstance();
        fileIOManager = new FileIOManager() {
            private ArrayList<User> users = new ArrayList<>(Arrays.asList(
                    new User("user1", "pass1", "email1@test.com", "John", "Doe", "EN", "EN"),
                    new User("user2", "pass2", "email2@test.com", "Jane", "Doe", "EN", "EN")
            ));

            @Override
            public ArrayList<User> getUsersFromFile() {
                return users;
            }

            @Override
            public void editUser(User user, String nieuw, String aspect) {
                switch (aspect) {
                    case "email":
                        user.setEmail(nieuw);
                        break;
                    // Voeg andere attributen toe indien nodig
                    default:
                        throw new IllegalArgumentException("Onbekend aspect: " + aspect);
                }
            }

            @Override
            public void saveUserToFile(User user) {
                users.add(user);
            }
        };
        accountManager.fileManager = fileIOManager;
    }

    @Test
    public void testLogin_DecisionTable() {
        // Decision Table for login method
        assertTrue(accountManager.login("email1@test.com", "pass1"));
        assertFalse(accountManager.login("email1@test.com", "wrongpass"));
        assertFalse(accountManager.login("email2@test.com", "pass1"));
        assertFalse(accountManager.login("unknown@test.com", "pass1"));
    }

    @Test
    public void testCheckUsernamePassword_DecisionTable() {
        // Decision Table for checkUsernamePassword method
        assertTrue(accountManager.checkUsernamePassword("user1", "pass1"));
        assertFalse(accountManager.checkUsernamePassword("user1", "wrongpass"));
        assertFalse(accountManager.checkUsernamePassword("user2", "pass1"));
        assertFalse(accountManager.checkUsernamePassword("unknown", "pass1"));
    }

    @Test
    public void testCreateAccount_EquivalentieklassenRandwaarden() {
        // Equivalentieklassen en Randwaarden voor createAccount
        accountManager.createAccount("user3", "pass3", "email3@test.com", "New", "User", "EN", "EN");
        assertTrue(accountManager.checkIfUserWithUsernameExists("user3"));
        assertFalse(accountManager.checkIfUserWithUsernameExists("unknown"));

        accountManager.createAccount("user4", "pass4", "email4@test.com", "New", "User", "EN", "EN");
        assertTrue(accountManager.checkIfUserWithEmailExists("email4@test.com"));
        assertFalse(accountManager.checkIfUserWithEmailExists("unknown@test.com"));
    }

    @Test
    public void testChangeEmail_EquivalentieklassenRandwaarden() {
        // Equivalentieklassen en Randwaarden voor changeEmail
        ChangePersonalData changePersonalData = new ChangePersonalData();
        changePersonalData.fileIOManager = fileIOManager;

        User user = new User("user5", "pass5", "email5@test.com", "Change", "Email", "EN", "EN");
        changePersonalData.users.add(user);
        fileIOManager.saveUserToFile(user);

        System.out.println("Before change: " + user.getEmail());
        changePersonalData.changeEmail(user, "newemail@test.com");
        System.out.println("After change: " + user.getEmail());
        assertEquals("newemail@test.com", user.getEmail());

        // Debug output
        changePersonalData.users.forEach(u -> System.out.println(u.getEmail()));

        assertThrows(IllegalArgumentException.class, () -> {
            // Test with an already existing email
            changePersonalData.changeEmail(user, "email1@test.com");
        });
    }

    @Test
    public void testCheckEmailPassword_ConditionCoverage() {
        // Condition Coverage voor checkEmailPassword
        assertTrue(accountManager.checkEmailPassword("email1@test.com", "pass1"));
        assertFalse(accountManager.checkEmailPassword("email1@test.com", "wrongpass"));
        assertFalse(accountManager.checkEmailPassword("unknown@test.com", "pass1"));
    }

    @Test
    public void testCheckEmail_MultipleConditionCoverage() {
        // Multiple Condition Coverage voor checkEmail
        ChangePersonalData changePersonalData = new ChangePersonalData();
        changePersonalData.fileIOManager = fileIOManager;
        changePersonalData.users = fileIOManager.getUsersFromFile();

        assertTrue(changePersonalData.checkEmail("newemail@test.com"));
        assertFalse(changePersonalData.checkEmail("email1@test.com"));
    }
}
