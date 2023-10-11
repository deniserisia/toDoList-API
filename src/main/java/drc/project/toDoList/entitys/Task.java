package drc.project.toDoList.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name ="tb_Taks")
public class Task {
    @Id
    @GeneratedValue(generator ="UUID")
    private UUID id;
    private String descricao;

    @Column(length = 50)
    private String titulo;
    private LocalDateTime inicioDaTarefa;
    private LocalDateTime fimDaTarefa;
    private String prioridade;

    private UUID idUser;

    @CreationTimestamp
    private LocalDateTime dataDeCadastroNaPlataforma;

}
