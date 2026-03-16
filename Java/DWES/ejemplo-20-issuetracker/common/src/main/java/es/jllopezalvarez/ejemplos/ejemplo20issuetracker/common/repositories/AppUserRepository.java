package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.repositories;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmailIgnoreCase(String email);
}