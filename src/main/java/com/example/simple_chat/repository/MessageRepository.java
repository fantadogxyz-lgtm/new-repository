package com.example.simple_chat.repository;

import com.example.simple_chat.dto.MessageDto;
import com.example.simple_chat.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query("SELECT new com.example.simple_chat.dto.MessageDto(" + "m.id, m.messageContent, m.sendTime, m.sender.username) " + "FROM Message m")
    List<MessageDto> findAllAsDto();
}
