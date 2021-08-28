package com.example.apprest.Controller;

import com.example.apprest.models.Post;
import com.example.apprest.models.User;
import com.example.apprest.repository.PostRepository;
import com.example.apprest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class PostController {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/users/{userId}/posts")
    public ResponseEntity<List<Post>> listarPostagens(@PathVariable(value = "userId") long userId){
        List<Post> posts = postRepository.findAllByUser_Id(userId);
        if(posts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
        }
    }

    @PostMapping("/users/{userId}/posts")
    public ResponseEntity<Post> salvarPostagem(@PathVariable(value = "userId") long userId,
                                               @RequestBody @Valid Post post){
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            post.setUser(user.get());
            return new ResponseEntity<Post>(postRepository.save(post), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/users/{userId}/posts/{postId}")
    public ResponseEntity<Post> atualizarPostagem(@PathVariable(value = "userId") long userId,
                                               @PathVariable(value = "postId") long postId,
                                               @RequestBody @Valid Post postRequest){
        Optional<User> user = userRepository.findById(userId);
        Optional<Post> post = postRepository.findById(postId);
        if(!user.isPresent() && !post.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        post.get().alterarPost(postRequest);
        return new ResponseEntity<Post>(postRepository.save(post.get()),HttpStatus.OK);

    }

    @DeleteMapping("/users/{userId}/posts/{postId}")
    public ResponseEntity<?> deletarPostagem(@PathVariable(value = "userId") long userId,
                                               @PathVariable(value = "postId") long postId){
        Optional<User> user = userRepository.findById(userId);
        Optional<Post> post = postRepository.findById(postId);
        if(!user.isPresent() && !post.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postRepository.delete(post.get());
        return new ResponseEntity<Post>(HttpStatus.OK);

    }


}
