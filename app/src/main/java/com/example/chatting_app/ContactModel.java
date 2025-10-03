package com.example.chatting_app;

// Example ContactModel
public class ContactModel {


    public String username;
    public int avatarIndex;
    public String password;
    public String documentId;
    public Boolean status;

    public ContactModel() {
    }
    public ContactModel(String username,String password,int avatarIndex,String documentId) {
        this.username = username;
        this.avatarIndex = avatarIndex;
        this.password = password;
        this.documentId = documentId;

    }

    public ContactModel(String username,String password,int avatarIndex,String documentId,Boolean status) {
        this.username = username;
        this.avatarIndex = avatarIndex;
        this.password = password;
        this.documentId = documentId;
        this.status = status;

    }


}
