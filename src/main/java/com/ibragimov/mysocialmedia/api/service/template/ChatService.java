package com.ibragimov.mysocialmedia.api.service;

import com.ibragimov.mysocialmedia.api.model.Chat;
import com.ibragimov.mysocialmedia.api.model.User;
import jdk.jshell.spi.ExecutionControl;

import java.util.List;
import java.util.UUID;

public interface ChatService {

    Chat createChat(User currentUser, User user);

    Chat findChatById(Integer chatId);

    List<Chat> findUsersChat(UUID userId);

}
