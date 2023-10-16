package br.com.vinixavier.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

// Coloca todos os getters e setters, Caso eu queira colocar individualmente @getter
@Data
@Entity(name = "tb_users")
public class UserModel {

   @Id
   @GeneratedValue(generator = "UUID" )
   private UUID id;

   
   private String username;
   private String name;
   private String password;


   /* 
   *Quando o meu dado for gerado de forma automatica o banco de dados já vai ter a informação do Creation times tamp
   * 
   * 
   *  
   */
   @CreationTimestamp
   // Define quando o meu dado foi criado
   private LocalDateTime createAt;
}
