package com.test.chat;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity @Getter @Setter @NoArgsConstructor
@Table(name="chat_message",
        indexes = {
                @Index(name="idx_room_created", columnList="room_id, created_at"),
                @Index(name="idx_room", columnList="room_id")
        })
public class ChatMessage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="room_id", nullable=false)
    private ChatRoom room;

    @Column(nullable=false, length=50)
    private String senderId;

    @Column(nullable=false, length=2000)
    private String content;

    @Column(nullable=false, length=20)
    private String messageType = "text"; // text/image/system 등

    @CreationTimestamp
    @Column(name="created_at", updatable=false)
    private LocalDateTime createdAt;
}