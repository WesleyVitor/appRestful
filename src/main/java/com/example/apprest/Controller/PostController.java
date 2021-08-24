package com.example.apprest.Controller;

import com.example.apprest.models.Post;
import com.example.apprest.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("/listaPosts")
    public ResponseEntity<List<Post>> getAllPosts(){
        List<Post> posts =  postRepository.findAll();
        if(posts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Post> salvarPost(@RequestBody @Valid Post post){
        return new ResponseEntity<Post>(postRepository.save(post), HttpStatus.CREATED);
    }

    @GetMapping("/listaPost/{id}")
    public ResponseEntity<Post> getOnePost(@PathVariable(value = "id") long id){
        Optional<Post> post = postRepository.findById(id);
        if(!post.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<Post>(post.get(), HttpStatus.OK);
        }
    }

    @PutMapping("/editarPost/{id}")
    public ResponseEntity<Post> editarPost(@RequestBody @Valid Post post, @PathVariable(value = "id") long id){
        Optional<Post> postEncontrado = postRepository.findById(id);
        if(!postEncontrado.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            post.setId(postEncontrado.get().getId());
            return new ResponseEntity<Post>(postRepository.save(post), HttpStatus.OK);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarPost(@PathVariable(value = "id") long id){
        Optional<Post> postEncontrado = postRepository.findById(id);
        if(!postEncontrado.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            postRepository.delete(postEncontrado.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

}
