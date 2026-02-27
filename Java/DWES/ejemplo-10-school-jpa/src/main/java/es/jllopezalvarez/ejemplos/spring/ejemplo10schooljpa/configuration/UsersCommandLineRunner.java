package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.configuration;

import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.entities.AppUser;
import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.services.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UsersCommandLineRunner implements CommandLineRunner {


    private final AppUserService appUserService;
    private final PasswordEncoder passwordEncoder;

    public UsersCommandLineRunner(AppUserService appUserService, PasswordEncoder passwordEncoder) {
        this.appUserService = appUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {


        String encodedPassword = passwordEncoder.encode("password");

        AppUser user = AppUser.builder()
                // .userId() // No hace falta porque estoy creando nuevo
                .email("jllopezalvarez@educa.madrid.org")
                .password(encodedPassword)
                .firstName("José Luis")
                .lastName("López Álvarez")
                // .birthDate() // Opcional en la entidad
                .registeredAt(LocalDateTime.now())
                .build();
        appUserService.save(user);


    }
}
