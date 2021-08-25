package com.example.apprest.service;

import com.example.apprest.models.Post;
import com.example.apprest.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PostServiceImp implements PostService{

    @Autowired
    PostRepository postRepository;

    @Override
    public List<Post> listaTodosOsPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post salvarPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Optional<Post> pegarPostEspecifico(long id) {
        return postRepository.findById(id);
    }

    @Override
    public void deletarPost(Post post) {
        postRepository.delete(post);
    }


}
