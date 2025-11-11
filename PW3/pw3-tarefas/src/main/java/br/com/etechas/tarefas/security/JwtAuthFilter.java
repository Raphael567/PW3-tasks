package br.com.etechas.tarefas.security;

import br.com.etechas.tarefas.repositorys.UsuarioRepository;
import br.com.etechas.tarefas.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService; // serviço que valida o

    @Autowired
    private UsuarioRepository usuarioRepository; // busca o usuário no banco

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {

        String path = request.getServletPath();

        if (path.equals("/login") || (path.equals("/usuarios") && request.getMethod().equals("POST"))) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = getToken(request);

        if (token != null && jwtService.isTokenValido(token)) {
            String username = jwtService.extractUsername(token);
            var usuario = usuarioRepository.findByUsername(username).orElse(null);

            if (usuario != null) {
                var auth = new UsernamePasswordAuthenticationToken(
                        usuario, null, usuario.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}
