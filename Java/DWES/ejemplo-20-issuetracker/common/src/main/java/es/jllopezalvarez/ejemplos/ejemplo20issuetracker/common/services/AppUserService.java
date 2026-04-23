package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.services;



import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.AppUser;

import java.util.List;
import java.util.Optional;

public interface AppUserService {
    public Optional<AppUser> findByEmail(String email);
    public Optional<AppUser> findById(Long id);

    List<AppUser> findAll();
}
