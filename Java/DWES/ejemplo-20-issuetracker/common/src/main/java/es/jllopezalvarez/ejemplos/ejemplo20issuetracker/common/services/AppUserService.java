package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.services;



import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.AppUser;

import java.util.Optional;

public interface AppUserService {
    public Optional<AppUser> findUserByEmail(String email);
}
