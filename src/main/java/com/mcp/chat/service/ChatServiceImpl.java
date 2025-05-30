package com.mcp.chat.service;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

import com.mcp.chat.config.ChatConfig;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    @NonNull
    private final ToolCallbackProvider tools;

    @NonNull
    private final ChatClient chatClient;

    @Override
    public ChatResponse chat(String userMessage, String chatId) {
        final String conversationId = (StringUtils.isEmpty(chatId)) ? UUID.randomUUID().toString() : chatId;
        log.debug("New message for chat {}", conversationId);

        return chatClient.prompt(userMessage)
                .advisors(adv -> adv.param(ChatConfig.CHAT_MEMORY_CONVERSATION_ID_KEY, conversationId))
                .toolCallbacks(tools)
                .call()
                .chatResponse();
    }

}
