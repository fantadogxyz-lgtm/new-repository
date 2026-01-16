package com.example.simple_chat.reqClasses;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MessageRequest {
    private String messageContent;
    private String sender;
}
