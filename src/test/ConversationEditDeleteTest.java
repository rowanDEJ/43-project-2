package com.assistant.aiassistant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConversationEditDeleteTest {
    private ConversationEditDelete conversationEditDelete;
    private Conversation conversation;

    @BeforeEach
    void setUp() {
        List<String> messages = new ArrayList<>();
        messages.add("Initial message");
        conversation = new Conversation("Test Topic", messages);
        conversationEditDelete = new ConversationEditDelete();
        conversationEditDelete.conversation = conversation;
    }

    @Test
    void testEditMessage() {
        conversationEditDelete.editMessage(0, "Edited message");
        assertEquals("Edited message", conversation.getMessages().get(0));

        conversationEditDelete.editMessage(1, "Invalid index message");
        assertEquals(1, conversation.getMessages().size());
    }

    @Test
    void testDeleteMessage() {
        conversationEditDelete.deleteMessage(0);
        assertTrue(conversation.getMessages().isEmpty());

        conversationEditDelete.deleteMessage(0); // Trying to delete from an empty list
        assertTrue(conversation.getMessages().isEmpty());
    }

    @Test
    void testSearchMessage() {
        conversationEditDelete.conversation.addMessage("Another message");
        conversationEditDelete.searchMessage("Initial");
        conversationEditDelete.searchMessage("Another");
        conversationEditDelete.searchMessage("Non-existent");
    }
}
