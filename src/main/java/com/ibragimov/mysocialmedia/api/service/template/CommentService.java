package com.ibragimov.mysocialmedia.api.service;

import com.ibragimov.mysocialmedia.api.model.Comment;

import java.util.UUID;

public interface CommentService {

    Comment createComment(Comment comment, UUID postId, UUID userId) throws Exception;
    Comment findCommentById(Integer id) throws Exception;
    Comment likeComment(Integer commentId, UUID userId) throws Exception;

}
