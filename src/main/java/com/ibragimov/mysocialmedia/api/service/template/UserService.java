package com.ibragimov.mysocialmedia.api.service;

import com.ibragimov.mysocialmedia.api.model.User;

import java.util.List;
import java.util.UUID;


public interface UserService {


    User registerUser(User user);


    User findUserById(UUID userId) throws Exception;


    User findUserByEmail(String email);


    User followUser(UUID userId1, UUID userId2) throws Exception;


    User updateUser(User user, UUID userId) throws Exception;


    List<User> searchUser(String query);

    User findUserByJwt(String jwt);

}
