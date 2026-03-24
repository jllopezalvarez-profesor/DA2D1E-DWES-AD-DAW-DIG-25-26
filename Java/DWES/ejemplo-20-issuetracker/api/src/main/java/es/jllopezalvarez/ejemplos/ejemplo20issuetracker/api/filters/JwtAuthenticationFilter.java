package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.api.filters;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.api.services.JwtServiceImpl;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.security.AppUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@NullMarked
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtServiceImpl jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtServiceImpl jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 1. Si ya está autenticado, saltamos este filtro de inmediato
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");

        // 2. Validación rápida del header
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = authHeader.substring(7);
        final String username = jwtService.extractUsername(jwt);

        // 3. Si el nombre de usuario no llega, salir
        if (username == null || username.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }

        // 4. Buscar el usuario - Esto lanza excepción UsernameNotFoundException si no existe el usuario
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

        // 5. Verificar si el token ha caducado
        if (!jwtService.isTokenExpired(jwt)) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }


}

