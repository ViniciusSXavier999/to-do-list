package br.com.vinixavier.todolist.task;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

/* ENTRE OS < > EU passo qual é a minha entidade e qual vai ser o id dessa entidade */
public interface ITaskRepository extends JpaRepository <TaskModel, UUID>{
    
}
