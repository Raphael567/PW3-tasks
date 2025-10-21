//Mariana e Paola

package br.com.etechas.tarefas.controllers;

import br.com.etechas.tarefas.dto.TarefaPostDTO;
import br.com.etechas.tarefas.dto.TarefaResponseDTO;
import br.com.etechas.tarefas.services.TarefaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@CrossOrigin(origins = "*")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public ResponseEntity<List<TarefaResponseDTO>> listarTarefas(){
        return new ResponseEntity<>(tarefaService.listarTarefas(), HttpStatus.OK);
    }

    /*
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTarefaPorId(@PathVariable Long id){
        try {
            tarefaService.excluirPorId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        if(tarefaService.deleteById(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<TarefaPostDTO> criarTarefa(@RequestBody TarefaPostDTO tarefaPostDTO){
        return new ResponseEntity<>(tarefaService.criarTarefa(tarefaPostDTO), HttpStatus.OK);
    }

}
