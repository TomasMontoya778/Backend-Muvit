package com.muvit.MUVIT.infrastructure.helpers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;


@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter{
 @Autowired
    private final JwtService jwtService;

    @Autowired
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                /*Obtener el token del request */
        String token = this.getTokenFromRequest(request);
/*Si el token es null entoncs que siga con los filtros del Spring security */
                if (token == null) {
                filterChain.doFilter(request, response); 
                return;
            }
            // Sacar el username del token
        String userName = this.jwtService.getUserNameFromToken(token);

        /*Si no lo encuentra en el contexto del spring  */

        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
    // Obtener la informaciond el usuario
        UserDetails user = userDetailsService.loadUserByUsername(userName);
        // Si el token es valido
        if (this.jwtService.isTokenValid(token, user)) {
            // Creamos la autentitifacion y la registramos en el contexto de spring
           var authToken = new UsernamePasswordAuthenticationToken(
            userName,
            null,
            user.getAuthorities());

            /*setDetails: Establece dettales adicionales de la autenticacion,
             como la direccion ip y la sesion de donde se realiza la solicitud */
            authToken.setDetails(new WebAuthenticationDetails(request));

            /*Guardar la autenticacion en el contexto de Spring */
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
       
        } filterChain.doFilter(request, response); 
    }
    public String getTokenFromRequest(HttpServletRequest request){
        final String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasLength(token) && token.startsWith("Bearer")){
            return token.substring(7);
        }
        return null;
    }
}
