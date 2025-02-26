package com.cdpchatbot.controller;

import com.cdpchatbot.service.ChatbotService;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@RestController
@RequestMapping("/api/chatbot")
public class ChatbotControlle {
    private final ChatbotService chatbotService;
    private static final Logger logger = LoggerFactory.getLogger(ChatbotControlle.class);


    public ChatbotControlle(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @GetMapping("/ask")
    public String askQuestion(@RequestParam String question) {
        return chatbotService.getAnswer(question);
    }
}
