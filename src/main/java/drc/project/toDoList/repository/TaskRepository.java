package drc.project.toDoList.repository;

import drc.project.toDoList.entitys.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findByIdUser(UUID idUser);

    //Task findByIdAndIdUser(UUID id, UUID idUser);
}
