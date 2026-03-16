package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.repositories;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
}