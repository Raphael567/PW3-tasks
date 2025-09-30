package br.com.etechas.tarefas.mapper;

import br.com.etechas.tarefas.dto.UsuarioCadastroDTO;
import br.com.etechas.tarefas.dto.UsuarioResponseDTO;
import br.com.etechas.tarefas.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface UsuarioMapper {
    Usuario toEntity(UsuarioCadastroDTO cadastro);
    UsuarioResponseDTO toUsuarioResponseDTO(Usuario usuario);
}
