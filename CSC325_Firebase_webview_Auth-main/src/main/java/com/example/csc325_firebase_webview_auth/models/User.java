package com.example.csc325_firebase_webview_auth.models;

public class User {
    private static User userInstance;
    private String name;

    private User() {

    }

    public static User getUser() {
        if (userInstance == null) {
            userInstance = new User();
        }

        return userInstance;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
