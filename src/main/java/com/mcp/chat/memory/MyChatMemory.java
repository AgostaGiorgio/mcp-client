package com.mcp.chat.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.context.annotation.Bean;

import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class MyChatMemory implements ChatMemory{

    @Getter
    private final Map<String, List<Message>> memory = new ConcurrentHashMap<>();
    

    @Bean
    public MyChatMemory customChatMemory() {
        return new MyChatMemory();
    }

  
    @Override
    public void add(String conversationId, List<Message> messages) {
        this.memory.putIfAbsent(conversationId, new ArrayList<>());
        this.memory.get(conversationId).addAll(messages);
    }

    @Override
    public List<Message> get(String conversationId) {
        return this.memory.getOrDefault(conversationId, new ArrayList<>());
    }

    @Override
    public void clear(String conversationId) {
        this.memory.remove(conversationId);
    }
    
}
