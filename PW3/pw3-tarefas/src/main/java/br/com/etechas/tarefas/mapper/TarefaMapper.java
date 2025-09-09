package br.com.etechas.tarefas.mapper;

import br.com.etechas.tarefas.dto.TarefasRequestDTO;
import br.com.etechas.tarefas.dto.TarefasResponseDTO;
import br.com.etechas.tarefas.entity.Tarefa;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper (componentModel = "spring")
public interface TarefaMapper {
    TarefaMapper INSTANCE = Mappers.getMapper(TarefaMapper.class);
    List<TarefasResponseDTO> toResponseDTOList(List<Tarefa> tarefa);
<<<<<<< HEAD

    Tarefa toResponseTarefa(TarefaResponseDTO tarefa);
=======
    Tarefa toEntity (TarefasRequestDTO dto);
>>>>>>> cb6d9fc5344e9d3114d8d51dc014e7c1647486bd
}
