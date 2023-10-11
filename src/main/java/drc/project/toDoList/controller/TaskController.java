package drc.project.toDoList.controller;

import drc.project.toDoList.entitys.Task;
import drc.project.toDoList.entitys.User;
import drc.project.toDoList.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tarefas")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/criacao")
    public Task create(@RequestBody Task task){
        var tarefa = this.taskRepository.save(task);
        return tarefa;
    }
}
