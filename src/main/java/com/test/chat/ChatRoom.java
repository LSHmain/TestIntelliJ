package com.test.chat;
import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor
@Table(name = "chat_room")
public class ChatRoom {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=100)
    private String name;
}