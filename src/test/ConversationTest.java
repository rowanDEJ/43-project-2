package com.assistant.aiassistant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConversationTest {
    private Conversation conversation;

    @BeforeEach
    void setUp() {
        List<String> messages = new ArrayList<>();
        messages.add("Initial message");
        conversation = new Conversation("Test Topic", messages);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("Test Topic", conversation.getTopic());
        assertEquals(1, conversation.getMessages().size());
        assertEquals("Initial message", conversation.getMessages().get(0));
    }

    @Test
    void testAddMessage() {
        conversation.addMessage("New message");
        assertEquals(2, conversation.getMessages().size());
        assertEquals("New message", conversation.getMessages().get(1));
    }
}