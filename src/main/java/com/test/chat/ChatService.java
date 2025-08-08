package com.test.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor @Transactional
public class ChatService {
    private final ChatRoomRepository roomRepo;
    private final ChatMessageRepository msgRepo;

    public ChatRoom createRoom(String name) {
        ChatRoom r = new ChatRoom();
        r.setName(name);
        return roomRepo.save(r);
    }

    public MessageRes sendMessage(Long roomId, String senderId, String content, String type) {
        ChatRoom room = roomRepo.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Room not found: " + roomId));
        ChatMessage m = new ChatMessage();
        m.setRoom(room);
        m.setSenderId(senderId);
        m.setContent(content);
        m.setMessageType(type == null || type.isBlank() ? "text" : type);
        ChatMessage saved = msgRepo.save(m);
        return new MessageRes(saved.getId(), roomId, saved.getSenderId(),
                saved.getContent(), saved.getMessageType(), saved.getCreatedAt());
    }

    @Transactional(readOnly = true)
    public Page<MessageRes> getMessages(Long roomId, int page, int size) {
        Page<ChatMessage> p = msgRepo.findByRoom_IdOrderByCreatedAtDesc(roomId, PageRequest.of(page, size));
        return p.map(m -> new MessageRes(
                m.getId(), roomId, m.getSenderId(), m.getContent(), m.getMessageType(), m.getCreatedAt()
        ));
    }
}