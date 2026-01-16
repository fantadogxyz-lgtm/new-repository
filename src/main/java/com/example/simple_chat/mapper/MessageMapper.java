package com.example.simple_chat.mapper;

import com.example.simple_chat.dto.MessageDto;
import com.example.simple_chat.entity.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {
    public MessageDto toDto (Message entity){
        return new MessageDto(entity.getId(),
                entity.getMessageContent(),
                entity.getSendTime(),
                entity.getSender().getUsername());
    }
}
