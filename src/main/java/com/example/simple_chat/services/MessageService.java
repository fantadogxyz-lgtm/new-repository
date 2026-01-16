package com.example.simple_chat.services;


import com.example.simple_chat.dto.MessageDto;
import com.example.simple_chat.reqClasses.MessageRequest;

import java.util.List;


public interface MessageService {
    public MessageDto postMessageIntoDb(MessageRequest request);

    public Iterable<MessageDto> getListOfMassages();
}
