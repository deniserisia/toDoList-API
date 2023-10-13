package drc.project.toDoList.controller;

import drc.project.toDoList.entitys.Task;
import drc.project.toDoList.repository.TaskRepository;
import drc.project.toDoList.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tarefas")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/criacao")
    public ResponseEntity create(@RequestBody Task task, HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        task.setIdUser((UUID) idUser);

        var currentDate = LocalDateTime.now();
        if (currentDate.isAfter(task.getInicioDaTarefa()) || currentDate.isAfter(task.getFimDaTarefa())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início / data de término  deve ser maior que a data atual.");
        }

        if (task.getInicioDaTarefa().isAfter(task.getFimDaTarefa())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início deve ser menor que a de término.");
        }

        var tarefa = this.taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.OK).body(tarefa);
        }

        @GetMapping("/lista-das-minhas-tarefas")
        public List<Task> list(HttpServletRequest request){
        var idUser = request.getAttribute("idUser");
        var tarefas = this.taskRepository.findByIdUser((UUID) idUser);
        return tarefas;
        }

        @PutMapping("/{id}")
        public ResponseEntity update(@RequestBody Task taskEntity, @PathVariable UUID id, HttpServletRequest request){
            //var idUser = request.getAttribute("idUser");
            var task = this.taskRepository.findById(id).orElse(null);

            if (task == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tarefa não encontrada");

            }
            var idUser = request.getAttribute("idUser");

            if (!task.getIdUser().equals(idUser)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Usuário não tem permissão para alterar essa tarefa");
            }

            Utils.copyNonNullProperties(taskEntity, task);
            var taskUpdate = this.taskRepository.findById(id).orElse(null);
            return ResponseEntity.ok().body(taskUpdate);
        }


}
