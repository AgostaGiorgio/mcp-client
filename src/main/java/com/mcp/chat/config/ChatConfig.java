package com.mcp.chat.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mcp.chat.memory.MyChatMemory;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "chat")
public class ChatConfig {

    public static final String CHAT_MEMORY_CONVERSATION_ID_KEY = "chat_memory_conversation_id";

    @NonNull
    private String prompt;

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder, MyChatMemory customChatMemory) {

        return chatClientBuilder
                .defaultSystem(prompt)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(customChatMemory).build())
                .build();
    }

    @Bean
    public MyChatMemory customChatMemory() {
        return new MyChatMemory();
    }

}
