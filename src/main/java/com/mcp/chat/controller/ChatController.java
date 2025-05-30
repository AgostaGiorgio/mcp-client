package com.mcp.chat.controller;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mcp.chat.service.ChatService;

import io.micrometer.common.lang.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ChatController {

    @NonNull
    private final ChatService chatService;

    @GetMapping("/chat")
    public ChatResponse chat(@RequestParam String userMessage, @RequestParam(required = false) String chatId) {
        return chatService.chat(userMessage, chatId);
    }

}
