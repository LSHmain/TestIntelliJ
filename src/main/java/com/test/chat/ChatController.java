package com.test.chat;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:80"})
@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/rooms")
    public ChatRoom createRoom(@RequestBody @Valid RoomCreateReq req) {
        return chatService.createRoom(req.name());
    }

    @PostMapping("/rooms/{roomId}/messages")
    public MessageRes sendMessage(@PathVariable Long roomId,
                                  @RequestBody @Valid MessageSendReq req) {
        return chatService.sendMessage(roomId, req.senderId(), req.content(), req.messageType());
    }

    @GetMapping("/rooms/{roomId}/messages")
    public Page<MessageRes> getMessages(@PathVariable Long roomId,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "20") int size) {
        return chatService.getMessages(roomId, page, size);
    }
}