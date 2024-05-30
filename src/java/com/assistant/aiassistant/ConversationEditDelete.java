package com.assistant.aiassistant;
public class ConversationEditDelete {

    Conversation conversation;

    public void editMessage(int index, String newMessage) {
        if (index >= 0 && index < this.conversation.getMessages().size()) {
            this.conversation.getMessages().set(index, newMessage);
        } else {
            System.out.println("Invalid message index");
        }
    }

    public void deleteMessage(int index) {
        if (index >= 0 && index < this.conversation.getMessages().size()) {
            this.conversation.getMessages().remove(index);
        } else {
            System.out.println("Invalid message index");
        }
    }

    public void searchMessage(String query) {
        for (String msg : this.conversation.getMessages()) {
            if (msg.contains(query)) {
                System.out.println(msg);
            }
        }
    }
}
