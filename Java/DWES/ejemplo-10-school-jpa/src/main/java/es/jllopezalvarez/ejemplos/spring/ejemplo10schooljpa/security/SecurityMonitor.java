package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NullMarked;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@NullMarked
public class SecurityMonitor implements AuthenticationSuccessHandler, AuthenticationFailureHandler {
    @EventListener
    public void onSuccess(AuthenticationSuccessEvent event){
        Authentication auth = event.getAuthentication();
        System.out.printf("Se ha autenticado el usuario %s (en evento)\n", auth.getName());
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.printf("Se ha autenticado el usuario %s (en handler)\n", authentication.getName());
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.printf("No se ha podido autenticar el usuario. Error: %s (en handler)\n", exception.getMessage());

        response.sendRedirect("/login?error");
    }
}
