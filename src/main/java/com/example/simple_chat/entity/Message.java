package com.example.simple_chat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "message_content", nullable = false)
    private String messageContent;

    @Column(name = "send_time", nullable = false)
    private OffsetDateTime sendTime;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

}