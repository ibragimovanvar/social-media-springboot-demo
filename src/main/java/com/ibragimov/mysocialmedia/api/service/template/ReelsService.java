package com.ibragimov.mysocialmedia.api.service;

import com.ibragimov.mysocialmedia.api.model.Reels;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

public interface ReelsService {

    Reels createReels(Reels reels, UUID userId) throws Exception;
    Reels getReelsById(UUID reelsId) throws FileNotFoundException;

    List<Reels> findReelsByUserId(UUID userId) throws Exception;

    List<Reels> findAllReels();


}
