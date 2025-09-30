package br.com.etechas.tarefas.dto;

import br.com.etechas.tarefas.enums.RoleEnum;

public record UsuarioCadastroDTO(
        String usernamw,
        String password,
        RoleEnum role
) {
    public String username() {
        return null;
    }
}
