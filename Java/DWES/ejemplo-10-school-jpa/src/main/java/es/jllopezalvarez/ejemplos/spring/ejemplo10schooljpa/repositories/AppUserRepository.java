package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.repositories;

import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.entities.AppUser;
import org.springframework.data.repository.ListCrudRepository;

public interface AppUserRepository extends ListCrudRepository<AppUser, Long> {
}
