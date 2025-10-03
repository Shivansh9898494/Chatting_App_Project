package com.example.chatting_app;

public class Chat_Model {

    String Message;
    String Sender_Name;
    String Sender_documentID;
    int Sender_AvtarIndex;
    String Reciver_Name;
    String Reciver_documentID;


    public Chat_Model(String Message, String Sender_Name, String Sender_documentID, int Sender_AvtarIndex, String Reciver_Name, String Reciver_documentID) {
        this.Message = Message;
        this.Sender_Name = Sender_Name;
        this.Sender_documentID = Sender_documentID;
        this.Sender_AvtarIndex = Sender_AvtarIndex;
        this.Reciver_Name = Reciver_Name;
        this.Reciver_documentID = Reciver_documentID;

    }

    public Chat_Model() {
    }
    public String getMessage() {
        return Message;
    }
    public void setMessage(String message) {
        Message = message;
    }
    public int getSender_AvtarIndex() {
        return Sender_AvtarIndex;
    }
    public void setSender_AvtarIndex(Integer sender_AvtarIndex) {
        Sender_AvtarIndex = sender_AvtarIndex;
    }

    public String getSender_documentID() {
        return Sender_documentID;
    }
    public void setSender_documentID(String sender_documentID) {
        Sender_documentID = sender_documentID;
    }
    public String getReciver_documentID() {
        return Reciver_documentID;
    }
    public void setReciver_documentID(String reciver_documentID) {
        Reciver_documentID = reciver_documentID;
    }
    public String getSender_Name() {
        return Sender_Name;
    }
    public void setSender_Name(String sender_Name) {
        Sender_Name = sender_Name;
    }
    public String getReciver_Name() {
        return Reciver_Name;
    }
    public void setReciver_Name(String reciver_Name) {
        Reciver_Name = reciver_Name;
    }

}
