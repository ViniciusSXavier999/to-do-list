package br.com.vinixavier.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity create( @RequestBody TaskModel task, HttpServletRequest request) {
        // Recuperando os dados do idUser lá do meu filterTask
        var idUser = request.getAttribute("idUser");
        task.setIdUser((UUID)idUser);


        // validação para ver se a data que a gente ta criando a tasks é maior que a data atual
        // now() mostra a data atual
        var currentDate = LocalDateTime.now();

        /*O método isBefore() da classe LocalDate em Java verifica se esta data é anterior à data especificada.
         * já o ISAFTER verifica se a data é maior 
        */
        if(currentDate.isAfter(task.getStartAt()) || currentDate.isAfter(task.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("A data de inicío / Data de término deve ser maior que a data atual");
        }

        if(task.getStartAt().isAfter(task.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("A data de inicio deve ser menor que a data de término");
        }
        

        var saveTask = repository.save(task);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        var tasks =   repository.findByIdUser((UUID)idUser);

        return tasks;

    }

}
