package br.com.etechas.tarefas.dto;


import java.time.LocalDate;

public record TarefaPostDTO(
        String titulo,
        String descricao,
        String responsavel,
        LocalDate dataLimite
) {
}
