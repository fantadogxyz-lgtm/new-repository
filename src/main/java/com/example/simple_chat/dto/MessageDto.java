package com.example.simple_chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MessageDto {
    private int id;
    private String messageContent;
    private OffsetDateTime sendTime;
    private String sender;
}
