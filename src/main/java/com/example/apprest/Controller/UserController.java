package com.example.apprest.Controller;

import com.example.apprest.models.Post;
import com.example.apprest.models.User;
import com.example.apprest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/listarUsuarios")
    public ResponseEntity<List<User>> listarUsuarios(){
        List<User> users = userService.index();
        if(!users.isEmpty()){
            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<User> salvarUsuario(@RequestBody @Valid User user){
        return new ResponseEntity<User>(userService.save(user),HttpStatus.CREATED);
    }

    @PutMapping("/{id_user}")
    public ResponseEntity<User> updateUser(@RequestBody @Valid User userRequest,
                                           @PathVariable(value = "id_user") long id_user){
        Optional<User> user = userService.show(id_user);
        if(user.isPresent()){
            user.get().atualizarUsuario(userRequest);
            return new ResponseEntity<User>(userService.save(user.get()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id_user}")
    public ResponseEntity<?> deletarPost(@PathVariable(value = "id_user") long id_user){
        Optional<User> userEncontrado = userService.show(id_user);
        if(!userEncontrado.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            userService.destroy(userEncontrado.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

}
