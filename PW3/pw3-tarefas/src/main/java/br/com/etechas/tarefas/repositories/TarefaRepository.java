package br.com.etechas.tarefas.repositories;

import br.com.etechas.tarefas.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    @Query("select t from Tarefa t where t.id = :id and " + "t.status = br.com.etechas.tarefas.enums.StatusEnum.PENDING")
    Optional<Tarefa> findByStatusAndPending(Long id);
}
