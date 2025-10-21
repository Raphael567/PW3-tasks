package br.com.etechas.tarefas.controllers;

import br.com.etechas.tarefas.dto.UsuarioCadastroDTO;
import br.com.etechas.tarefas.dto.UsuarioResponseDTO;
import br.com.etechas.tarefas.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> registrar(@RequestBody UsuarioCadastroDTO cadastrar){
        return new ResponseEntity<UsuarioResponseDTO>(usuarioService.registrar(cadastrar), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAll(){
        return new ResponseEntity<List<UsuarioResponseDTO>>(usuarioService.getAll(), HttpStatus.OK);
    }
}
