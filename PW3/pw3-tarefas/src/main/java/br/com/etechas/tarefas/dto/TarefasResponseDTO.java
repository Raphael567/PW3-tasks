package br.com.etechas.tarefas.dto;

import br.com.etechas.tarefas.enums.StatusEnum;

import java.time.LocalDate;

public record TarefasResponseDTO(
        Long id,
        String titulo,
        String responsavel,
        LocalDate dataLimite,
        StatusEnum status
) {
}
