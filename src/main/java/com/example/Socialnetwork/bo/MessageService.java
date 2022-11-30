package com.example.Socialnetwork.bo;

import com.example.Socialnetwork.db.MessageDB;
import com.example.Socialnetwork.db.MessageRepository;
import com.example.Socialnetwork.db.PostDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService implements IMessageService{

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public MessageDB saveMessage(Message message) throws IllegalArgumentException{
        if (message == null) throw new IllegalArgumentException();

        else {
            MessageDB messageDB = new MessageDB();
            messageDB.setMessage(message.getMessage());
            messageDB.setDate(LocalDateTime.now());
            messageDB.setReceiver(message.getReceiver());
            messageDB.setSender(message.getSender());

            return messageRepository.save(messageDB);
        }

    }

    @Override
    public List<Message> findMessageByReceiver(String receiver) {
        List<Message> messages = new ArrayList<>();

        for(MessageDB messageDB: messageRepository.findMessageByReceiver(receiver)) {
            Message message = new Message();
            message.setMessage(messageDB.getMessage());
            message.setMessageID(messageDB.getMessageID());
            message.setMessageDate(messageDB.getDate());
            message.setReceiver(messageDB.getReceiver());
            message.setSender(messageDB.getSender());
            messages.add(message);

        }
        return messages;
    }
}
