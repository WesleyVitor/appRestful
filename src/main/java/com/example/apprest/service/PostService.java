package com.example.apprest.service;

import com.example.apprest.models.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> listaTodosOsPosts();
    Post salvarPost(Post post);
    Optional<Post> pegarPostEspecifico(long id);
    void deletarPost(Post post);
}
