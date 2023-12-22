package com.ibragimov.mysocialmedia.api.service.impl;

import com.ibragimov.mysocialmedia.api.model.Chat;
import com.ibragimov.mysocialmedia.api.model.User;
import com.ibragimov.mysocialmedia.api.service.ChatService;

import java.util.List;
import java.util.UUID;

public class ChatServiceImpl implements ChatService {
    @Override
    public Chat createChat(User currentUser, User user) {
        return null;
    }

    @Override
    public Chat findChatById(Integer chatId) {
        return null;
    }

    @Override
    public List<Chat> findUsersChat(UUID userId) {
        return null;
    }
}
