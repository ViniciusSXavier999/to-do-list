package br.com.vinixavier.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository repository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel user) {

       var usuario = repository.findByUsername(user.getUsername());

       if (usuario != null) {
        System.out.println("Usuario já existe");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe!!");
       }


        var userCreated = repository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
