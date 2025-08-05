package br.com.etechas.tarefas.services;

import br.com.etechas.tarefas.entity.Tarefa;
import br.com.etechas.tarefas.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> listarTarefas() {
        try {
            return tarefaRepository.findAll();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao buscar tarefas" + ex.getMessage());
        }
    }
}
