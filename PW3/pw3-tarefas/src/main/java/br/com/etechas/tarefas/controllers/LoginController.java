package br.com.etechas.tarefas.controllers;

import br.com.etechas.tarefas.dto.LoginRequestDTO;
import br.com.etechas.tarefas.dto.LoginResponseDTO;
import br.com.etechas.tarefas.security.JWTHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTHolder jwtHolder;

    @PostMapping()
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO login) {
        var authenticated = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.usuario(),
                        login.senha())
        );

        final String token = jwtHolder.generateToken((UserDetails) authenticated.getPrincipal());

        return new ResponseEntity<>(new LoginResponseDTO(token), HttpStatus.OK);
    }

}
