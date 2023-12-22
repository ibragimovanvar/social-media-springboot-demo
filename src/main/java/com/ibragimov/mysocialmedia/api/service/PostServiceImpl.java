package com.ibragimov.mysocialmedia.api.service.impl;

import com.ibragimov.mysocialmedia.api.model.Post;
import com.ibragimov.mysocialmedia.api.model.User;
import com.ibragimov.mysocialmedia.api.repository.PostRepository;
import com.ibragimov.mysocialmedia.api.repository.UserRepository;
import com.ibragimov.mysocialmedia.api.service.PostService;
import com.ibragimov.mysocialmedia.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Post createNewPost(Post post, UUID userId) throws Exception {
        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setVideo(post.getVideo());
        newPost.setUser(userService.findUserById(userId));
        newPost = postRepository.save(newPost);
        return newPost;
    }

    @Override
    public String deletePost(UUID postId, UUID userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if(post.getUser().getId() != user.getId())
          throw new Exception("You cant delete another users post");

        postRepository.delete(post);
        return "Post deleted successfully";
    }

    @Override
    public List<Post> findPostByUserId(UUID userId) {
       return postRepository.findPostByUserId(userId);
    }

    @Override
    public Post findPostById(UUID postId) throws Exception {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isEmpty()) {
            throw new Exception("Post not found with id " + postId);
        }

        return optionalPost.get();
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(UUID postId, UUID userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (user.getSavedPost().contains(post)){
            user.getSavedPost().remove(post);
        }else {
            user.getSavedPost().add(post);
        }

         userRepository.save(user);
        return post;
    }

    @Override
    public Post likePost(UUID postId, UUID userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if(post.getLikes().contains(user)){
            post.getLikes().remove(user);
        }else {
            post.getLikes().add(user);
        }

        return postRepository.save(post);
    }
}
