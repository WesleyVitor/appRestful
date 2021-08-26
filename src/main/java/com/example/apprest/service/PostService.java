package com.example.apprest.service;

import com.example.apprest.models.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> getAllPosts();
    Post savePost(Post post);
    Optional<Post> getOnePost(long id);
    void deletePost(Post post);
}
