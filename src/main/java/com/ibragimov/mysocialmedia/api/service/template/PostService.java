package com.ibragimov.mysocialmedia.api.service;

import com.ibragimov.mysocialmedia.api.model.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {

    Post createNewPost(Post post, UUID userId) throws Exception;

    String deletePost(UUID postId, UUID userId) throws Exception;

    List<Post> findPostByUserId(UUID userId);

    Post findPostById(UUID postId) throws Exception;

    List<Post> findAllPost();

    Post savedPost(UUID postId, UUID userId) throws Exception;

    Post likePost(UUID postId, UUID userId) throws Exception;
}
