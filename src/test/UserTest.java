package com.assistant.aiassistant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("username", "password", "email@example.com", "firstName", "lastName", "EN");
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("username", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("firstName", user.getFirstName());
        assertEquals("lastName", user.getLastName());
        assertEquals("email@example.com", user.getEmail());
        assertEquals("EN", user.getPreferredLanguage());
    }

    @Test
    void testSetters() {
        user.setUserName("newUsername");
        user.setPassword("newPassword");
        user.setFirstName("newFirstName");
        user.setLastName("newLastName");
        user.setEmail("newEmail@example.com");
        user.setPreferredLanguage("NL");

        assertEquals("newUsername", user.getUsername());
        assertEquals("newPassword", user.getPassword());
        assertEquals("newFirstName", user.getFirstName());
        assertEquals("newLastName", user.getLastName());
        assertEquals("newEmail@example.com", user.getEmail());
        assertEquals("NL", user.getPreferredLanguage());
    }

    @Test
    void testChangeLanguage() {
        user.changeLanguage();
        assertEquals("NL", user.getPreferredLanguage());
        user.changeLanguage();
        assertEquals("EN", user.getPreferredLanguage());
    }
}