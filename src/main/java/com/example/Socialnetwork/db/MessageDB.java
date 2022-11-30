package com.example.Socialnetwork.db;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
public class MessageDB {

    @Id
    @Column(name = "messageID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int messageID;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "sender")
    private String sender;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "message")
    private String message;

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime messageDate) {
        this.date = messageDate;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageID='" + messageID + '\'' +
                ", messageDate=" + date +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

}
