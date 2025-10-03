package com.example.chatting_app;

public class SignUpModel {
    public String username;
    public String password;
    public int avatarIndex;

    public SignUpModel() {
    }
    public SignUpModel(String username, String password, int avatarIndex) {
        this.username = username;
        this.password = password;
        this.avatarIndex = avatarIndex;
    }
}
