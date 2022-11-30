package com.example.Socialnetwork.bo;

import com.example.Socialnetwork.db.MessageDB;

import java.util.List;

public interface IMessageService {

    public MessageDB saveMessage(Message message);
    public List<Message> findMessageByReceiver(String receiver);
}
