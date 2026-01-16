package com.example.simple_chat.services;

import com.example.simple_chat.dto.MessageDto;
import com.example.simple_chat.reqClasses.MessageRequest;
import com.example.simple_chat.entity.Message;
import com.example.simple_chat.mapper.MessageMapper;
import com.example.simple_chat.repository.MessageRepository;
import com.example.simple_chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;



@Service
public class MessageServiceImpl implements MessageService{


    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final MessageMapper messageMapper;

    @Autowired
    MessageServiceImpl(MessageRepository messageRepository, MessageMapper messageMapper, UserRepository userRepository){
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
        this.userRepository = userRepository;
    }
   @Override
    @Transactional
    public MessageDto postMessageIntoDb(MessageRequest request) {

       return userRepository.findByUsername(request.getSender())
               .map(user -> {
                Message message = Message.builder()
                        .messageContent(request.getMessageContent())
                        .sender(user)
                        .sendTime(OffsetDateTime.now())
                        .build();
                messageRepository.save(message);
                return messageMapper.toDto(message);
               }).orElse(null);
    }

    @Override
    @Transactional
    public Iterable<MessageDto> getListOfMassages() {
        return messageRepository.findAllAsDto();
    }
}
