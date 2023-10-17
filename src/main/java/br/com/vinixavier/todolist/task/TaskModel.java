package br.com.vinixavier.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "tb_tasks")
@Data
public class TaskModel {

    /*O QUE UMA TAREFA PODE TER ?
       ID
     * USUARIO(ID_usuario)
     * DESCRIÇÃO
     * TÍTULO
     * DATA DE INICIO
     * DATA DE TERMINO DA TAREFA
     * PRIORIDADE 
     * 
    */

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;

    @Column(length = 50)
    private String title;
    private String priority;
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    
    private UUID idUser;

    @CreationTimestamp
    private LocalDateTime createdAt;

    

    
}
