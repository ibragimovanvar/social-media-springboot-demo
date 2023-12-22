package com.ibragimov.mysocialmedia.api.service.impl;

import com.ibragimov.mysocialmedia.api.model.Reels;
import com.ibragimov.mysocialmedia.api.model.User;
import com.ibragimov.mysocialmedia.api.repository.ReelsRepository;
import com.ibragimov.mysocialmedia.api.service.ReelsService;
import com.ibragimov.mysocialmedia.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReelsServiceImpl implements ReelsService {

    @Autowired
    private ReelsRepository reelsRepository;

    @Autowired
    private UserService userService;

    @Override
    public Reels createReels(Reels reels, UUID userId) throws Exception {
        User user = userService.findUserById(userId);
        Reels newReels = new Reels();
        newReels.setUser(user);
        newReels.setTitle(reels.getTitle());
        newReels.setVideo(reels.getVideo());

        return reelsRepository.save(newReels);
    }

    @Override
    public Reels getReelsById(UUID reelsId) throws FileNotFoundException {

        Optional<Reels> reelsOptional = reelsRepository.findById(reelsId);
        if (reelsOptional.isEmpty()) {
            throw new FileNotFoundException("Not found reels with this id:" + reelsId);
        }

        return reelsOptional.get();
    }

    @Override
    public List<Reels> findReelsByUserId(UUID userId) throws Exception {
        User userById = userService.findUserById(userId);
        return reelsRepository.findByUserId(userById.getId());
    }

    @Override
    public List<Reels> findAllReels() {
        return reelsRepository.findAll();
    }
}
