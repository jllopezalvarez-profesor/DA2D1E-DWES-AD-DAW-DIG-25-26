package es.jllopezalvarez.dwes.spring.ejemplos.ejemplo06schoolgenericrepo.repositories;


import es.jllopezalvarez.dwes.spring.ejemplos.ejemplo06schoolgenericrepo.entities.Entity;

import java.util.List;
import java.util.Optional;

public interface Repository<ID, T extends Entity<ID>> {
    List<T> findAll();
    Optional<T> findById(ID id);
    T save(T item);
    void delete(T item);
}
