package com.assistant.aiassistant;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class AccountManagerTest {

    private AccountManager accountManager;
    private FileIOManager fileManager;

    @BeforeEach
    public void setUp() {
        fileManager = new FileIOManager() {
            private ArrayList<User> users = new ArrayList<>();

            @Override
            public ArrayList<User> getUsersFromFile() {
                if (users.isEmpty()) {
                    users.add(new User("testUser1", "password1", "email1@example.com", "First1", "Last1", "EN"));
                    users.add(new User("testUser2", "password2", "email2@example.com", "First2", "Last2", "EN"));
                }
                return users;
            }

            @Override
            public void saveUserToFile(User userToSave) {
                users.add(userToSave);
            }

            @Override
            public void rewriteUsersToFile(ArrayList<User> users) {
                this.users = users;
            }
        };

        accountManager = AccountManager.getInstance();
        accountManager.fileManager = fileManager;
        accountManager.logout();  // Ensuring no user is logged in at the start of each test
    }

    // Decision table for login method
    @Test
    public void testLoginDecisionTable() {
        assertTrue(accountManager.login("email1@example.com", "password1")); // true, true
        assertFalse(accountManager.login("email1@example.com", "wrongpassword")); // true, false
        assertFalse(accountManager.login("wrongemail@example.com", "password1")); // false, true
        assertFalse(accountManager.login("wrongemail@example.com", "wrongpassword")); // false, false
    }

    // Decision table for checkIfUserWithUsernameExists method
    @Test
    public void testCheckIfUserWithUsernameExistsDecisionTable() {
        assertTrue(accountManager.checkIfUserWithUsernameExists("testUser1")); // true
        assertFalse(accountManager.checkIfUserWithUsernameExists("nonexistentUser")); // false
    }

    // Equivalence partitioning and boundary value analysis for checkUsernamePassword method
    @Test
    public void testCheckUsernamePasswordEquivalencePartitioningAndBoundaryValue() {
        assertTrue(accountManager.checkUsernamePassword("testUser1", "password1")); // valid
        assertFalse(accountManager.checkUsernamePassword("wrongUser", "password1")); // invalid username
        assertFalse(accountManager.checkUsernamePassword("testUser1", "wrongPassword")); // invalid password
        assertFalse(accountManager.checkUsernamePassword("", "")); // boundary value: empty username and password
    }

    // Equivalence partitioning and boundary value analysis for checkEmailPassword method
    @Test
    public void testCheckEmailPasswordEquivalencePartitioningAndBoundaryValue() {
        assertTrue(accountManager.checkEmailPassword("email1@example.com", "password1")); // valid
        assertFalse(accountManager.checkEmailPassword("wrongEmail@example.com", "password1")); // invalid email
        assertFalse(accountManager.checkEmailPassword("email1@example.com", "wrongPassword")); // invalid password
        assertFalse(accountManager.checkEmailPassword("", "")); // boundary value: empty email and password
    }

    // Decision, condition, and multiple condition coverage for setUserWithUsername method
    @Test
    public void testSetUserWithUsernameDecisionConditionCoverage() {
        accountManager.setUserWithUsername("testUser1");
        assertNotNull(accountManager.getActiveUser());
        assertEquals("testUser1", accountManager.getActiveUser().getUsername());

        accountManager.setUserWithUsername("nonexistentUser");
        assertNull(accountManager.getActiveUser());
    }

    // Test for createAccount method
    @Test
    public void testCreateAccount() {
        accountManager.createAccount("newUser", "newPass", "newEmail@example.com", "New", "User", "EN");
        assertTrue(accountManager.checkIfUserWithUsernameExists("newUser"));
        assertTrue(accountManager.checkIfUserWithEmailExists("newEmail@example.com"));
    }

    // Test for createAccount method with existing username
    @Test
    public void testCreateAccountExistingUsername() {
        int initialUserCount = fileManager.getUsersFromFile().size();
        accountManager.createAccount("testUser1", "newPass", "newEmail2@example.com", "New", "User", "EN");
        int currentUserCount = fileManager.getUsersFromFile().size();
        assertFalse(accountManager.checkIfUserWithEmailExists("newEmail2@example.com"));
        assertEquals(initialUserCount, currentUserCount);
        // We expect the old username to remain and the new user with the same username to be ignored
        assertEquals(1, fileManager.getUsersFromFile().stream().filter(u -> u.getUsername().equals("testUser1")).count());
    }

    // Test for logout method
    @Test
    public void testLogout() {
        accountManager.setUserWithUsername("testUser1");
        accountManager.logout();
        assertNull(accountManager.getActiveUser());
    }

    // Test for checkIfUserWithEmailExists method
    @Test
    public void testCheckIfUserWithEmailExists() {
        assertTrue(accountManager.checkIfUserWithEmailExists("email1@example.com"));
        assertFalse(accountManager.checkIfUserWithEmailExists("nonexistentEmail@example.com"));
    }

    // Test for checkIfUserWithFullNameExists method
    @Test
    public void testCheckIfUserWithFullNameExists() {
        assertTrue(accountManager.checkIfUserWithFullNameExists("First1 Last1"));
        assertFalse(accountManager.checkIfUserWithFullNameExists("Non Existent"));
    }
}
