package com.example.chatbot.controller;

import com.example.chatbot.model.ChatMessage;
import com.example.chatbot.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ChatController {
    private final ChatService service;

    public ChatController(ChatService service){
        this.service = service;
    }

    @GetMapping("/history")
    public ResponseEntity<List<ChatMessage>> history(){
        return ResponseEntity.ok(service.getHistory());
    }

    @PostMapping("/chat")
    public ResponseEntity<ChatMessage> chat(@RequestBody Map<String, String> payload){
        String text = payload.getOrDefault("message", "");
        ChatMessage reply = service.handleUserMessage(text);
        return ResponseEntity.ok(reply);
    }
}
