package br.com.vinixavier.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository <UserModel, UUID> {
    // criando um método
    UserModel findByUsername (String username);
    
}
