package com.ibragimov.mysocialmedia.api.service.impl;

import com.ibragimov.mysocialmedia.api.model.Comment;
import com.ibragimov.mysocialmedia.api.model.Post;
import com.ibragimov.mysocialmedia.api.model.User;
import com.ibragimov.mysocialmedia.api.repository.CommentRepository;
import com.ibragimov.mysocialmedia.api.repository.PostRepository;
import com.ibragimov.mysocialmedia.api.service.CommentService;
import com.ibragimov.mysocialmedia.api.service.PostService;
import com.ibragimov.mysocialmedia.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment createComment(Comment comment, UUID postId, UUID userId) throws Exception {
        User user = userService.findUserById(userId);
        Post post = postService.findPostById(postId);

        comment.setUser(user);
        comment.setContent(comment.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);
        post.getComments().add(savedComment);
        postRepository.save(post);

        return savedComment;
    }

    @Override
    public Comment findCommentById(Integer id) throws Exception {
        Optional<Comment> byId = commentRepository.findById(id);
        if (byId.isEmpty()) {
            throw new Exception("Comment doesnt exist with this id:" + id);
        }
        return byId.get();
    }

    @Override
    public Comment likeComment(Integer commentId, UUID userId) throws Exception {
        Comment comment = findCommentById(commentId);
        User user = userService.findUserById(userId);

        if(!comment.getLiked().contains(user)){
            comment.getLiked().add(user);
        }else {
            comment.getLiked().remove(user);
        }

        return commentRepository.save(comment);
    }
}
