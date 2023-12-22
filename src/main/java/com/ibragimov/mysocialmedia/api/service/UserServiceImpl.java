package com.ibragimov.mysocialmedia.api.service.impl;

import com.ibragimov.mysocialmedia.api.model.User;
import com.ibragimov.mysocialmedia.api.repository.UserRepository;
import com.ibragimov.mysocialmedia.api.service.UserService;
import com.ibragimov.mysocialmedia.config.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(User user) {
return null;
    }

    @Override
    public User findUserById(UUID userId) throws Exception {
        Optional<User> byId = userRepository.findById(userId);
        if (byId.isPresent()) {
            return byId.get();
        }

        throw new Exception("User doesnt exist!");
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User followUser(UUID loggedUserId, UUID userId) throws Exception {

       User loggedUser =  findUserById(loggedUserId);
       User user =  findUserById(userId);

       user.getFollowers().add(loggedUser.getId());
       loggedUser.getFollowings().add(user.getId());

       userRepository.save(user);
       userRepository.save(loggedUser);

       return loggedUser;
    }

    @Override
    public User updateUser(User user, UUID userId) throws Exception {
        Optional<User> user1 = userRepository.findById(userId);

        if(user1.isEmpty()) {
            throw new Exception("user not exit with id "+userId);
        }

        User oldUser= user1.get();

        if(user.getFirstName()!=null) {
            oldUser.setFirstName(user.getFirstName());
        }
        if(user.getLastName()!=null) {
            oldUser.setLastName(user.getLastName());
        }
        if(user.getEmail()!=null) {
            oldUser.setEmail(user.getEmail());
        }
        if(user.getGender() != null){
            oldUser.setGender(user.getGender());
        }

        User updatedUser=userRepository.save(oldUser);

        return updatedUser;
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }

    @Override
    public User findUserByJwt(String jwt) {
        String email = JwtProvider.getEmailFromJwtToken(jwt);
        User user = userRepository.findByEmail(email);
        return user;
    }
}
