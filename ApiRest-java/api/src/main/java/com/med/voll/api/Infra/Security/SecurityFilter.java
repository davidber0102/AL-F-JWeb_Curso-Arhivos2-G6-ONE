package com.med.voll.api.Infra.Security;

import com.med.voll.api.Domain.Usuarios.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                    throws ServletException, IOException {
        //System.out.println("El filtro esta siendo llamado");
        //Obtener el token del header
        System.out.println("Este es el inicio del filter");
        var authHeader = request.getHeader("Authorization");
        System.out.println(authHeader);
        if(authHeader != null ) {
            //System.out.println("validamos qie token no es nulll");
            var token = authHeader.replace("Bearer ", "");
            //System.out.println(token);
            //System.out.println(tokenService.getSubject(token)); //Este usuario tiene sesion
            var nombreUsuario = tokenService.getSubject(token);
            if (nombreUsuario != null){
                //token valido
                var usuario = usuarioRepository.findByLogin(nombreUsuario);
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                        usuario.getAuthorities()); // Forzamos el inicio de sesion
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
