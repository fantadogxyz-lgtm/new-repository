package com.example.simple_chat.controller;

import com.example.simple_chat.reqClasses.MessageRequest;
import com.example.simple_chat.dto.MessageDto;
import com.example.simple_chat.services.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

@Controller
public class MessageController {



    private final MessageServiceImpl messageService;

        @Autowired
        MessageController(MessageServiceImpl messageService){
            this.messageService = messageService;
        }

        @MessageMapping("/send")
        @SendTo("/topic/messages")
        @Transactional
        public MessageDto sendMessage (MessageRequest messageRequest) throws Exception{
            return messageService.postMessageIntoDb(messageRequest);
        }


    }


