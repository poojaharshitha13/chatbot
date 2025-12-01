package com.example.chatbot.service;

import com.example.chatbot.model.ChatMessage;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {
    private final List<ChatMessage> history = new ArrayList<>();

    public ChatService(){
        history.add(new ChatMessage("bot", "Hello! I'm SimpleBot. Ask me anything or say 'help'."));
    }

    public List<ChatMessage> getHistory(){
        return new ArrayList<>(history);
    }

    public ChatMessage handleUserMessage(String userText){
        ChatMessage userMsg = new ChatMessage("user", userText);
        history.add(userMsg);

        String reply = generateReply(userText);
        ChatMessage botMsg = new ChatMessage("bot", reply);
        history.add(botMsg);
        return botMsg;
    }

    private String generateReply(String userText){
        if(userText == null || userText.trim().isEmpty()){
            return "Please type something — I'm listening.";
        }
        String s = userText.toLowerCase();

        if(s.contains("hello") || s.contains("hi") || s.contains("hey")){
            return "Hi there! How can I help you today?";
        }
        if(s.contains("how are you")){
            return "I'm a bot — always ready! How are you?";
        }
        if(s.contains("time")){
            return "Current time: " + LocalDateTime.now().toString();
        }
        if(s.contains("help")){
            return "You can ask me simple things: 'hello', 'time', 'your name', or ask for 'topics'.";
        }
        if(s.contains("name")){
            return "I am SimpleBot — a demo chatbot built with Java and Spring Boot.";
        }
        if(s.contains("topics") || s.contains("what can you do")){
            return "I can greet you, show time, echo messages, and maintain history.";
        }
        return "You said: '" + userText + "' — I don't fully understand yet. Try 'help'.";
    }
}
