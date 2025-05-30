package com.mcp.chat.service;

import org.springframework.ai.chat.model.ChatResponse;

public interface ChatService {
    
    ChatResponse chat(String userMessage, String chatId);
}
