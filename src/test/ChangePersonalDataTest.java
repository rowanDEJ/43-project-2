package com.assistant.aiassistant;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Scanner;

public class ChangePersonalDataTest {

    private ChangePersonalData changePersonalData;
    private FileIOManager fileManager;
    private User testUser;

    @BeforeEach
    public void setUp() {
        fileManager = new FileIOManager() {
            private ArrayList<User> users = new ArrayList<>();

            @Override
            public ArrayList<User> getUsersFromFile() {
                if (users.isEmpty()) {
                    users.add(new User("testUser", "password", "email@example.com", "First", "Last", "EN"));
                }
                return users;
            }

            @Override
            public void editUser(User user, String newValue, String aspect) {
                for (User u : users) {
                    if (u.getUsername().equals(user.getUsername())) {
                        switch (aspect) {
                            case "username":
                                u.setUserName(newValue);
                                break;
                            case "password":
                                u.setPassword(newValue);
                                break;
                            case "email":
                                u.setEmail(newValue);
                                break;
                            case "firstname":
                                u.setFirstName(newValue);
                                break;
                            case "lastname":
                                u.setLastName(newValue);
                                break;
                            case "preferredLanguage":
                                u.setPreferredLanguage(newValue);
                                break;
                        }
                    }
                }
            }
        };

        changePersonalData = new ChangePersonalData();
        changePersonalData.fileIOManager = fileManager;
        testUser = new User("testUser", "password", "email@example.com", "First", "Last", "EN");
    }

    @Test
    public void testChangeUsername() {
        Scanner scanner = new Scanner("newUsername\n");
        changePersonalData.changeUsername(testUser, scanner);
        assertEquals("newUsername", testUser.getUsername());
    }

    @Test
    public void testChangePassword() {
        Scanner scanner = new Scanner("newPassword\n");
        changePersonalData.changePassword(testUser, scanner);
        assertEquals("newPassword", testUser.getPassword());
    }

    @Test
    public void testChangeEmail() {
        Scanner scanner = new Scanner("newEmail@example.com\n");
        changePersonalData.changeEmail(testUser, scanner);
        assertEquals("newEmail@example.com", testUser.getEmail());
    }

    @Test
    public void testChangeFirstname() {
        Scanner scanner = new Scanner("NewFirst\n");
        changePersonalData.changeFirstname(testUser, scanner);
        assertEquals("NewFirst", testUser.getFirstName());
    }

    @Test
    public void testChangeLastname() {
        Scanner scanner = new Scanner("NewLast\n");
        changePersonalData.changeLastname(testUser, scanner);
        assertEquals("NewLast", testUser.getLastName());
    }

    @Test
    public void testChangePreferredLanguage() {
        Scanner scanner = new Scanner("NL\n");
        changePersonalData.changePreferredLanguage(testUser, scanner);
        assertEquals("NL", testUser.getPreferredLanguage());
    }
}
