package br.com.etechas.tarefas.repositories;

import br.com.etechas.tarefas.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> { }
