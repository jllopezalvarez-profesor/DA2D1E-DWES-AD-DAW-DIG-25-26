package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.services;

import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.entities.AppUser;
import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.repositories.AppUserRepository;
import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.security.AppUserDetails;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    public AppUserDetailsService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Autenticando el usuario...");
        AppUser appUser = appUserRepository
                .findAll().stream()
                .filter(u -> u.getEmail().equals(username))
                .findFirst()
                .orElseThrow(()->new UsernameNotFoundException("No se ha encontrado el usuario con email " + username));

        return new AppUserDetails(appUser);
    }
}
