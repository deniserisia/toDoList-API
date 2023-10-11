package drc.project.toDoList.repository;

import drc.project.toDoList.entitys.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
}
