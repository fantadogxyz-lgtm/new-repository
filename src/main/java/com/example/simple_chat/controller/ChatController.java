package com.example.simple_chat.controller;


import com.example.simple_chat.dto.MessageDto;
import com.example.simple_chat.services.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ChatController {

    private final MessageServiceImpl messageService;

    @Autowired
    public ChatController(MessageServiceImpl messageService){
        this.messageService = messageService;
    }



    @GetMapping("/messages")
    public Iterable<MessageDto> getMessages(){
        return messageService.getListOfMassages();
    }
}
