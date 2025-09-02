//Raphael Pereira Canuto
//Hellen Novi Salvador

package br.com.etechas.tarefas.services;


import br.com.etechas.tarefas.dto.TarefasResponseDTO;
import br.com.etechas.tarefas.entity.Tarefa;
import br.com.etechas.tarefas.enums.StatusEnum;
import br.com.etechas.tarefas.mapper.TarefaMapper;
import br.com.etechas.tarefas.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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

    public boolean excluirPorId(Long idTarefa) {
        Optional<Tarefa> tarefa  = tarefaRepository.findById(idTarefa);

        if(tarefa.isPresent() && tarefa.get().isPending()) {
            tarefaRepository.deleteById(idTarefa);
            return true;
        }  else {
            throw new RuntimeException("Tarefa em progresso ou concluída");
        }
        //return false;
    }

//    public boolean excluirPorId(Long idTarefa) {
//        Optional<Tarefa> tarefa = tarefaRepository.findById(idTarefa);
//
//        if (tarefa.isPresent()) {
//            if (tarefa.get().getStatus().equals(StatusEnum.PENDING)) {
//                tarefaRepository.deleteById(idTarefa);
//                return true;
//            } else {
//                throw new RuntimeException("Tarefa em progresso ou concluída");
//            }
//        }
//        return false;
//    }
}
