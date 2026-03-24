package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.api.controllers;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.api.dto.LoginRequestDto;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.api.dto.LoginResponseDto;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.api.services.JwtService;
import org.jspecify.annotations.NullMarked;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        // Comprobar usuario y contraseña
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );

        // Si llegamos aquí es que el usuario y contraseña son correctos
        String accessToken = jwtService.generateAccessToken(loginRequestDto.getUsername());

        return ResponseEntity.ok(LoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken("token de refresco")
                .build());
    }
}
