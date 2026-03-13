package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@NullMarked
public class AlternativeSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        ///
        System.out.printf("Login OK en clase que hereda. Usuario: %s\n.", authentication.getPrincipal());

        super.onAuthenticationSuccess(request, response, authentication);


    }
}
