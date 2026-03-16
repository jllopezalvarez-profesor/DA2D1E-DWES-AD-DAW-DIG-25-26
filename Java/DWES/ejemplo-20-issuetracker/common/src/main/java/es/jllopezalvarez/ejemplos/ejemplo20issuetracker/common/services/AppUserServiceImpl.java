package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.services;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.AppUser;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.repositories.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;

    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public Optional<AppUser> findUserByEmail(String email) {
        return appUserRepository.findByEmailIgnoreCase(email);
    }
}
