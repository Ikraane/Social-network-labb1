package com.example.Socialnetwork.controller;

import com.example.Socialnetwork.bo.IMessageService;
import com.example.Socialnetwork.bo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
@CrossOrigin
public class MessageController {

    @Autowired
    private IMessageService iMessageService;

    @PostMapping("/add")
    public String addMessage(@RequestBody Message message) {
        iMessageService.saveMessage(message);
        return "New message is added";
    }

    @GetMapping("/getMessages")
    public List<Message> findMessageByReceiver(@RequestParam(value = "receiver", required = false) String receiver) {
        return iMessageService.findMessageByReceiver(receiver);
    }
}
