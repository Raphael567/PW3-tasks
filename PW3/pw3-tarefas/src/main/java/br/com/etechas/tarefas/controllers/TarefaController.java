//Raphael Pereira Canuto
//Hellen Novi Salvador

package br.com.etechas.tarefas.controllers;

import br.com.etechas.tarefas.dto.TarefasRequestDTO;
import br.com.etechas.tarefas.dto.TarefasResponseDTO;
import br.com.etechas.tarefas.entity.Tarefa;
import br.com.etechas.tarefas.enums.StatusEnum;
import br.com.etechas.tarefas.services.TarefaService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public ResponseEntity<List<TarefasResponseDTO>> listarTarefas() {
        return new ResponseEntity<>(tarefaService.listarTarefas(), HttpStatus.OK);
    }

    @PostMapping("/criar")
    public ResponseEntity<Void> criarTarefa(@RequestBody TarefasRequestDTO dto) {
        tarefaService.criarTarefa(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> editarTarefa(@PathVariable Long id, @RequestBody TarefasRequestDTO dto) {
        Tarefa tarefaEditada = tarefaService.editarTarefa(id, dto);
        return new ResponseEntity<>(tarefaEditada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> excluirPorId(@PathVariable Long id) {
        var verifica = tarefaService.excluirPorId(id);

        if(verifica) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
