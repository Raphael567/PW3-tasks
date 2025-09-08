package br.com.etechas.tarefas.dto;

import br.com.etechas.tarefas.enums.StatusEnum;

import java.time.LocalDate;

public record TarefasRequestDTO (
    Long id,
    String titulo,
    String descricao,
    String responsavel,
    LocalDate dataLimite,
    StatusEnum status
) { }
