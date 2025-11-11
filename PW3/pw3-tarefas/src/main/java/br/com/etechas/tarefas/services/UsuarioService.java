package br.com.etechas.tarefas.services;

import br.com.etechas.tarefas.dto.UsuarioCadastroDTO;
import br.com.etechas.tarefas.dto.UsuarioResponseDTO;
import br.com.etechas.tarefas.entity.Usuario;
import br.com.etechas.tarefas.mapper.UsuarioMapper;
import br.com.etechas.tarefas.repositorys.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {
    private String username;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioResponseDTO registrar(UsuarioCadastroDTO cadastro) {

        Optional<Usuario> usuarioName =  usuarioRepository.findByUsername(cadastro.username());

        if(usuarioName.isPresent()){
            throw new RuntimeException("Usuário com mesmo usuario já existe!");
        }

        var usuario = usuarioMapper.toEntity(cadastro);

        var senhaCifrada = passwordEncoder.encode(cadastro.password());

        usuario.setUsername(cadastro.username());
        usuario.setPassword(senhaCifrada);

        usuarioRepository.save(usuario);

        UsuarioResponseDTO usuarioResponseDTO = usuarioMapper.toUsuarioResponseDTO(usuario);

        System.out.println(cadastro);
        System.out.println(usuario);
        return usuarioResponseDTO;
    }

    public List<UsuarioResponseDTO> getAll(){
        List<Usuario> usuarios = usuarioRepository.findAll();

        List<UsuarioResponseDTO> usuariosResponseDTO = usuarioMapper.toUsuarioResponseDTOList(usuarios);

        return usuariosResponseDTO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Usuario não encontrado com username: "+ username));
    }

}
