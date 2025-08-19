package br.com.etechas.tarefas.services;

import br.com.etechas.tarefas.dto.TarefasResponseDTO;
import br.com.etechas.tarefas.entity.Tarefa;
import br.com.etechas.tarefas.mapper.TarefaMapper;
import br.com.etechas.tarefas.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private TarefaMapper tarefaMapper;

    public List<TarefasResponseDTO> listarTarefas() {
        try {
            return tarefaMapper.toResponseDTOList(tarefaRepository.findAll());
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao buscar tarefas" + ex.getMessage());
        }
    }
}
