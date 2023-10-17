package br.com.vinixavier.todolist.task;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository repository;

    @PostMapping("/")
    public TaskModel create( @RequestBody TaskModel task, HttpServletRequest request) {
        // Recuperando os dados do idUser lá do meu filterTask
        var idUser = request.getAttribute("idUser");
        task.setIdUser((UUID)idUser);

        var saveTask = repository.save(task);
        return saveTask;
    }
    
}
