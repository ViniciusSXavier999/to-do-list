package br.com.vinixavier.todolist.user;

import lombok.Data;

// Coloca todos os getters e setters, Caso eu queira colocar individualmente @getter
@Data
public class UserModel {
   private String username;
   private String name;
   private String password;


}
