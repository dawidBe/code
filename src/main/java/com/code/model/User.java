package com.code.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User {

    private final int id;
    private String name;
    private List<Message> messages;
    private List<User> followers;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.messages = new ArrayList<>();
        this.followers = new ArrayList<>();
    }

    public User(int id, String name, List<Message> messages, List<User> followers) {
        this.id = id;
        this.name = name;
        this.messages = messages;
        this.followers = followers;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }
}
