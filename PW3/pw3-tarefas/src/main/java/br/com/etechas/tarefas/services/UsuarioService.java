package br.com.etechas.tarefas.services;

import br.com.etechas.tarefas.dto.UsuarioCadastroDTO;
import br.com.etechas.tarefas.dto.UsuarioResponseDTO;
import br.com.etechas.tarefas.entity.Usuario;
import br.com.etechas.tarefas.mapper.UsuarioMapper;
import br.com.etechas.tarefas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public UsuarioResponseDTO registrar(UsuarioCadastroDTO cadastro) {
        usuarioRepository.findByUsername(cadastro.username()).ifPresent(u -> {
            throw new RuntimeException("Username já existe");
        });

        Usuario usuario = usuarioMapper.toEntity(cadastro);
        Usuario salvo = usuarioRepository.save(usuario);

        return usuarioMapper.toUsuarioResponseDTO(salvo);
    }
}
