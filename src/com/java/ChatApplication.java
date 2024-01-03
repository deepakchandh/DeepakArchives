package com.java;

import java.util.*;

class User {
    private String userId;
    private String username;
    // other user attributes

    public User(String userId, String username) {
        this.userId = userId;
        this.username = username;
        // initialize other attributes
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getters and setters for attributes
    // ...
}

// Message class represents a message in the system
class Message {
    private String messageId;
    private String senderId;
    private String receiverId;
    private String content;
    private Date timestamp;
    // other message attributes

    public Message(String messageId, String senderId, String receiverId, String content) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.timestamp = new Date();
        // initialize other attributes
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    // Getters and setters for attributes
    // ...
}

// MessengerService class handles messaging operations
class MessengerService {
    private Map<String, User> users;
    private List<Message> messages;

    public MessengerService() {
        this.users = new HashMap<>();
        this.messages = new ArrayList<>();
    }

    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public User getUserById(String userId) {
        return users.get(userId);
    }

    public void sendMessage(String senderId, String receiverId, String content) {
        User sender = getUserById(senderId);
        User receiver = getUserById(receiverId);

        if (sender == null || receiver == null) {
            System.out.println("User not found");
            return;
        }

        String messageId = UUID.randomUUID().toString();
        Message message = new Message(messageId, senderId, receiverId, content);
        messages.add(message);

        // Additional logic to handle real-time communication and notifications
        // ...
    }

    public List<Message> getMessagesByUserId(String userId) {
        List<Message> userMessages = new ArrayList<>();

        for (Message message : messages) {
            if (message.getSenderId().equals(userId) || message.getReceiverId().equals(userId)) {
                userMessages.add(message);
            }
        }

        return userMessages;
    }

    // Additional methods for managing conversations, notifications, etc.
    // ...
}
public class ChatApplication {

    public static void main(String[] args) {
        MessengerService messengerService = new MessengerService();

        User user1 = new User("1", "Alice");
        User user2 = new User("2", "Bob");

        messengerService.addUser(user1);
        messengerService.addUser(user2);

        messengerService.sendMessage("1", "2", "Hello, Bob!");
        messengerService.sendMessage("2", "1", "Hi, Alice!");

        List<Message> user1Messages = messengerService.getMessagesByUserId("1");
        for (Message message : user1Messages) {
            System.out.println("Sender: " + message.getSenderId());
            System.out.println("Receiver: " + message.getReceiverId());
            System.out.println("Content: " + message.getContent());
            System.out.println();
        }
    }
}
